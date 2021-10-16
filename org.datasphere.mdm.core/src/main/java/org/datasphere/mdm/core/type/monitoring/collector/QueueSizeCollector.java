
package org.datasphere.mdm.core.type.monitoring.collector;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

public class QueueSizeCollector extends Collector {

    private final Queue<?> queue;

    private final String metricName;
    private final String metricHelpText;

    public static void createAndRegister(final Queue<?> queue, final String metricName, final String metricHelpText) {
        new QueueSizeCollector(queue, metricName, metricHelpText).register();
    }

    public QueueSizeCollector(final Queue<?> queue, final String metricName, final String metricHelpText) {
        this.queue = queue;
        this.metricName = metricName;
        this.metricHelpText = metricHelpText;
    }

    @Override
    public List<MetricFamilySamples> collect() {
        return Collections.singletonList(new GaugeMetricFamily(metricName, metricHelpText, queue.size()));
    }
}
