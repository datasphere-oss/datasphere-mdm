

package org.datasphere.mdm.data.context;

import java.util.Date;

import org.datasphere.mdm.data.service.segments.records.delete.RecordDeleteStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.core.context.DataRecordContext;
import org.datasphere.mdm.core.context.MutableValidityRangeContext;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;
import org.datasphere.mdm.system.type.pipeline.PipelineInput;

/**
 * @author Mikhail Mikhailov
 * Delete context.
 */
public class DeleteRequestContext
    extends AbstractRecordIdentityContext
    implements
        DataRecordContext,
        PipelineInput,
        ExternalIdResettingContext,
        MutableValidityRangeContext,
        OperationTypeContext,
        AccessRightContext,
        BatchAwareContext,
        SetupAwareContext,
        DraftAwareContext,
        DraftIdResettingContext,
        ReadWriteTimelineContext<OriginRecord>,
        ReadWriteDataContext<OriginRecord> {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -5350865249523055692L;
    /**
     * A record. It may be supplied to put some data before delete action.
     * This is to also be used for repeated draft updates before final delete record/period action.
     */
    private final transient DataRecord record;
    /**
     * A possibly set draft id.
     */
    private Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * Version (de)activation attribute validFrom.
     */
    private Date validFrom;
    /**
     * Version (de)activation attribute validTo.
     */
    private Date validTo;
    /**
     * Status that record had before delete.
     */
    private  RecordStatus previousStatus;

    /**
     * Constructor.
     */
    protected DeleteRequestContext(DeleteRequestContextBuilder b) {
        super(b);

        this.record = b.record;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.previousStatus = b.previousStatus;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;

        flags.set(DataContextFlags.FLAG_INACTIVATE_CASCADE, b.cascade);
        flags.set(DataContextFlags.FLAG_INACTIVATE_WIPE, b.wipe);
        flags.set(DataContextFlags.FLAG_INACTIVATE_PERIOD, b.inactivatePeriod);
        flags.set(DataContextFlags.FLAG_INACTIVATE_ORIGIN, b.inactivateOrigin);
        flags.set(DataContextFlags.FLAG_INACTIVATE_ETALON, b.inactivateEtalon);
        flags.set(DataContextFlags.FLAG_WORKFLOW_ACTION, b.workflowAction);
        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
    }

    @Override
    public String getStartTypeId() {
        return RecordDeleteStartExecutor.SEGMENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getDraftId() {
        return draftId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDraftId(Long draftId) {
        this.draftId = draftId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getParentDraftId() {
        return parentDraftId;
    }

    /**
     * @return the record
     */
    @Override
    public DataRecord getRecord() {
        return record;
    }

    /**
     * @return the validFrom
     */
    @Override
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * @return the validTo
     */
    @Override
    public Date getValidTo() {
        return validTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValidFrom(Date from) {
        this.validFrom = from;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValidTo(Date to) {
        this.validTo = to;
    }

    /**
     * @return the previousStatus
     */
    public RecordStatus getPreviousStatus() {
        return previousStatus;
    }

    /**
     * @param previousStatus the previousStatus validTo set
     */
    public void setPreviousStatus(RecordStatus previousStatus) {
        this.previousStatus = previousStatus;
    }

    /**
     * @return the cascade
     */
    public boolean isCascade() {
        return flags.get(DataContextFlags.FLAG_INACTIVATE_CASCADE);
    }

    /**
     * @return the wipe
     */
    public boolean isWipe() {
        return flags.get(DataContextFlags.FLAG_INACTIVATE_WIPE);
    }

    /**
     * @return the inactivatePeriod
     */
    public boolean isInactivatePeriod() {
        return flags.get(DataContextFlags.FLAG_INACTIVATE_PERIOD);
    }

    /**
     * @return the inactivateOrigin
     */
    public boolean isInactivateOrigin() {
        return flags.get(DataContextFlags.FLAG_INACTIVATE_ORIGIN);
    }

    /**
     * @return the inactivateEtalon
     */
    public boolean isInactivateEtalon() {
        return flags.get(DataContextFlags.FLAG_INACTIVATE_ETALON);
    }

    /**
     * @return the skipSuspendWorkflow
     */
    public boolean isWorkflowAction() {
        return flags.get(DataContextFlags.FLAG_WORKFLOW_ACTION);
    }

    /**
     * @return suppressAudit
     */
    public boolean isSuppressAudit() {
        return flags.get(DataContextFlags.FLAG_SUPPRESS_AUDIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExternalId(String externalId) {
        this.externalId = this.externalId != null
                ? ExternalId.of(externalId, this.externalId.getEntityName(), this.externalId.getSourceSystem())
                : null;
    }

    /**
     * Builder shorthand.
     * @return builder
     */
    public static DeleteRequestContextBuilder builder() {
        return new DeleteRequestContextBuilder();
    }

    /**
     * @author Mikhail Mikhailov
     * Builder
     */
    public static class DeleteRequestContextBuilder extends AbstractRecordIdentityContextBuilder<DeleteRequestContextBuilder> {
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
        /**
         * Version (de)activation attribute validFrom.
         */
        private Date validFrom;
        /**
         * Version (de)activation attribute validTo.
         */
        private Date validTo;
        /**
         * Status that record had before delete.
         */
        private RecordStatus previousStatus;
        /**
         * (Soft) deletes golden and origin records.
         */
        private boolean cascade;
        /**
         * Physically remove data validFrom the storage.
         */
        private boolean wipe;
        /**
         * A version for inactive period should be put above all.
         */
        private boolean inactivatePeriod;
        /**
         * Inactivate origin flag.
         */
        private boolean inactivateOrigin;
        /**
         * Inactivate etalon flag.
         */
        private boolean inactivateEtalon;
        /**
         * Skips process and tasks suspending, if set to true.
         */
        private boolean workflowAction;

        /**
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * The data record.
         */
        private DataRecord record;
        /**
         * Constructor.
         */
        protected DeleteRequestContextBuilder() {
            super();
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public DeleteRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public DeleteRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param record the golden record to set
         */
        public DeleteRequestContextBuilder record(DataRecord record) {
            this.record = record;
            return self();
        }
        /**
         * @param from the validFrom validTo set
         */
        public DeleteRequestContextBuilder validFrom(Date from) {
            this.validFrom = from;
            return this;
        }

        /**
         * @param to the validTo validTo set
         */
        public DeleteRequestContextBuilder validTo(Date to) {
            this.validTo = to;
            return this;
        }

        /**
         * @param cascade the cascade validTo set
         */
        public DeleteRequestContextBuilder cascade(boolean cascade) {
            this.cascade = cascade;
            return this;
        }
        /**
         * @param previousStatus the previousStatus validTo set
         */
        public DeleteRequestContextBuilder previousStatus(RecordStatus previousStatus) {
            this.previousStatus = previousStatus;
            return this;
        }
        /**
         * Wipe flag.
         * @param wipe the physical delete flag
         * @return self
         */
        public DeleteRequestContextBuilder wipe(boolean wipe) {
            this.wipe = wipe;
            return this;
        }
        /**
         * Inactivate period.
         * @param inactivatePeriod
         * @return
         */
        public DeleteRequestContextBuilder inactivatePeriod(boolean inactivatePeriod) {
            this.inactivatePeriod = inactivatePeriod;
            return this;
        }
        /**
         * Inactivate origin flag.
         * @param inactivateOrigin
         * @return self
         */
        public DeleteRequestContextBuilder inactivateOrigin(boolean inactivateOrigin) {
            this.inactivateOrigin = inactivateOrigin;
            return this;
        }
        /**
         * Inactivate etalon flag.
         * @param inactivateEtalon
         * return self
         */
        public DeleteRequestContextBuilder inactivateEtalon(boolean inactivateEtalon) {
            this.inactivateEtalon = inactivateEtalon;
            return this;
        }
        /**
         * @param workflowAction workflow action/rollback state signal
         * @return
         */
        public DeleteRequestContextBuilder workflowAction(boolean workflowAction) {
            this.workflowAction = workflowAction;
            return this;
        }

        /**
         * @param batchUpsert the flag
         * @return self
         */
        public DeleteRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return this;
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public DeleteRequestContext build() {
            return new DeleteRequestContext(this);
        }
    }
}
