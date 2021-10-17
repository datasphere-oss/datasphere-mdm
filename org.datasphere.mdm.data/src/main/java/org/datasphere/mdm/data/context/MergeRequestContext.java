

/**
 *
 */
package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.datasphere.mdm.data.service.segments.records.merge.RecordMergeStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Merge request context.
 */
public class MergeRequestContext
    extends AbstractRecordIdentityContext
    implements
        ReadWriteTimelineContext<OriginRecord>,
        ReadWriteDataContext<OriginRecord>,
        MergeDuplicatesContext<OriginRecord>,
        BatchAwareContext,
        SetupAwareContext,
        AccessRightContext,
        ExtendedAttributesAwareContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -1140437951331151093L;
    /**
     * Duplicates list.
     */
    private final List<AbstractRecordIdentityContext> duplicates;
    /**
     * Is this merge an auto merge or a manual one.
     */
    private final boolean manual;
    /**
     * Rule id.
     */
    private final Integer ruleId;

    private final boolean upRecordsToContext;

    private final Integer shardNumber;

    private final boolean withTranstition;

    /**
     * Constructor.
     */
    protected MergeRequestContext(MergeRequestContextBuilder b) {
        super(b);
        this.ruleId = b.ruleId;
        this.duplicates = b.duplicates;
        this.manual = b.manual;
        this.upRecordsToContext = b.upRecordsToContext;
        this.shardNumber = b.shardNumber;
        this.withTranstition = b.withTransition;

        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
    }
    /**
     * @return the duplicates
     */
    public List<AbstractRecordIdentityContext> getDuplicates() {
        return duplicates;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RecordMergeStartExecutor.SEGMENT_ID;
    }
    /**
     * @return the ruleId
     */
    public Integer getRuleId() {
        return ruleId;
    }
    /**
     * @return the manual
     */
    public boolean isManual() {
        return manual;
    }

    /**
     * @return the with transition flag
     */
    public boolean isWithTranstition() {
        return withTranstition;
    }
    /**
     * @return the bypassExtensionPoints
     */
    public boolean isUpRecordsToContext() {
        return upRecordsToContext;
    }
    /**
     * @return the shardNumber
     */
    public Integer getShardNumber() {
        return shardNumber;
    }
    /**
     * Builder shorthand.
     * @return builder
     */
    public static MergeRequestContextBuilder builder() {
        return new MergeRequestContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Builder for the context.
     */
    public static class MergeRequestContextBuilder extends AbstractRecordIdentityContextBuilder<MergeRequestContextBuilder> {
        /**
         * Duplicates list.
         */
        private List<AbstractRecordIdentityContext> duplicates;
        /**
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * Is this merge an auto merge or a manual one.
         */
        private boolean manual;
        /**
         * Bypass extension points during merge.
         */
        private boolean upRecordsToContext = true;
        /**
         * Shard number
         */
        private Integer shardNumber;

        private boolean withTransition = true;

        private Integer ruleId;

        protected MergeRequestContextBuilder() {
            super();
        }

        /**
         * @param ruleId the ruleId to set
         */
        public MergeRequestContextBuilder ruleId(Integer ruleId) {
            this.ruleId = ruleId;
            return this;
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public MergeRequestContextBuilder duplicates(Collection<? extends AbstractRecordIdentityContext> duplicates) {
            if (CollectionUtils.isNotEmpty(duplicates)) {
                duplicates(duplicates.toArray(AbstractRecordIdentityContext[]::new));
            }
            return this;
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public MergeRequestContextBuilder duplicates(AbstractRecordIdentityContext... duplicates) {
            if (ArrayUtils.isNotEmpty(duplicates)) {
                for (int i = 0; i < duplicates.length; i++) {
                    duplicate(duplicates[i]);
                }
            }
            return this;
        }
        /**
         * Sets the duplicates list.
         * @param duplicates the list to set
         * @return self
         */
        public MergeRequestContextBuilder duplicate(AbstractRecordIdentityContext duplicate) {
            if (Objects.nonNull(duplicate)) {
                if (Objects.isNull(this.duplicates)) {
                    this.duplicates = new ArrayList<>();
                }

                this.duplicates.add(duplicate);
            }
            return this;
        }
        /**
         * @param batchUpsert the flag
         * @return self
         */
        public MergeRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return this;
        }
        /**
         * Is this merge an auto merge or a manual one.
         * @param manual merge type
         * @return self
         */
        public MergeRequestContextBuilder manual(boolean manual) {
            this.manual= manual;
            return this;
        }
        /**
         * @param upRecordsToContext up records to context or note
         */
        public MergeRequestContextBuilder upRecordsToContext(boolean upRecordsToContext) {
            this.upRecordsToContext = upRecordsToContext;
            return this;
        }
        /**
         * @param shardNumber shard Number
         */
        public MergeRequestContextBuilder shardNumber(Integer shardNumber) {
            this.shardNumber = shardNumber;
            return this;
        }
        /**
         * @param withTransition with transtition
         */
        public MergeRequestContextBuilder withTransition(boolean withTransition) {
            this.withTransition = withTransition;
            return this;
        }
        /**
         * The build method.
         * @return new merge context
         */
        @Override
        public MergeRequestContext build() {
            return new MergeRequestContext(this);
        }
    }
}
