

package org.datasphere.mdm.core.service.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.util.JobUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.StepExecutionSplitter;
import org.springframework.batch.integration.partition.StepExecutionRequest;
import org.springframework.batch.poller.DirectPoller;
import org.springframework.batch.poller.Poller;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.Payloads;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.hazelcast.topic.ITopic;

/**
 * @author Mikhail Mikhailov
 * Equal distribution partition handler, sending handle messages to all cluster members via topic.
 */
public class JobTargetedPartitionHandler implements PartitionHandler, InitializingBean {
    /**
     * This logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTargetedPartitionHandler.class);
    /**
     * BQueue for sending partitions to.
     */
    private ITopic<Message<StepExecutionRequest>> messagingGateway;
    /**
     * Some magic from SB.
     */
    private int gridSize = 1;
    /**
     * Name of the step, which will be used to provess partitions.
     */
    private String stepName;
    /**
     * Poll interval to poll partitions for completion in millis.
     */
    private long pollInterval = 10000;
    /**
     * The job explorer to use. If null, one will be created.
     */
    private JobExplorer jobExplorer;
    /**
     * Timeout to wait before fail.
     */
    private long timeout = -1;
    /**
     * Data source to use.
     */
    private DataSource dataSource;
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.notNull(stepName, "A step name must be provided for the remote workers.");
        Assert.state(messagingGateway != null, "The MessagingOperations must be set.");
        Assert.state(!(dataSource == null && jobExplorer == null), "Both dataSource and jobExplorer are null. Handler is misconfigured.");

        if (dataSource != null && jobExplorer == null) {
            JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
            jobExplorerFactoryBean.setDataSource(dataSource);
            jobExplorerFactoryBean.afterPropertiesSet();
            jobExplorer = jobExplorerFactoryBean.getObject();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<StepExecution> handle(StepExecutionSplitter stepSplitter, StepExecution splitExecution)
            throws Exception {

        Set<StepExecution> split = stepSplitter.split(splitExecution, gridSize);
        if (CollectionUtils.isEmpty(split)) {
            return Collections.emptyList();
        }

        Map<Integer, StepExecution> steps = new TreeMap<>();
        split.forEach(step -> {

            String[] parts = StringUtils.split(step.getStepName(), ':');
            steps.put(Integer.parseInt(parts[parts.length - 2]), step);
        });

        split.clear();

        return runSteps(steps);
    }

    /**
     * When using job repository polling, the time limit to wait.
     *
     * @param timeout millisconds to wait, defaults to -1 (no timeout).
     */
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    /**
     * {@link org.springframework.batch.core.explore.JobExplorer} to use to query the job repository.  Either this or a
     * {@link javax.sql.DataSource} is required when using job repository polling.
     *
     * @param jobExplorer {@link org.springframework.batch.core.explore.JobExplorer} to use for lookups
     */
    public void setJobExplorer(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    /**
     * How often to poll the job repository for the status of the slaves.
     *
     * @param pollInterval milliseconds between polls, defaults to 10000 (10 seconds).
     */
    public void setPollInterval(long pollInterval) {
        this.pollInterval = pollInterval;
    }

    /**
     * {@link javax.sql.DataSource} pointing to the job repository
     *
     * @param dataSource {@link javax.sql.DataSource} that points to the job repository's store
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * A pre-configured gateway for sending and receiving messages to the remote workers. Using this property allows a
     * large degree of control over the timeouts and other properties of the send. It should have channels set up
     * internally: <ul> <li>request channel capable of accepting {@link StepExecutionRequest} payloads</li> <li>reply
     * channel that returns a list of {@link StepExecution} results</li> </ul> The timeout for the repoy should be set
     * sufficiently long that the remote steps have time to complete.
     *
     * @param messagingGateway the {@link org.springframework.integration.core.MessagingTemplate} to set
     */
    public void setMessagingGateway(ITopic<Message<StepExecutionRequest>> messagingGateway) {
        this.messagingGateway = messagingGateway;
    }

    /**
     * Passed to the {@link StepExecutionSplitter} in the {@link #handle(StepExecutionSplitter, StepExecution)} method,
     * instructing it how many {@link StepExecution} instances are required, ideally. The {@link StepExecutionSplitter}
     * is allowed to ignore the grid size in the case of a restart, since the input data partitions must be preserved.
     *
     * @param gridSize the number of step executions that will be created
     */
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    /**
     * The name of the {@link Step} that will be used to execute the partitioned {@link StepExecution}. This is a
     * regular Spring Batch step, with all the business logic required to complete an execution based on the input
     * parameters in its {@link StepExecution} context. The name will be translated into a {@link Step} instance by the
     * remote worker.
     *
     * @param stepName the name of the {@link Step} instance to execute business logic
     */
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    /**
     * @return the stepName
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * @param messages the messages to be aggregated
     * @return the list as it was passed in
     */
    @Aggregator(sendPartialResultsOnExpiry = "true")
    public List<?> aggregate(@Payloads List<?> messages) {
        return messages;
    }

    /**
     * Handle sequential execution.
     * @param steps steps
     * @return collection
     * @throws Exception
     */
    private Collection<StepExecution> runSteps(final Map<Integer, StepExecution> steps) throws Exception {

        int messageCount = 0;
        Iterator<StepExecution> sei = steps.values().iterator();
        while (sei.hasNext()) {
            StepExecution stepExecution = sei.next();
            messagingGateway.publish(createMessage(messageCount++, steps.size(), stepExecution));
        }

        final List<StepExecution> result = new ArrayList<>(steps.size());

        StepsPollingCallable callable = new StepsPollingCallable(steps.values(), result);
        Poller<Collection<StepExecution>> poller = new DirectPoller<>(pollInterval);
        Future<Collection<StepExecution>> futures = poller.poll(callable);

        if (timeout >= 0) {
            return futures.get(timeout, TimeUnit.MILLISECONDS);
        } else {
            return futures.get();
        }
    }

    /**
     * Creates a new step request.
     * @param sequenceNumber seqno
     * @param sequenceSize the sequence size
     * @param stepExecutionRequest the request
     * @return message
     */
    private Message<StepExecutionRequest> createMessage(int sequenceNumber, int sequenceSize, StepExecution stepExecution) {

        String[] parts = StringUtils.split(stepExecution.getStepName(), ':');
        if (parts.length < 3) {
            throw new IllegalArgumentException("Partition name malformed. Must consist of three parts [partition:<number>:UUID].");
        }

        String targetMemberUUID = StringUtils.trim(parts[parts.length - 1]);
        StepExecutionRequest request = new StepExecutionRequest(stepName, stepExecution.getJobExecutionId(), stepExecution.getId());
        return MessageBuilder.withPayload(request)
            .setSequenceNumber(sequenceNumber)
            .setSequenceSize(sequenceSize)
            .setCorrelationId(request.getJobExecutionId() + ":" + request.getStepName())
            .setHeader(JobUtils.JOB_CLUSTER_MEMBER_UUID, targetMemberUUID)
            .build();
    }
    /**
     * Steps polling callable.
     * @author Mikhail Mikhailov
     */
    private class StepsPollingCallable implements Callable<Collection<StepExecution>> {
        /**
         * In collection.
         */
        private final Collection<StepExecution> in;
        /**
         * Out collection.
         */
        private final Collection<StepExecution> out;
        /**
         * Constructor.
         * @param in collection to read from
         * @param out collection to write to
         */
        public StepsPollingCallable(Collection<StepExecution> in, Collection<StepExecution> out) {
            super();
            this.in = in;
            this.out = out;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public Collection<StepExecution> call() throws Exception {

            for (Iterator<StepExecution> i = in.iterator(); i.hasNext(); ) {

                StepExecution inStep = i.next();
                StepExecution outStep =
                    jobExplorer.getStepExecution(
                            inStep.getJobExecutionId(),
                            inStep.getId());

                if (!outStep.getStatus().isRunning()) {
                    out.add(outStep);
                    i.remove();
                }
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Currently waiting for {} partitions to finish", in.size());
            }

            if (this.in.isEmpty()) {
                return out;
            } else {
                // null means 'go to the next cycle'
                return null;
            }
        }
    }
}
