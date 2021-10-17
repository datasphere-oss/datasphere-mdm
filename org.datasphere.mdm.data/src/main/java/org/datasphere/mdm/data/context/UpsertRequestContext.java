
package org.datasphere.mdm.data.context;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.data.service.segments.records.upsert.RecordUpsertStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.core.context.DataRecordContext;
import org.datasphere.mdm.core.context.MutableValidityRangeContext;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Record upsert.
 */
public class UpsertRequestContext
    extends AbstractRecordIdentityContext
    implements
        DataRecordContext,
        ExternalIdResettingContext,
        MutableValidityRangeContext,
        ReadWriteTimelineContext<OriginRecord>,
        ReadWriteDataContext<OriginRecord>,
        UpsertIndicatorContext,
        OperationTypeContext,
        AccessRightContext,
        SetupAwareContext,
        BatchAwareContext,
        RecalculateTimelineAwareContext,
        DraftAwareContext,
        DraftIdResettingContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = 6651928422821780602L;
    /**
     * Internal use.
     */
    public enum UpsertRecordHint {
        HINT_PUBLISHING,
        HINT_ETALON_ID
    }
    /**
     * Hints.
     */
    private final transient Map<UpsertRecordHint, Object> hints;
    /**
     * A possibly set draft id.
     */
    private Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * Golden record.
     */
    private final transient DataRecord data;
    /**
     * Last update date to use (optional).
     */
    private final Date lastUpdate;
    /**
     * Set range from.
     */
    private Date validFrom;
    /**
     * Set range to.
     */
    private Date validTo;
    /**
     * FIXME: Kill this
     */
    private final RecordStatus originStatus;
    /**
     * Cpde attribute aliases.
     */
    private final transient Collection<CodeAttributeAlias> codeAttributeAliases;
    /**
     * The box key.
     */
    protected String boxKey;
    /**
     * Constructor.
     */
    protected UpsertRequestContext(UpsertRequestContextBuilder b) {
        super(b);

        this.data = b.data;
        this.lastUpdate = b.lastUpdate;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.originStatus = b.originStatus;
        this.codeAttributeAliases = b.codeAttributeAliases;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;
        this.hints = b.hints;

        flags.set(DataContextFlags.FLAG_IS_ENRICHMENT, b.enrichment);
        flags.set(DataContextFlags.FLAG_SKIP_DQ, b.skipCleanse);
        flags.set(DataContextFlags.FLAG_RECALCULATE_WHOLE_TIMELINE, b.recalculateWholeTimeline);
        flags.set(DataContextFlags.FLAG_IS_RECORD_RESTORE, b.isRestore);
        flags.set(DataContextFlags.FLAG_IS_PERIOD_RESTORE, b.isPeriodRestore);
        flags.set(DataContextFlags.FLAG_INCLUDE_DRAFTS, b.includeDraftVersions);
        flags.set(DataContextFlags.FLAG_MERGE_WITH_PREVIOUS_VERSION, b.mergeWithPreviousVersion);
        flags.set(DataContextFlags.FLAG_SKIP_INDEX_DROP, b.skipIndexDrop);
        flags.set(DataContextFlags.FLAG_SKIP_MATCHING_PREPROCESSING, b.skipMatchingPreprocessing);
        flags.set(DataContextFlags.FLAG_SUPPRESS_AUDIT, b.suppressAudit);
        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
        flags.set(DataContextFlags.FLAG_EMPTY_STORAGE, b.emptyStorage);
        flags.set(DataContextFlags.FLAG_SKIP_CONSISTENCY_CHECKS, b.skipConsistencyChecks);
        flags.set(DataContextFlags.FLAG_SKIP_MATCHING, b.skipMatching);
        flags.set(DataContextFlags.FLAG_SUPPRESS_WORKFLOW, b.suppressWorkflow);
        flags.set(DataContextFlags.FLAG_IS_APPLY_DRAFT, b.isApplyDraft);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RecordUpsertStartExecutor.SEGMENT_ID;
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
        return data;
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
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
     * @return the skipCleanse
     */
    public boolean isSkipCleanse() {
        return flags.get(DataContextFlags.FLAG_SKIP_DQ);
    }

    /**
     * @return the skipConsistencyChecks
     */
    public boolean isSkipConsistencyChecks() {
        return flags.get(DataContextFlags.FLAG_SKIP_CONSISTENCY_CHECKS);
    }

    /**
     * @return the skipMatching preprocessing
     */
    public boolean isSkipMatchingPreprocessing() {
        return flags.get(DataContextFlags.FLAG_SKIP_MATCHING_PREPROCESSING);
    }

    /**
     * @return the skipMatching
     */
    public boolean isSkipMatching() {
        return flags.get(DataContextFlags.FLAG_SKIP_MATCHING);
    }

    /**
     * @return suppressAudit
     */
    public boolean isSuppressAudit() {
        return flags.get(DataContextFlags.FLAG_SUPPRESS_AUDIT);
    }

    /**
     * @return define that is restore request.
     */
    public boolean isRecordRestore() {
        return flags.get(DataContextFlags.FLAG_IS_RECORD_RESTORE);
    }

    /**
     * @return define that is period restore request.
     */
    public boolean isPeriodRestore() {
        return flags.get(DataContextFlags.FLAG_IS_PERIOD_RESTORE);
    }
    /**
     * @return true, if this context is a part of initial load process
     */
    public boolean isEmptyStorage() {
        return flags.get(DataContextFlags.FLAG_EMPTY_STORAGE);
    }

    /**
     * @return true, if need force suppress workflow, else false
     */
    public boolean isSuppressWorkflow() {
        return flags.get(DataContextFlags.FLAG_SUPPRESS_WORKFLOW);
    }

    /**
     * @return true, if is publishing context, else not
     */
    public boolean isApplyDraft() {
        return flags.get(DataContextFlags.FLAG_IS_APPLY_DRAFT);
    }

    /**
     * Merge with previous version?
     * @return true if so, false otherwise
     */
    public boolean isMergeWithPreviousVersion() {
        return flags.get(DataContextFlags.FLAG_MERGE_WITH_PREVIOUS_VERSION);
    }

    /**
     * @return the includeDraftVersions
     */
    public boolean isIncludeDraftVersions() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_DRAFTS);
    }

    /**
     * Skips drop operation upon index info creation.
     * @return
     */
    public boolean isSkipIndexDrop() {
        return flags.get(DataContextFlags.FLAG_SKIP_INDEX_DROP);
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

    @SuppressWarnings("unchecked")
    public<T> T getHint(UpsertRecordHint h) {
        return MapUtils.isEmpty(hints) ? null : (T) hints.get(h);
    }

    /**
     * @return the codeAttributeAliases
     */
    public Collection<CodeAttributeAlias> getCodeAttributeAliases() {
        return codeAttributeAliases == null ? Collections.emptyList() : codeAttributeAliases;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEtalonRecordKey() {
        return !flags.get(DataContextFlags.FLAG_IS_ENRICHMENT) && ExternalIdResettingContext.super.isEtalonRecordKey();
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public boolean isOriginRecordKey() {
        return !flags.get(DataContextFlags.FLAG_IS_ENRICHMENT) && ExternalIdResettingContext.super.isOriginRecordKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOriginExternalId() {
        return !flags.get(DataContextFlags.FLAG_IS_ENRICHMENT) && ExternalIdResettingContext.super.isOriginExternalId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnrichmentKey() {
        return flags.get(DataContextFlags.FLAG_IS_ENRICHMENT)
                && ExternalIdResettingContext.super.isEtalonRecordKey()
                && ExternalIdResettingContext.super.isOriginExternalId();
    }

    /**
     * Is the context a valid upsert golden record context.
     * @return true, if the context is a valid upsert golden record context
     */
    public boolean isEtalon() {
        return hasData() && isEtalonRecordKey();
    }

    /**
     * Is the context a valid upsert origin record context.
     * @return true, if the context is a valid upsert origin record context
     */
    public boolean isOrigin() {
        return hasData() && (isOriginExternalId() || isOriginRecordKey());
    }

    /**
     * Is the context a valid upsert origin record context.
     * @return true, if the context is a valid upsert origin record context
     */
    public boolean isEnrichment() {
        return hasData() && isEnrichmentKey();
    }

    /**
     * @return the originStatus
     */
    public RecordStatus getOriginStatus() {
        return originStatus;
    }

    /**
     * Builder shortcut.
     * @return builder
     */
    public static UpsertRequestContextBuilder builder() {
        return new UpsertRequestContextBuilder();
    }

    /**
     * Re-packaging builder shorthand.
     * @return builder
     */
    public static UpsertRequestContextBuilder builder(UpsertRequestContext other) {
        return new UpsertRequestContextBuilder(other);
    }

    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class UpsertRequestContextBuilder extends AbstractRecordIdentityContextBuilder<UpsertRequestContextBuilder> {
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
        /**
         * The data record.
         */
        private DataRecord data;
        /**
         * Enrichment record.
         */
        private boolean enrichment;
        /**
         * Last update date to use (optional).
         */
        private Date lastUpdate;
        /**
         * Skip cleanse functions.
         */
        private boolean skipCleanse;
        /**
         * Skip consistency checks, performed by DQ.
         */
        private boolean skipConsistencyChecks;
        /**
         * Skip matching preprocessing part of the upsert.
         */
        private boolean skipMatchingPreprocessing;
        /**
         * Suppress audit upon upsert.
         */
        private boolean suppressAudit;
        /**
         * Tells the etalon calculation routine,
         * that the whole time line must be completely recalculated.
         */
        private boolean recalculateWholeTimeline;
        /**
         * Set range from.
         */
        private Date validFrom;
        /**
         * Set range to.
         */
        private Date validTo;
        /**
         * Origin status to put.
         */
        private RecordStatus originStatus;
        /**
         * define that is restore request.
         */
        private boolean isRestore;
        /**
         * define that is restore period request.
         */
        private boolean isPeriodRestore;
        /**
         * Include draft versions into various calculations or not (approver view).
         */
        private boolean includeDraftVersions;

        /**
         * merge with previous version
         */
        private boolean mergeWithPreviousVersion;
        /**
         * Skip or perform index drop. This might be true for reindex job, which did explicit cleanup before run.
         */
        private boolean skipIndexDrop;
        /**
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * This context is participating in initial load process. Skips relation key resolution.
         */
        private boolean emptyStorage;
        /**
         * Cpde attribute aliases.
         */
        private Collection<CodeAttributeAlias> codeAttributeAliases;
        /**
         * Skip matching main phase part of the upsert.
         */
        private boolean skipMatching;
        /**
         * force Skip workflow or not
         */
        private boolean suppressWorkflow;
        /**
         * is apply draft context indication
         */
        private boolean isApplyDraft;
        /**
         * Hints.
         */
        private Map<UpsertRecordHint, Object> hints;
        /**
         * Constructor.
         */
        protected UpsertRequestContextBuilder() {
            super();
        }
        /**
         * Constructor.
         */
        protected UpsertRequestContextBuilder(UpsertRequestContext other) {
            super(other);
            this.data = other.data;
            this.lastUpdate = other.lastUpdate;
            this.validFrom = other.validFrom;
            this.validTo = other.validTo;
            this.originStatus = other.originStatus;
            this.codeAttributeAliases = other.codeAttributeAliases;

            this.enrichment = other.flags.get(DataContextFlags.FLAG_IS_ENRICHMENT);
            this.skipCleanse = other.flags.get(DataContextFlags.FLAG_SKIP_DQ);
            this.skipConsistencyChecks = other.flags.get(DataContextFlags.FLAG_SKIP_CONSISTENCY_CHECKS);
            this.recalculateWholeTimeline = other.flags.get(DataContextFlags.FLAG_RECALCULATE_WHOLE_TIMELINE);
            this.isRestore = other.flags.get(DataContextFlags.FLAG_IS_RECORD_RESTORE);
            this.isPeriodRestore = other.flags.get(DataContextFlags.FLAG_IS_PERIOD_RESTORE);
            this.includeDraftVersions = other.flags.get(DataContextFlags.FLAG_INCLUDE_DRAFTS);
            this.mergeWithPreviousVersion = other.flags.get(DataContextFlags.FLAG_MERGE_WITH_PREVIOUS_VERSION);
            this.skipMatchingPreprocessing = other.flags.get(DataContextFlags.FLAG_SKIP_MATCHING_PREPROCESSING);
            this.suppressAudit = other.flags.get(DataContextFlags.FLAG_SUPPRESS_AUDIT);
            this.batchOperation = other.flags.get(DataContextFlags.FLAG_BATCH_OPERATION);
            this.emptyStorage = other.flags.get(DataContextFlags.FLAG_EMPTY_STORAGE);
            this.skipMatching = other.flags.get(DataContextFlags.FLAG_SKIP_MATCHING);
            this.suppressWorkflow = other.flags.get(DataContextFlags.FLAG_SUPPRESS_WORKFLOW);
            this.isApplyDraft = other.flags.get(DataContextFlags.FLAG_IS_APPLY_DRAFT);
        }

        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public UpsertRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public UpsertRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }

        /**
         * @param data the data record to set
         */
        public UpsertRequestContextBuilder record(DataRecord data) {
            this.data = data;
            return self();
        }

        /**
         * @param enrichment the enrichment
         */
        public UpsertRequestContextBuilder enrichment(boolean enrichment) {
            this.enrichment = enrichment;
            return self();
        }
        /**
         * @param lastUpdate the last update to set
         */
        public UpsertRequestContextBuilder lastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return self();
        }

        /**
         * @param skipCleanse skip cleanse or not
         */
        public UpsertRequestContextBuilder skipCleanse(boolean skipCleanse) {
            this.skipCleanse = skipCleanse;
            return self();
        }

        /**
         * @param skipConsistencyChecks skip consistency checks or not
         */
        public UpsertRequestContextBuilder skipConsistencyChecks(boolean skipConsistencyChecks) {
            this.skipConsistencyChecks = skipConsistencyChecks;
            return self();
        }


        /**
         * @param skipMatchingPreprocessing skip matching preprocessing or not
         */
        public UpsertRequestContextBuilder skipMatchingPreprocessing(boolean skipMatchingPreprocessing) {
            this.skipMatchingPreprocessing = skipMatchingPreprocessing;
            return self();
        }

        /**
         * Re-index whole timeline _WITHOUT_ any save actions.
         * @param recalculateWholeTimeline the flag
         * @return self
         */
        public UpsertRequestContextBuilder recalculateWholeTimeline(boolean recalculateWholeTimeline) {
            this.recalculateWholeTimeline = recalculateWholeTimeline;
            return self();
        }

        /**
         * Sppress audt events emission during upsert.
         * @param suppressAudit flag
         * @return self
         */
        public UpsertRequestContextBuilder suppressAudit(boolean suppressAudit) {
            this.suppressAudit = suppressAudit;
            return self();
        }

        /**
         * @param batchUpsert the flag
         * @return self
         */
        public UpsertRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return self();
        }

        /**
         * @param emptyStorage the flag
         * @return self
         */
        public UpsertRequestContextBuilder emptyStorage(boolean emptyStorage) {
            this.emptyStorage = emptyStorage;
            return self();
        }

        /**
         * @param validFrom the range from to set
         */
        public UpsertRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return self();
        }
        /**
         * @param validTo the range to to set
         */
        public UpsertRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return self();
        }
        /**
         * @param originStatus the originStatus to to set
         */
        public UpsertRequestContextBuilder originStatus(RecordStatus originStatus) {
            this.originStatus = originStatus;
            return self();
        }
        /**
         * @param includeDraftVersions include draft versions or not
         * @return self
         */
        public UpsertRequestContextBuilder includeDraftVersions(boolean includeDraftVersions) {
            this.includeDraftVersions = includeDraftVersions;
            return self();
        }

        /**
         * define that is restore request.
         * @param recordRestore
         * @return self
         */
        public UpsertRequestContextBuilder recordRestore(boolean recordRestore) {
            isRestore = recordRestore;
            return self();
        }

        /**
         * define that that is a period restore request.
         * @param periodRestore
         * @return self
         */
        public UpsertRequestContextBuilder periodRestore(boolean periodRestore) {
            isPeriodRestore = periodRestore;
            return self();
        }
        /**
         * Sets code attribute aliases.
         * @param codeAttributeAliases
         * @return
         */
        public UpsertRequestContextBuilder codeAttributeAliases(Collection<CodeAttributeAlias> codeAttributeAliases) {
            this.codeAttributeAliases = codeAttributeAliases;
            return self();
        }
        /**
         *
         * @param enrichByPreviousVersion - enrich By Previous Version
         * @return self
         */
        public UpsertRequestContextBuilder mergeWithPrevVersion(boolean enrichByPreviousVersion) {
            this.mergeWithPreviousVersion = enrichByPreviousVersion;
            return self();
        }
        /**
         *
         * @param skipIndexDrop - skip index drop or not
         * @return self
         */
        public UpsertRequestContextBuilder skipIndexDrop(boolean skipIndexDrop) {
            this.skipIndexDrop = skipIndexDrop;
            return self();
        }
        /**
         * @param skipMatching skip matching main phase or not
         */
        public UpsertRequestContextBuilder skipMatching(boolean skipMatching) {
            this.skipMatching = skipMatching;
            return self();
        }
        /**
         * @param suppressWorkflow force suppress workflow or not
         */
        public UpsertRequestContextBuilder suppressWorkflow(boolean suppressWorkflow) {
            this.suppressWorkflow = suppressWorkflow;
            return self();
        }
        /**
         * @param applyDraft is apply draft context or not
         */
        public UpsertRequestContextBuilder applyDraft(boolean applyDraft) {
            this.isApplyDraft = applyDraft;
            return self();
        }
        /**
         * Set upsert hint.
         * @param h the hint
         * @param value the value
         */
        public UpsertRequestContextBuilder hint(UpsertRecordHint h, Object value) {
            if (Objects.nonNull(h)) {
                if (Objects.isNull(hints)) {
                    hints = new EnumMap<>(UpsertRecordHint.class);
                }

                hints.put(h, value);
            }
            return self();
        }
        /**
         * @return  get valid from
         */
        public Date getValidFrom() {
            return validFrom;
        }

        /**
         * @return  get valid to
         */
        public Date getValidTo() {
            return validTo;
        }

        /**
         * @return  get record
         */
        public DataRecord getRecord() {
            return data;
        }

       /**
        * Builds a context.
        * @return a new context
        */
        @Override
       public UpsertRequestContext build() {
            return new UpsertRequestContext(this);
        }
    }
}
