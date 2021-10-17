

package org.datasphere.mdm.data.context;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.data.service.segments.relations.get.RelationGetStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Gets relations
 */
public class GetRelationRequestContext
    extends AbstractRelationIdentityContext
    implements
        ContainmentRelationContext<GetRequestContext>,
        RelationFromIdentityContext,
        ReadOnlyTimelineContext<OriginRelation>,
        AccessRightContext,
        SetupAwareContext,
        DraftAwareContext,
        DraftIdResettingContext,
        TimelineQueryContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -5874979941996090899L;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * For a particular date range (left &lt;-&gt; right).
     */
    private final Pair<Date, Date> forDatesFrame;
    /**
     * Operation id.
     */
    private final String forOperationId;
    /**
     * Last update date to cut off versions.
     */
    private final Date forLastUpdate;
    /**
     * Has updates (new versions) after this date.
     */
    private final Date forUpdatesAfter;
    /**
     * A possibly set draft id.
     */
    private Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * Constructor.
     */
    protected GetRelationRequestContext(GetRelationRequestContextBuilder b) {
        super(b);
        this.forDate = b.forDate;
        this.forDatesFrame = b.forDatesFrame;
        this.forOperationId = b.forOperationId;
        this.forLastUpdate = b.forLastUpdate;
        this.forUpdatesAfter = b.forUpdatesAfter;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;

        flags.set(DataContextFlags.FLAG_INCLUDE_DRAFTS, b.fetchKeys);
        flags.set(DataContextFlags.FLAG_FETCH_TIMELINE_DATA, b.fetchData);
        flags.set(DataContextFlags.FLAG_SKIP_TIMELINE_CALCULATIONS, b.skipCalculations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RelationGetStartExecutor.SEGMENT_ID;
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
     * @return the unpublishedState
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
     *
     * @return builder
     */
    public static GetRelationRequestContextBuilder builder(){
        return new GetRelationRequestContextBuilder();
    }

    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetRelationRequestContextBuilder extends AbstractRelationIdentityContextBuilder<GetRelationRequestContextBuilder> {
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
        /**
         * For a particular date (as of).
         */
        private Date forDate;
        /**
         * For a particular date range (left <-> right).
         */
        private Pair<Date, Date> forDatesFrame;
        /**
         * Operation id.
         */
        private String forOperationId;
        /**
         * Last update date to cut off versions.
         */
        private Date forLastUpdate;
        /**
         * Has updates (new versions) after this date.
         */
        private Date forUpdatesAfter;
        /**
         * Fetch keys or not.
         */
        private boolean fetchKeys;
        /**
         * Return timeline with data.
         */
        private boolean fetchData;
        /**
         * Skip etalon calculation or not.
         */
        private boolean skipCalculations;
        /**
         * Constructor.
         */
        protected GetRelationRequestContextBuilder() {
            super();
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public GetRelationRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public GetRelationRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param forDate the forDate to set
         */
        public GetRelationRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return this;
        }
        /**
         * @param forDatesFrame the forDate to set
         */
        public GetRelationRequestContextBuilder forDatesFrame(Pair<Date, Date> forDatesFrame) {
            this.forDatesFrame = forDatesFrame;
            return this;
        }
        /**
         * @param forOperationId the forOperationId to set
         */
        public GetRelationRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return this;
        }
        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public GetRelationRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return this;
        }
        /**
         * @param updatesAfter the updatesAfter to set
         */
        public GetRelationRequestContextBuilder forUpdatesAfter(Date updatesAfter) {
            this.forUpdatesAfter = updatesAfter;
            return self();
        }
        /**
         * Request tasks additionally. Show draft version.
         */
        public GetRelationRequestContextBuilder fetchKeys(boolean fetchKeys) {
            this.fetchKeys = fetchKeys;
            return this;
        }
        /**
         * @param fetchTimelineData the fetchTimelineData to set
         */
        public GetRelationRequestContextBuilder fetchData(boolean fetchTimelineData) {
            this.fetchData = fetchTimelineData;
            return this;
        }
        /**
         * @param skipCalculations the skipCalculations to set
         */
        public GetRelationRequestContextBuilder skipCalculations(boolean skipCalculations) {
            this.skipCalculations = skipCalculations;
            return self();
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetRelationRequestContext build() {
            return new GetRelationRequestContext(this);
        }
    }
}
