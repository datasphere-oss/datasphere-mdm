

package org.datasphere.mdm.data.context;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.system.context.DraftAwareContext;

/**
 * @author Mikhail Mikhailov
 * Gets relations
 */
public class GetRelationTimelineRequestContext
    extends AbstractRelationIdentityContext
    implements DraftAwareContext, TimelineQueryContext, RelationFromIdentityContext, RelationToIdentityContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -5874979941996090899L;
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
     * A possibly set draft id.
     */
    private final Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * Constructor.
     */
    protected GetRelationTimelineRequestContext(GetRelationTimelineRequestContextBuilder b) {
        super(b);
        this.forDate = b.forDate;
        this.forDatesFrame = b.forDatesFrame;
        this.forUpdatesAfter = b.forUpdatesAfter;
        this.forLastUpdate = b.forLastUpdate;
        this.forOperationId = b.forOperationId;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;

        flags.set(DataContextFlags.FLAG_FETCH_KEYS, b.fetchKeys);
        flags.set(DataContextFlags.FLAG_FETCH_TIMELINE_DATA, b.fetchData);
        flags.set(DataContextFlags.FLAG_SKIP_TIMELINE_CALCULATIONS, b.skipCalculations);
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
     * {@inheritDoc}
     */
    @Override
    public RelativeDirection getDirection() {

        if (Objects.nonNull(fromKeys())) {
            return RelativeDirection.FROM;
        }

        return Objects.nonNull(toKeys()) ? RelativeDirection.TO : null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toBoxKey() {

        String boxKey = RelationFromIdentityContext.super.toBoxKey();
        if (Objects.isNull(boxKey)) {
            return RelationToIdentityContext.super.toBoxKey();
        }

        return boxKey;
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
     * @return the updatesAfter
     */
    @Override
    public Date getForUpdatesAfter() {
        return forUpdatesAfter;
    }
    /**
     * @return the lastUpdate
     */
    @Override
    public Date getForLastUpdate() {
        return forLastUpdate;
    }
    /**
     * @return the forOperationId
     */
    @Override
    public String getForOperationId() {
        return forOperationId;
    }
    /**
     * @return the tasks
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
     * @return the skipCalculations
     */
    @Override
    public boolean isSkipCalculations() {
        return flags.get(DataContextFlags.FLAG_SKIP_TIMELINE_CALCULATIONS);
    }
    /**
     * Default empty builder.
     * @return builder
     */
    public static GetRelationTimelineRequestContextBuilder builder(){
        return new GetRelationTimelineRequestContextBuilder();
    }
    /**
    * Copy builder.
    * @return builder
    */
   public static GetRelationTimelineRequestContextBuilder builder(RelationIdentityContext other) {

       GetRelationTimelineRequestContextBuilder b = new GetRelationTimelineRequestContextBuilder();
       b.relationEtalonKey = other.getRelationEtalonKey();
       b.relationOriginKey = other.getRelationOriginKey();
       b.entityName = other.getEntityName();
       b.etalonKey = other.getEtalonKey();
       b.externalId = other.getExternalId();
       b.sourceSystem = other.getSourceSystem();
       b.originKey = other.getOriginKey();
       b.lsn = other.getLsn();
       b.shard = other.getShard();
       b.relationLsn = other.getRelationLsn();
       b.relationShard = other.getRelationShard();

       if (other instanceof GetRelationRequestContext) {

           GetRelationRequestContext gOther = (GetRelationRequestContext) other;
           b.fetchData = gOther.isFetchData();
           b.forDate = gOther.getForDate();
           b.forLastUpdate = gOther.getForLastUpdate();
           b.forUpdatesAfter = gOther.getForUpdatesAfter();
           b.forDatesFrame = gOther.getForDatesFrame();
           b.forOperationId = gOther.getForOperationId();
       } else if (other instanceof GetRelationTimelineRequestContext) {

           GetRelationTimelineRequestContext tOther = (GetRelationTimelineRequestContext) other;
           b.fetchData = tOther.isFetchData();
           b.fetchKeys = tOther.isFetchKeys();
           b.skipCalculations = tOther.isSkipCalculations();
           b.forDate = tOther.getForDate();
           b.forLastUpdate = tOther.getForLastUpdate();
           b.forUpdatesAfter = tOther.getForUpdatesAfter();
           b.forDatesFrame = tOther.getForDatesFrame();
           b.forOperationId = tOther.getForOperationId();
       }

       if (other instanceof DraftAwareContext) {
           DraftAwareContext dOther = (DraftAwareContext) other;
           b.draftId = dOther.getDraftId();
           b.parentDraftId = dOther.getParentDraftId();
       }

       return b;
   }

    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetRelationTimelineRequestContextBuilder
        extends AbstractRelationIdentityContextBuilder<GetRelationTimelineRequestContextBuilder> {
        /**
         * For a particular date (as of).
         */
        protected Date forDate;
        /**
         * For a particular date range (left <-> right).
         */
        protected Pair<Date, Date> forDatesFrame;
        /**
         * Has updates (new versions) after this date.
         */
        protected Date forUpdatesAfter;
        /**
         * Last update date to cut off versions.
         */
        protected Date forLastUpdate;
        /**
         * Operation id.
         */
        protected String forOperationId;
        /**
         * Return keys.
         */
        protected boolean fetchKeys = true;
        /**
         * Return timeline with data.
         */
        protected boolean fetchData;
        /**
         * Skip etalon, activity, operation type calculations.
         * Return raw timeline.
         */
        protected boolean skipCalculations;
        /**
         * The draft id.
         */
        protected Long draftId;
        /**
         * The parent draft id.
         */
        protected Long parentDraftId;
        /**
         * Constructor.
         */
        protected GetRelationTimelineRequestContextBuilder() {
            super();
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public GetRelationTimelineRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public GetRelationTimelineRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param forDate the forDate to set
         */
        public GetRelationTimelineRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return self();
        }
        /**
         * @param forDatesFrame the date frame to set
         */
        public GetRelationTimelineRequestContextBuilder forDatesFrame(Pair<Date, Date> forDatesFrame) {
            this.forDatesFrame = forDatesFrame;
            return self();
        }
        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public GetRelationTimelineRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return self();
        }
        /**
         * @param updatesAfter the updatesAfter to set
         */
        public GetRelationTimelineRequestContextBuilder forUpdatesAfter(Date updatesAfter) {
            this.forUpdatesAfter = updatesAfter;
            return self();
        }
        /**
         * @param forOperationId the forOperationId to set
         */
        public GetRelationTimelineRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return self();
        }
        /**
         * @param keys the keys to set
         */
        public GetRelationTimelineRequestContextBuilder fetchKeys(boolean keys) {
            this.fetchKeys = keys;
            return self();
        }
        /**
         * @param fetchTimelineData the fetchTimelineData to set
         */
        public GetRelationTimelineRequestContextBuilder fetchData(boolean fetchTimelineData) {
            this.fetchData = fetchTimelineData;
            return self();
        }
        /**
         * @param skipCalculations the skipCalculations to set
         */
        public GetRelationTimelineRequestContextBuilder skipCalculations(boolean skipCalculations) {
            this.skipCalculations = skipCalculations;
            return self();
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetRelationTimelineRequestContext build() {
            return new GetRelationTimelineRequestContext(this);
        }
    }
}
