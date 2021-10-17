

package org.datasphere.mdm.data.context;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.data.type.keys.RecordKeys;

/**
 * @author Mikhail Mikhailov
 * Gets relations of the left side record, denoted by fields for relation name 'name'.
 */
public class GetRelationsTimelineRequestContext
    extends AbstractRecordIdentityContext
    implements TimelineQueryContext {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -8833494823028426839L;
    /**
     * 'Load all for names' support.
     */
    private final List<String> relationNames;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * For a particular date range.
     */
    private final Pair<Date, Date> forDatesFrame;
    /**
     * Has updates (new versions) after this date.
     */
    private final Date forUpdatesAfter;
    /**
     * Last update date to cut off versions.
     */
    private final Date forLastUpdate;
    /**
     * Operation id.
     */
    private final String forOperationId;
    /**
     * The parent record draft ID.
     */
    private final Long parentDraftId;
    /**
     * Constructor.
     */
    protected GetRelationsTimelineRequestContext(GetRelationsTimelineRequestContextBuilder b) {
        super(b);
        this.relationNames = b.relationNames;
        this.forDate = b.forDate;
        this.forDatesFrame = b.forDatesFrame;
        this.forLastUpdate = b.forLastUpdate;
        this.forUpdatesAfter = b.forUpdatesAfter;
        this.forOperationId = b.forOperationId;
        this.parentDraftId = b.parentDraftId;

        flags.set(DataContextFlags.FLAG_FETCH_KEYS, b.fetchKeys);
        flags.set(DataContextFlags.FLAG_FETCH_TIMELINE_DATA, b.fetchData);
        flags.set(DataContextFlags.FLAG_FETCH_TIMELINE_BY_TO_SIDE, b.fetchByToSide);
        flags.set(DataContextFlags.FLAG_SKIP_TIMELINE_CALCULATIONS, b.skipCalculations);
        flags.set(DataContextFlags.FLAG_REDUCE_REFERENCE_RELATIONS, b.reduceReferences);
        flags.set(DataContextFlags.FLAG_INCLUDE_INACTIVE, b.includeInactive);
    }

    /**
     * Returns supplied parent draft ID, if any.
     * @return parent draft id
     */
    public Long getParentDraftId() {
        return parentDraftId;
    }

    /**
     * Returns true, if this context defines a valid parent draftId field,
     * @return true, if this context defines a valid parent draftId field,
     */
    public boolean hasParentDraftId() {
        return Objects.nonNull(getParentDraftId()) && getParentDraftId() > 0;
    }

    /**
     * Returns true, if one of the draft ids is set,
     * marking this context as capable for a draft operation.
     * @return true, if one of the draft ids is set
     */
    public boolean isDraftOperation() {
        return hasParentDraftId();
    }

    /**
     * @return the relationNames
     */
    public List<String> getRelationNames() {
        return relationNames == null ? Collections.emptyList() : this.relationNames;
    }

    /**
     * @return the forDate
     */
    @Override
    public Date getForDate() {
        return forDate;
    }

    /**
     * @return the dates frame
     */
    @Override
    public Pair<Date, Date> getForDatesFrame() {
        return forDatesFrame;
    }

    /**
     * @return the lastUpdate
     */
    @Override
    public Date getForLastUpdate() {
        return forLastUpdate;
    }

    /**
     * @return the updatesAfter
     */
    @Override
    public Date getForUpdatesAfter() {
        return forUpdatesAfter;
    }
    /**
     * @return the forOperationId
     */
    @Override
    public String getForOperationId() {
        return forOperationId;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFetchKeys() {
        return flags.get(DataContextFlags.FLAG_FETCH_KEYS);
    }
    /**
     * @return the fetchTimelineData
     */
    @Override
    public boolean isFetchData() {
        return flags.get(DataContextFlags.FLAG_FETCH_TIMELINE_DATA);
    }
    /**
     * @return the fetchByToSide
     */
    public boolean isFetchByToSide() {
        return flags.get(DataContextFlags.FLAG_FETCH_TIMELINE_BY_TO_SIDE);
    }
    /**
     * @return the skipCalculations
     */
    @Override
    public boolean isSkipCalculations() {
        return flags.get(DataContextFlags.FLAG_SKIP_TIMELINE_CALCULATIONS);
    }

    /**
     * @return the reduce references
     */
    public boolean isReduceReferences() {
        return flags.get(DataContextFlags.FLAG_REDUCE_REFERENCE_RELATIONS);
    }

    /**
     * @return the inactive elements
     */
    public boolean isIncludeInactive() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_INACTIVE);
    }

    /**
     * Gets new builder.
     * @return builder
     */
    public static GetRelationsTimelineRequestContextBuilder builder() {
        return new GetRelationsTimelineRequestContextBuilder();
    }

    /**
     * Gets new builder.
     * FIXME: Almost no difference with GetRelationsRequestContext. Distinct flags are unused there. Merge the two.
     * @return builder
     */
    public static GetRelationsTimelineRequestContextBuilder builder(GetRelationsRequestContext other) {

        RecordKeys keys = other.keys();
        GetRelationsTimelineRequestContextBuilder b = new GetRelationsTimelineRequestContextBuilder();
        b.entityName = keys != null ? keys.getEntityName() : other.getEntityName();
        b.etalonKey = keys != null && keys.getEtalonKey() != null ? keys.getEtalonKey().getId() : other.getEtalonKey();
        b.externalId = keys != null && keys.getOriginKey() != null ? keys.getOriginKey().getExternalId() : other.getExternalId();
        b.originKey = keys != null && keys.getOriginKey() != null ? keys.getOriginKey().getId() : other.getOriginKey();
        b.sourceSystem = keys != null && keys.getOriginKey() != null ? keys.getOriginKey().getSourceSystem() : other.getSourceSystem();
        b.fetchData = other.isFetchTimelineData();
        b.forDate = other.getForDate();
        b.forDatesFrame = other.getForDatesFrame();
        b.forOperationId = other.getOperationId();
        b.relationNames = other.getRelationNames();
        b.reduceReferences = other.isReduceReferences();
        b.forLastUpdate = other.getForLastUpdate();
        return b;
    }
    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetRelationsTimelineRequestContextBuilder
        extends AbstractRecordIdentityContextBuilder<GetRelationsTimelineRequestContextBuilder> {
        /**
         * 'Load all for names' support.
         */
        private List<String> relationNames;
        /**
         * For a particular date (as of).
         */
        private Date forDate;
        /**
         * For a particular date range (left <-> right).
         */
        private Pair<Date, Date> forDatesFrame;
        /**
         * Last update date to cut off versions.
         */
        private Date forLastUpdate;
        /**
         * Has updates (new versions) after this date.
         */
        private Date forUpdatesAfter;
        /**
         * Operation id.
         */
        private String forOperationId;
        /**
         * Return keys.
         */
        private boolean fetchKeys = true;
        /**
         * Return timeline with data.
         */
        private boolean fetchData;
/**
         * Fetch by to side. Otherwise by from side.
         */
        private boolean fetchByToSide;
        /**
         * Skip etalon, activity, operation type calculations.
         * Return raw timeline.
         */
        private boolean skipCalculations;
        /**
         * Reduce reference timeline
         */
        private boolean reduceReferences;
        /**
         * Include inactive relations flag.
         */
        private boolean includeInactive;
        /**
         * The parent record draft ID.
         */
        private Long parentDraftId;
        /**
         * Constructor.
         */
        protected GetRelationsTimelineRequestContextBuilder() {
            super();
        }
        /**
         * @param parentDraftId the parentDraftId to set
         */
        public GetRelationsTimelineRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return this;
        }
        /**
         * @param relationNames the relation names to set
         * @return self
         */
        public GetRelationsTimelineRequestContextBuilder relationNames(List<String> relationNames) {
            this.relationNames = relationNames;
            return this;
        }
        /**
         * @param relationNames the relation names to set
         * @return self
         */
        public GetRelationsTimelineRequestContextBuilder relationNames(String... relationNames) {
            this.relationNames = Arrays.asList(relationNames);
            return this;
        }
        /**
         * @param forDate the forDate to set
         */
        public GetRelationsTimelineRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return this;
        }
        /**
         * @param forDatesFrame the date frame to set
         */
        public GetRelationsTimelineRequestContextBuilder forDatesFrame(Pair<Date, Date> forDatesFrame) {
            this.forDatesFrame = forDatesFrame;
            return this;
        }
        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public GetRelationsTimelineRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return this;
        }
        /**
         * @param updatesAfter the updatesAfter to set
         */
        public GetRelationsTimelineRequestContextBuilder forUpdatesAfter(Date updatesAfter) {
            this.forUpdatesAfter = updatesAfter;
            return self();
        }
        /**
         * @param forOperationId the forOperationId to set
         */
        public GetRelationsTimelineRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return this;
        }
        /**
         * @param keys the keys to set
         */
        public GetRelationsTimelineRequestContextBuilder fetchKeys(boolean keys) {
            this.fetchKeys = keys;
            return self();
        }
        /**
         * @param fetchTimelineData the fetchTimelineData to set
         */
        public GetRelationsTimelineRequestContextBuilder fetchData(boolean fetchTimelineData) {
            this.fetchData = fetchTimelineData;
            return this;
        }
        /**
         * @param fetchByToSide the fetchByToSide to set
         */
        public GetRelationsTimelineRequestContextBuilder fetchByToSide(boolean fetchByToSide) {
            this.fetchByToSide = fetchByToSide;
            return this;
        }
        /**
         * @param skipCalculations the skipCalculations to set
         */
        public GetRelationsTimelineRequestContextBuilder skipCalculations(boolean skipCalculations) {
            this.skipCalculations = skipCalculations;
            return this;
        }
        /**
         * @param reduceReferences the reduceReferences to set
         */
        public GetRelationsTimelineRequestContextBuilder reduceReferences(boolean reduceReferences) {
            this.reduceReferences = reduceReferences;
            return this;
        }
        /**
         * Request inactive additionally. Show inactive rels.
         */
        public GetRelationsTimelineRequestContextBuilder includeInactive(boolean includeInactive) {
            this.includeInactive = includeInactive;
            return this;
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetRelationsTimelineRequestContext build() {
            return new GetRelationsTimelineRequestContext(this);
        }
    }
}
