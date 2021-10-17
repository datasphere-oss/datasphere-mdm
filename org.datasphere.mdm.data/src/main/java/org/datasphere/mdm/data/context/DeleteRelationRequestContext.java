

package org.datasphere.mdm.data.context;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.data.service.segments.relations.delete.RelationDeleteStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.core.context.DataRecordContext;
import org.datasphere.mdm.core.context.MutableValidityRangeContext;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Delete relation context.
 */
public class DeleteRelationRequestContext
    extends AbstractRelationIdentityContext
    implements
        DataRecordContext,
        RelationFromIdentityContext,
        ReadWriteTimelineContext<OriginRelation>,
        ReadWriteDataContext<OriginRelation>,
        ContainmentRelationContext<DeleteRequestContext>,
        MutableValidityRangeContext,
        BatchAwareContext,
        OperationTypeContext,
        AccessRightContext,
        SetupAwareContext,
        ReferenceRelationContext,
        DraftAwareContext,
        DraftIdResettingContext {
    /**
     * Internal use.
     */
    public enum DeleteRelationHint {
        HINT_PUBLISHING
    }
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 2751466540755521772L;
    /**
     * A possibly set draft id.
     */
    private Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * A record. It may be supplied to put some data before delete action.
     * This is to also be used for repeated draft updates before final delete record/period action.
     */
    private final transient DataRecord record;
    /**
     * Valid from for this set.
     */
    private Date validFrom;
    /**
     * Valid to for this set.
     */
    private Date validTo;
    /**
     * Name of the relation.
     */
    private final String relationName;
    /**
     * Hints.
     */
    private Map<DeleteRelationHint, Object> hints;
    /**
     * Constructor.
     */
    protected DeleteRelationRequestContext(DeleteRelationRequestContextBuilder b) {
        super(b);
        this.relationName = b.relationName;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.record = b.record;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;
        this.hints = b.hints;

        flags.set(DataContextFlags.FLAG_INACTIVATE_WIPE, b.wipe);
        flags.set(DataContextFlags.FLAG_INACTIVATE_PERIOD, b.inactivatePeriod);
        flags.set(DataContextFlags.FLAG_INACTIVATE_ORIGIN, b.inactivateOrigin);
        flags.set(DataContextFlags.FLAG_INACTIVATE_ETALON, b.inactivateEtalon);
        flags.set(DataContextFlags.FLAG_WORKFLOW_ACTION, b.workflowAction);
        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RelationDeleteStartExecutor.SEGMENT_ID;
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
     * {@inheritDoc}
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
     * @return the relationName
     */
    public String getRelationName() {
        return relationName;
    }

    /**
     * Whether the context has relation name and type set.
     * @return true if so, false otherwise
     */
    public boolean isValid() {
        return relationName != null;
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
     * @return the wipe
     */
    public boolean isWipe() {
        return flags.get(DataContextFlags.FLAG_INACTIVATE_WIPE);
    }

    @SuppressWarnings("unchecked")
    public<T> T getHint(DeleteRelationHint h) {
        return MapUtils.isEmpty(hints) ? null : (T) hints.get(h);
    }
    /**
     * Gets builder.
     * @return builder
     */
    public static DeleteRelationRequestContextBuilder builder() {
        return new DeleteRelationRequestContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Delete relation request bulder class.
     */
    public static class DeleteRelationRequestContextBuilder
        extends AbstractRelationIdentityContextBuilder<DeleteRelationRequestContextBuilder> {
        /**
         * Valid from for this set.
         */
        private Date validFrom;
        /**
         * Valid to for this set.
         */
        private Date validTo;
        /**
         * Name of the relation.
         */
        private String relationName;
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
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * Wipe flag.
         */
        private boolean wipe;
        /**
         * Skips process and tasks suspending, if set to true.
         */
        private boolean workflowAction;
        /**
         * The data record.
         */
        private DataRecord record;
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
        /**
         * Hints.
         */
        private Map<DeleteRelationHint, Object> hints;
        /**
         * Constructor.
         */
        protected DeleteRelationRequestContextBuilder() {
           super();
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public DeleteRelationRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public DeleteRelationRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param record the golden record to set
         */
        public DeleteRelationRequestContextBuilder record(DataRecord record) {
            this.record = record;
            return self();
        }
        /**
         * @param relationName the relationName to set
         * @return self
         */
        public DeleteRelationRequestContextBuilder relationName(String relationName) {
            this.relationName = relationName;
            return this;
        }

        /**
         * @param validFrom the validFrom to set
         */
        public DeleteRelationRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return this;
        }
        /**
         * @param validTo the validTo to set
         */
        public DeleteRelationRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return this;
        }
        /**
         * Inactivate period.
         * @param inactivatePeriod
         * @return
         */
        public DeleteRelationRequestContextBuilder inactivatePeriod(boolean inactivatePeriod) {
            this.inactivatePeriod = inactivatePeriod;
            return this;
        }
        /**
         * Inactivate origin flag.
         * @param inactivateOrigin
         * @return self
         */
        public DeleteRelationRequestContextBuilder inactivateOrigin(boolean inactivateOrigin) {
            this.inactivateOrigin = inactivateOrigin;
            return this;
        }
        /**
         * Inactivate etalon flag.
         * @param inactivateEtalon
         * return self
         */
        public DeleteRelationRequestContextBuilder inactivateEtalon(boolean inactivateEtalon) {
            this.inactivateEtalon = inactivateEtalon;
            return this;
        }

        /**
         * Wipe flag.
         * @param wipe the physical delete flag
         * @return self
         */
        public DeleteRelationRequestContextBuilder wipe(boolean wipe) {
            this.wipe = wipe;
            return this;
        }
        /**
         * @param workflowAction workflow action/rollback state signal
         * @return
         */
        public DeleteRelationRequestContextBuilder workflowAction(boolean workflowAction) {
            this.workflowAction = workflowAction;
            return this;
        }
        /**
         * @param batchUpsert the flag
         * @return self
         */
        public DeleteRelationRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return this;
        }
        /**
         * Set restore hint.
         * @param h the hint
         * @param value the value
         */
        public DeleteRelationRequestContextBuilder hint(DeleteRelationHint h, Object value) {
            if (Objects.nonNull(h)) {
                if (Objects.isNull(hints)) {
                    hints = new EnumMap<>(DeleteRelationHint.class);
                }

                hints.put(h, value);
            }
            return self();
        }
        /**
         * Builder method.
         * @return context
         */
        @Override
        public DeleteRelationRequestContext build() {
            return new DeleteRelationRequestContext(this);
        }
    }
}
