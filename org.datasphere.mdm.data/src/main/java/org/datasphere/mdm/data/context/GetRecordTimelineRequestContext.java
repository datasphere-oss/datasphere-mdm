

/**
 *
 */
package org.datasphere.mdm.data.context;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.system.context.DraftAwareContext;

/**
 * @author Mikhail Mikhailov
 * Fetch timeline for a record.
 */
public class GetRecordTimelineRequestContext
    extends AbstractRecordIdentityContext
    implements DraftAwareContext, TimelineQueryContext {
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
     * Constructor.
     */
    protected GetRecordTimelineRequestContext(GetRecordTimelineRequestContextBuilder b) {
        super(b);
        this.forDate = b.forDate;
        this.forDatesFrame = b.forDatesFrame;
        this.forUpdatesAfter = b.forUpdatesAfter;
        this.forLastUpdate = b.forLastUpdate;
        this.forOperationId = b.forOperationId;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;

        // Flags
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
     * Builder shorthand.
     * @return builder
     */
    public static GetRecordTimelineRequestContextBuilder builder() {
        return new GetRecordTimelineRequestContextBuilder();
    }
    /**
     * Builder shorthand.
     * @return builder
     */
    public static GetRecordTimelineRequestContextBuilder builder(AbstractRecordIdentityContext other) {

        GetRecordTimelineRequestContextBuilder b = new GetRecordTimelineRequestContextBuilder(other);
        if (other instanceof GetRequestContext) {
            GetRequestContext gOther = (GetRequestContext) other;
            b.forDate = gOther.getForDate();
            b.fetchData = gOther.isFetchTimelineData();
            b.forLastUpdate = gOther.getForLastUpdate();
            b.forOperationId = gOther.getForOperationId();
            b.forUpdatesAfter = gOther.getUpdatesAfter();
        } else if (other instanceof GetRecordTimelineRequestContext) {
            GetRecordTimelineRequestContext tOther = (GetRecordTimelineRequestContext) other;
            b.fetchData = tOther.isFetchData();
            b.fetchKeys = tOther.isFetchKeys();
            b.skipCalculations = tOther.isSkipCalculations();
            b.forDate = tOther.getForDate();
            b.forDatesFrame = tOther.getForDatesFrame();
            b.forUpdatesAfter = tOther.getForUpdatesAfter();
            b.forLastUpdate = tOther.getForLastUpdate();
            b.forOperationId = tOther.getForOperationId();
        }

        return b;
    }
    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetRecordTimelineRequestContextBuilder
        extends AbstractRecordIdentityContextBuilder<GetRecordTimelineRequestContextBuilder> {
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
         * Return timeline data.
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
        protected GetRecordTimelineRequestContextBuilder() {
            super();
        }
/**
         * Copy constructor.
         * @param other the other identity
         */
        protected GetRecordTimelineRequestContextBuilder(AbstractRecordIdentityContext other) {
            super(other);
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public GetRecordTimelineRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public GetRecordTimelineRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param forDate the forDate to set
         */
        public GetRecordTimelineRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return self();
        }
        /**
         * @param forDatesFrame the date frame to set
         */
        public GetRecordTimelineRequestContextBuilder forDatesFrame(Pair<Date, Date> forDatesFrame) {
            this.forDatesFrame = forDatesFrame;
            return self();
        }
        /**
         * @param updatesAfter the updatesAfter to set
         */
        public GetRecordTimelineRequestContextBuilder forUpdatesAfter(Date updatesAfter) {
            this.forUpdatesAfter = updatesAfter;
            return self();
        }
        /**
         * @param forOperationId the forOperationId to set
         */
        public GetRecordTimelineRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return self();
        }
        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public GetRecordTimelineRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return self();
        }
        /**
         * @param keys the keys to set
         */
        public GetRecordTimelineRequestContextBuilder fetchKeys(boolean keys) {
            this.fetchKeys = keys;
            return self();
        }
        /**
         * @param fetchData the fetchData to set
         */
        public GetRecordTimelineRequestContextBuilder fetchData(boolean fetchData) {
            this.fetchData = fetchData;
            return self();
        }
        /**
         * @param skipCalculations the skipCalculations to set
         */
        public GetRecordTimelineRequestContextBuilder skipCalculations(boolean skipCalculations) {
            this.skipCalculations = skipCalculations;
            return self();
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetRecordTimelineRequestContext build() {
            return new GetRecordTimelineRequestContext(this);
        }
    }
}
