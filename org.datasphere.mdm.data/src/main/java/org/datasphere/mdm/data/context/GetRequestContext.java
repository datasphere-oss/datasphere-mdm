

/**
 *
 */
package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.datasphere.mdm.data.service.segments.records.get.RecordGetStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Get record request context.
 */
public class GetRequestContext
    extends AbstractRecordIdentityContext
    implements
        ReadOnlyTimelineContext<OriginRecord>,
        SimpleAttributesDiffContext,
        AccessRightContext,
        SetupAwareContext,
        ExtendedAttributesAwareContext,
        DraftAwareContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -6826317454436803507L;
    /**
     * A possibly set draft id.
     */
    private final Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * Preview etalon keys
     */
    private final List<String> previewEtalonKeys;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * Has updates (new versions) after this date.
     */
    private final Date updatesAfter;
    /**
     * Last update date to cut off versions.
     */
    private final Date forLastUpdate;
    /**
     * Operation id.
     */
    private final String forOperationId;
    /**
     * Manual merge attributes.
     */
    private final Map<String, String> manualMergeAttrs;
    /**
     * Constructor.
     */
    protected GetRequestContext(GetRequestContextBuilder b) {

        super(b);
        this.forDate = b.forDate;
        this.updatesAfter = b.updatesAfter;
        this.forLastUpdate = b.forLastUpdate;
        this.forOperationId = b.forOperationId;
        this.previewEtalonKeys = b.previewEtalons;
        this.manualMergeAttrs = b.manualMergeAttrs;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;

        // Flags
        flags.set(DataContextFlags.FLAG_FETCH_ORIGINS, b.fetchOrigins);
        flags.set(DataContextFlags.FLAG_FETCH_ORIGINS_HISTORY, b.fetchOriginsHistory);
        flags.set(DataContextFlags.FLAG_FETCH_SOFT_DELETED, b.fetchSoftDeleted);
        flags.set(DataContextFlags.FLAG_FETCH_LARGE_OBJECTS, b.fetchLargeObjects);
        flags.set(DataContextFlags.FLAG_FETCH_TIMELINE_DATA, b.fetchTimelineData);
        flags.set(DataContextFlags.FLAG_FETCH_DATA_FOR_PERIOD, b.loadDataForPeriod);
        flags.set(DataContextFlags.FLAG_INCLUDE_MERGED, b.includeMerged);
        flags.set(DataContextFlags.FLAG_INCLUDE_INACTIVE, b.includeInactive);
        flags.set(DataContextFlags.FLAG_INCLUDE_DRAFTS, b.includeDrafts);
        flags.set(DataContextFlags.FLAG_INCLUDE_WINNERS, b.includeWinners);
        flags.set(DataContextFlags.FLAG_DIFF_TO_DRAFT, b.diffToDraft);
        flags.set(DataContextFlags.FLAG_DIFF_TO_PREVIOUS, b.diffToPrevious);
        flags.set(DataContextFlags.FLAG_STRICT_DRAFT, b.strictDraft);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RecordGetStartExecutor.SEGMENT_ID;
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
    public Long getParentDraftId() {
        return parentDraftId;
    }

    /**
     * @return the forDate
     */
    public Date getForDate() {
        return forDate;
    }

    /**
     * @return the updatesAfter
     */
    public Date getUpdatesAfter() {
        return updatesAfter;
    }

    /**
     * @return the lastUpdate
     */
    public Date getForLastUpdate() {
        return forLastUpdate;
    }

    /**
     * @return the forOperationId
     */
    public String getForOperationId() {
        return forOperationId;
    }

    /**
     * Get manual merge attributes.
     * @return manual merge attributes.
     */
    public Map<String, String> getManualMergeAttributes(){
        return manualMergeAttrs;
    }

    /**
     * @return the fetchOrigins
     */
    public boolean isFetchOrigins() {
        return flags.get(DataContextFlags.FLAG_FETCH_ORIGINS);
    }

    /**
     * @return the fetchOriginsHistory
     */
    public boolean isFetchOriginsHistory() {
        return flags.get(DataContextFlags.FLAG_FETCH_ORIGINS_HISTORY);
    }

    /**
     * @return the fetchSoftDeleted
     */
    public boolean isFetchSoftDeleted() {
        return flags.get(DataContextFlags.FLAG_FETCH_SOFT_DELETED);
    }

    /**
     * @return the includeMerged
     */
    public boolean isIncludeMerged() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_MERGED);
    }

    /**
     * @return the includeInactive
     */
    public boolean isIncludeInactive() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_INACTIVE);
    }
    /**
     * @return the unpublishedState
     */
    public boolean isIncludeDrafts() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_DRAFTS);
    }
    /**
     * @return the diffToDraft
     */
    public boolean isDiffToDraft() {
        return flags.get(DataContextFlags.FLAG_DIFF_TO_DRAFT);
    }
    /**
     * @return the diffToPervious
     */
    public boolean isDiffToPervious() {
        return flags.get(DataContextFlags.FLAG_DIFF_TO_PREVIOUS);
    }
    /**
     * @return the isStrictDraft
     */
    public boolean isStrictDraft() {
        return flags.get(DataContextFlags.FLAG_STRICT_DRAFT);
    }
    /**
     * @return the fetchLargeObjects
     */
    public boolean isFetchLargeObjects() {
        return flags.get(DataContextFlags.FLAG_FETCH_LARGE_OBJECTS);
    }
    /**
     * @return the fetchTimelineData
     */
    public boolean isFetchTimelineData() {
        return flags.get(DataContextFlags.FLAG_FETCH_TIMELINE_DATA);
    }
    /**
     * @return load data for period or not
     */
    public boolean isLoadDataForPeriod() {
        return flags.get(DataContextFlags.FLAG_FETCH_DATA_FOR_PERIOD);
    }
    /**
     * @return collection of etalons for
     */
    public List<String> getPreviewEtalonKeys() {
        return previewEtalonKeys;
    }

    /**
     * Builder shorthand.
     * @return builder
     */
    public static GetRequestContextBuilder builder() {
        return new GetRequestContextBuilder();
    }
    /**
     * Builder shorthand.
     * @param other the other context to copy
     * @return builder
     */
    public static GetRequestContextBuilder builder(GetRequestContext other) {
        return new GetRequestContextBuilder(other);
    }
    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetRequestContextBuilder extends AbstractRecordIdentityContextBuilder<GetRequestContextBuilder> {
        /**
         * Etalon ids for merge preview
         */
        private List<String> previewEtalons = Collections.emptyList();
        /**
         * For a particular date (as of).
         */
        private Date forDate;
        /**
         * Has updates (new versions) after this date.
         */
        private Date updatesAfter;
        /**
         * Last update date to cut off versions.
         */
        private Date forLastUpdate;
        /**
         * Operation id.
         */
        private String forOperationId;
        /**
         * Return fetchOrigins or not.
         */
        private boolean fetchOrigins;
        /**
         * Return fetchOrigins history or not.
         */
        private boolean fetchOriginsHistory;
        /**
         * Return soft deleted or not.
         */
        private boolean fetchSoftDeleted;
        /**
         * Return includeMerged or not.
         */
        private boolean includeMerged;
        /**
         * Include inactive versions in calculation.
         */
        private boolean includeInactive;
        /**
         * View unpublished state or not.
         */
        private boolean includeDrafts;
        /**
         * Include information about winners
         */
        private boolean includeWinners;
        /**
         * Calculate and return diff to draft (pending) state, if the record is in pending state.
         */
        private boolean diffToDraft;
        /**
         * Calculate and return diff to previous etalon state (one version ago).
         */
        private boolean diffToPrevious;
        /**
         * Use strictDrafts value without check by author record name
         */
        private boolean strictDraft;
        /**
         * Return large objects data immediately or not.
         */
        private boolean fetchLargeObjects;
        /**
         * Return timeline with data.
         */
        private boolean fetchTimelineData;
        /**
         * Load data for record period, not for date.
         */
        private boolean loadDataForPeriod;
        /**
         * Manual merge attributes.
         */
        private Map<String, String> manualMergeAttrs;
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
        /**
         * Constructor.
         */
        protected GetRequestContextBuilder(GetRequestContext other) {
            super(other);
            this.previewEtalons = other.previewEtalonKeys != null && !other.previewEtalonKeys.isEmpty()
                    ? new ArrayList<>(other.previewEtalonKeys)
                    : null;

            this.manualMergeAttrs = other.manualMergeAttrs != null && !other.manualMergeAttrs.isEmpty()
                    ? new HashMap<>(other.manualMergeAttrs)
                    : null;

            this.forDate = other.forDate;
            this.updatesAfter = other.updatesAfter;
            this.forLastUpdate = other.forLastUpdate;
            this.forOperationId = other.forOperationId;

            this.fetchOrigins = other.isFetchOrigins();
            this.fetchOriginsHistory = other.isFetchOriginsHistory();
            this.fetchSoftDeleted = other.isFetchSoftDeleted();
            this.includeMerged = other.isIncludeMerged();
            this.includeInactive = other.isIncludeInactive();
            this.includeDrafts = other.isIncludeDrafts();
            this.includeWinners = other.isIncludeWinners();
            this.diffToDraft = other.isDiffToDraft();
            this.diffToPrevious = other.isDiffToPervious();
            this.strictDraft = other.isStrictDraft();
            this.fetchLargeObjects = other.isFetchLargeObjects();
            this.fetchTimelineData = other.isFetchTimelineData();
        }
        /**
         * Constructor.
         */
        protected GetRequestContextBuilder() {
            super();
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public GetRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public GetRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param previewEtalons collection of keys for merge preview
         * @return self
         */
        public GetRequestContextBuilder previewKeys(List<String> previewEtalons) {
            this.previewEtalons = previewEtalons;
            return this;
        }

        /**
         * @param forDate the forDate to set
         */
        public GetRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return this;
        }

        /**
         * @param updatesAfter the updatesAfter to set
         */
        public GetRequestContextBuilder updatesAfter(Date updatesAfter) {
            this.updatesAfter = updatesAfter;
            return this;
        }

        /**
         * @param forOperationId the forOperationId to set
         */
        public GetRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return this;
        }

        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public GetRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return this;
        }

        /**
         * @param fetchOrigins the fetchOrigins to set
         */
        public GetRequestContextBuilder fetchOrigins(boolean fetchOrigins) {
            this.fetchOrigins = fetchOrigins;
            return this;
        }

        /**
         * @param fetchOriginsHistory the fetchOriginsHistory to set
         */
        public GetRequestContextBuilder fetchOriginsHistory(boolean fetchOriginsHistory) {
            this.fetchOriginsHistory = fetchOriginsHistory;
            return this;
        }

        /**
         * @param fetchSoftDeleted the fetchSoftDeleted to set
         */
        public GetRequestContextBuilder fetchSoftDeleted(boolean fetchSoftDeleted) {
            this.fetchSoftDeleted = fetchSoftDeleted;
            return this;
        }
        /**
         * @param includeMerged the includeMerged to set
         */
        public GetRequestContextBuilder includeMerged(boolean includeMerged) {
            this.includeMerged = includeMerged;
            return this;
        }
        /**
         * Instructs etalon calculator to include inactive versions into calculation.
         * @param includeInactive the includeInactive to set
         * @return self
         */
        public GetRequestContextBuilder includeInactive(boolean includeInactive) {
            this.includeInactive = includeInactive;
            return this;
        }
        /**
         * Request unpublished state of a record or not.
         * @param includeDrafts requested state
         * @return self
         */
        public GetRequestContextBuilder includeDrafts(boolean includeDrafts) {
            this.includeDrafts = includeDrafts;
            return this;
        }
        /**
         * Request information about winners
         * @param includeWinners requested state
         * @return self
         */
        public GetRequestContextBuilder includeWinners(boolean includeWinners) {
            this.includeWinners = includeWinners;
            return this;
        }
        /**
         * Calculate and return diff to draft (pending) state, if the record is in pending state.
         * @param diffToDraft request state
         * @return self
         */
        public GetRequestContextBuilder diffToDraft(boolean diffToDraft) {
            this.diffToDraft = diffToDraft;
            return this;
        }
        /**
         * Calculate and return diff to previous etalon state (one version ago).
         * @param diffToPrevious request state
         * @return self
         */
        public GetRequestContextBuilder diffToPrevious(boolean diffToPrevious) {
            this.diffToPrevious = diffToPrevious;
            return this;
        }
        /**
         * Use strictDrafts value without check by author record name
         * @param strictDraft requested state
         * @return self
         */
        public GetRequestContextBuilder strictDraft(boolean strictDraft) {
            this.strictDraft = strictDraft;
            return this;
        }
        /**
         * @param fetchLargeObjects the fetchLargeObjects to set
         */
        public GetRequestContextBuilder fetchLargeObjects(boolean fetchLargeObjects) {
            this.fetchLargeObjects = fetchLargeObjects;
            return this;
        }
        /**
         * @param fetchTimelineData the fetchTimelineData to set
         */
        public GetRequestContextBuilder fetchTimelineData(boolean fetchTimelineData) {
            this.fetchTimelineData = fetchTimelineData;
            return this;
        }
        /**
         * @param loadDataForPeriod set 'load data for period' flag
         */
        public GetRequestContextBuilder loadDataForPeriod(boolean loadDataForPeriod) {
            this.loadDataForPeriod = loadDataForPeriod;
            return this;
        }
        /**
         * Manual merge attributes.
         * @param manualMergeAttrs manual merge attributes.
         * @return context builder.
         */
        public GetRequestContextBuilder manualMergeAttrs(Map<String, String> manualMergeAttrs) {
            this.manualMergeAttrs = manualMergeAttrs;
            return  this;
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetRequestContext build() {
            return new GetRequestContext(this);
        }
    }
}
