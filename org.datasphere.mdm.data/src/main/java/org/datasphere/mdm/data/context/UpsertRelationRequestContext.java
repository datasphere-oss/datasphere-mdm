

package org.datasphere.mdm.data.context;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.data.service.segments.relations.upsert.RelationUpsertStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.core.context.DataRecordContext;
import org.datasphere.mdm.core.context.MutableValidityRangeContext;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.keys.ReferenceAliasKey;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Single relation upsert request context.
 */
public class UpsertRelationRequestContext
    extends AbstractRelationIdentityContext
    implements
        DataRecordContext,
        RelationFromIdentityContext,
        ReadWriteTimelineContext<OriginRelation>,
        ReadWriteDataContext<OriginRelation>,
        ContainmentRelationContext<UpsertRequestContext>,
        MutableValidityRangeContext,
        UpsertIndicatorContext,
        BatchAwareContext,
        RecalculateTimelineAwareContext,
        SetupAwareContext,
        OperationTypeContext,
        AccessRightContext,
        ReferenceRelationContext,
        DraftAwareContext,
        DraftIdResettingContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -2042264082583817129L;
    /**
     * Internal use.
     */
    public enum UpsertRelationHint {
        HINT_PUBLISHING,
        HINT_ETALON_ID,
        HINT_PROCESSING_SIDE
    }
    /**
     * Hints.
     */
    private final transient Map<UpsertRelationHint, Object> hints;
    /**
     * A possibly set draft id.
     */
    private Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
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
     * Relations to.
     */
    private final transient DataRecord record;
    /**
     * Reference alias key
     */
    private final transient ReferenceAliasKey referenceAliasKey;
    /**
     * Constructor.
     */
    protected UpsertRelationRequestContext(UpsertRelationRequestContextBuilder b) {
        super(b);
        this.relationName = b.relationName;
        this.record = b.record;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.referenceAliasKey = b.referenceAliasKey;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;
        this.hints = b.hints;

        flags.set(DataContextFlags.FLAG_INCLUDE_DRAFTS, b.includeDraftVersions);
        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
        flags.set(DataContextFlags.FLAG_EMPTY_STORAGE, b.emptyStorage);
        flags.set(DataContextFlags.FLAG_RECALCULATE_WHOLE_TIMELINE, b.recalculateWholeTimeline);
        flags.set(DataContextFlags.FLAG_SKIP_INDEX_DROP, b.skipIndexDrop);
        flags.set(DataContextFlags.FLAG_SUPPRESS_WORKFLOW, b.suppressWorkflow);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RelationUpsertStartExecutor.SEGMENT_ID;
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

    @SuppressWarnings("unchecked")
    public<T> T getHint(UpsertRelationHint h) {
        return MapUtils.isEmpty(hints) ? null : (T) hints.get(h);
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
        this.validFrom =from;
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
     * @return the relation
     */
    @Override
    public DataRecord getRecord() {
        return record;
    }

    /**
     *
     * @return reference alias key
     */
    public ReferenceAliasKey getReferenceAliasKey() {
        return referenceAliasKey;
    }

    /**
     * @return the includeDraftVersions
     */
    public boolean isIncludeDraftVersions() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_DRAFTS);
    }

    /**
     * @return true, if this record not saved in storage before
     */
    public boolean isEmptyStorage() {
        return flags.get(DataContextFlags.FLAG_EMPTY_STORAGE);
    }

    /**
     * Skips drop operation upon index info creation.
     * @return
     */
    public boolean isSkipIndexDrop() {
        return flags.get(DataContextFlags.FLAG_SKIP_INDEX_DROP);
    }

    /**
     * @return suppressWorkflow
     */
    public boolean isSuppressWorkflow() {
        return flags.get(DataContextFlags.FLAG_SUPPRESS_WORKFLOW);
    }
    /**
     * Creates new builder instance.
     * @return new builder instance.
     */
    public static UpsertRelationRequestContextBuilder builder() {
        return new UpsertRelationRequestContextBuilder();
    }

    /**
     * @author Mikhail Mikhailov
     * Upsert relation request bulder class.
     */
    public static class UpsertRelationRequestContextBuilder
        extends AbstractRelationIdentityContextBuilder<UpsertRelationRequestContextBuilder> {
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
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
         * Relations to.
         */
        private DataRecord record;
        /**
         * Reference alias key
         */
        private ReferenceAliasKey referenceAliasKey;
        /**
         * Include draft versions into various calculations or not (approver view).
         */
        private boolean includeDraftVersions;
        /**
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * This context is participating in initial load process. Skips relation key resolution.
         */
        private boolean emptyStorage;
        /**
         * Tells the etalon calculation routine,
         * that the whole time line must be completely recalculated.
         */
        private boolean recalculateWholeTimeline;
        /**
         * Skip or perform index drop. This might be true for reindex job, which did explicit cleanup before run.
         */
        private boolean skipIndexDrop;
        /**
         * suppress Workflow
         */
        private boolean suppressWorkflow;
        /**
         * Hints.
         */
        private Map<UpsertRelationHint, Object> hints;
        /**
         * Constructor.
         */
        protected UpsertRelationRequestContextBuilder() {
           super();
        }

        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public UpsertRelationRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public UpsertRelationRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }

        /**
         * @return the relation
         */
        public UpsertRelationRequestContextBuilder record(DataRecord record) {
            this.record = record;
            return this;
        }

        /**
         * @param validFrom the validFrom to set
         */
        public UpsertRelationRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        /**
         * @param validTo the validTo to set
         */
        public UpsertRelationRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return this;
        }

        /**
         * Sets relation name.
         * @param relationName the relation name
         * @return self
         */
        public UpsertRelationRequestContextBuilder relationName(String relationName) {
            this.relationName = relationName;
            return this;
        }

        /**
         * Set reference alias key
         * @param referenceAliasKey -
         * @return self
         */
        public UpsertRelationRequestContextBuilder referenceAliasKey(ReferenceAliasKey referenceAliasKey) {
            this.referenceAliasKey = referenceAliasKey;
            return this;
        }

        /**
         * @param includeDraftVersions include draft versions or not
         * @return self
         */
        public UpsertRelationRequestContextBuilder includeDraftVersions(boolean includeDraftVersions) {
            this.includeDraftVersions = includeDraftVersions;
            return this;
        }
        /**
         * @param batchUpsert the flag
         * @return self
         */
        public UpsertRelationRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return this;
        }
        /**
         * @param emptyStorage the flag
         * @return self
         */
        public UpsertRelationRequestContextBuilder emptyStorage(boolean emptyStorage) {
            this.emptyStorage = emptyStorage;
            return this;
        }
        /**
         * Re-index whole timeline _WITHOUT_ any save actions.
         * @param recalculateWholeTimeline the flag
         * @return self
         */
        public UpsertRelationRequestContextBuilder recalculateWholeTimeline(boolean recalculateWholeTimeline) {
            this.recalculateWholeTimeline = recalculateWholeTimeline;
            return this;
        }
        /**
         *
         * @param skipIndexDrop - skip index drop or not
         * @return self
         */
        public UpsertRelationRequestContextBuilder skipIndexDrop(boolean skipIndexDrop) {
            this.skipIndexDrop = skipIndexDrop;
            return this;
        }

        public UpsertRelationRequestContextBuilder suppressWorkflow(boolean suppressWorkflow){
            this.suppressWorkflow = suppressWorkflow;
            return this;
        }
        /**
         * Set upsert hint.
         * @param h the hint
         * @param value the value
         */
        public UpsertRelationRequestContextBuilder hint(UpsertRelationHint h, Object value) {
            if (Objects.nonNull(h)) {
                if (Objects.isNull(hints)) {
                    hints = new EnumMap<>(UpsertRelationHint.class);
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
        public UpsertRelationRequestContext build() {
            return new UpsertRelationRequestContext(this);
        }
    }
}
