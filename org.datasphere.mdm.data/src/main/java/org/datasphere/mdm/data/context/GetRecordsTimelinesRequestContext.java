
package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.system.context.CommonRequestContext;

/**
 * @author Mikhail Mikhailov
 * Fetch timeline for a record.
 */
public class GetRecordsTimelinesRequestContext extends CommonRequestContext implements TimelineQueryContext {
    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = -6826317454436803507L;
    /**
     * Etalon key.
     */
    private final List<String> etalonKeys;
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
    protected GetRecordsTimelinesRequestContext(GetRecordsTimelineRequestContextBuilder b) {

        super(b);
        this.etalonKeys = new ArrayList<>(b.etalonKeys);
        this.forDate = b.forDate;
        this.forDatesFrame = b.forDatesFrame;
        this.forUpdatesAfter = b.forUpdatesAfter;
        this.forLastUpdate = b.forLastUpdate;
        this.forOperationId = b.forOperationId;

        // Flags
        flags.set(DataContextFlags.FLAG_INCLUDE_DRAFTS, b.includeDrafts);
        flags.set(DataContextFlags.FLAG_FETCH_KEYS, b.fetchKeys);
        flags.set(DataContextFlags.FLAG_FETCH_TIMELINE_DATA, b.fetchData);
        flags.set(DataContextFlags.FLAG_SKIP_TIMELINE_CALCULATIONS, b.skipCalculations);
    }
    /**
     * @return the etalonKey
     */

    public List<String> getEtalonKeys() {
        return etalonKeys;
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
     * @return the unpublishedState
     */
    public boolean isIncludeDrafts() {
        return flags.get(DataContextFlags.FLAG_INCLUDE_DRAFTS);
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
     * {@inheritDoc}
     */
    public Collection<RecordKeys> keys() {
        return getFromStorage(RecordIdentityContext.SID_RECORD_KEYS);
    }
    /**
     * {@inheritDoc}
     */
    public void keys(Collection<RecordKeys> keys) {
        putToStorage(RecordIdentityContext.SID_RECORD_KEYS, keys);
    }
    /**
     * Builder shorthand.
     * @return builder
     */
    public static GetRecordsTimelineRequestContextBuilder builder() {
        return new GetRecordsTimelineRequestContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class GetRecordsTimelineRequestContextBuilder
        extends CommonRequestContextBuilder<GetRecordsTimelineRequestContextBuilder> {
        /**
         * Keys.
         */
        private Set<String> etalonKeys = new HashSet<>();
        /**
         * For a particular date (as of).
         */
        private Date forDate;
        /**
         * For a particular date range (left <-> right).
         */
        private Pair<Date, Date> forDatesFrame;
        /**
         * Has updates (new versions) after this date.
         */
        private Date forUpdatesAfter;
        /**
         * Last update date to cut off versions.
         */
        private Date forLastUpdate;
        /**
         * Operation id.
         */
        private String forOperationId;
        /**
         * View unpublished state or not.
         */
        private boolean includeDrafts;
        /**
         * Return keys.
         */
        private boolean fetchKeys = true;
        /**
         * Return timeline data.
         */
        private boolean fetchData;
        /**
         * Skip etalon, activity, operation type calculations.
         * Return raw timeline.
         */
        private boolean skipCalculations;
        /**
         * Constructor.
         */
        protected GetRecordsTimelineRequestContextBuilder() {
            super();
        }
        /**
         * @param etalonKey the etalonKey to set
         */
        public GetRecordsTimelineRequestContextBuilder etalonKeys(String... etalonKey) {
            if (ArrayUtils.isNotEmpty(etalonKey)) {
                return etalonKeys(Arrays.asList(etalonKey));
            }
            return self();
        }
        /**
         * @param etalonKeys the etalonKeys to set
         */
        public GetRecordsTimelineRequestContextBuilder etalonKeys(Collection<String> etalonKeys) {
            if (CollectionUtils.isNotEmpty(etalonKeys)) {
                this.etalonKeys.addAll(etalonKeys);
            }
            return self();
        }
        /**
         * @param forDate the forDate to set
         */
        public GetRecordsTimelineRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return self();
        }
        /**
         * @param forDatesFrame the date frame to set
         */
        public GetRecordsTimelineRequestContextBuilder forDatesFrame(Pair<Date, Date> forDatesFrame) {
            this.forDatesFrame = forDatesFrame;
            return self();
        }
        /**
         * @param updatesAfter the updatesAfter to set
         */
        public GetRecordsTimelineRequestContextBuilder updatesAfter(Date updatesAfter) {
            this.forUpdatesAfter = updatesAfter;
            return self();
        }
        /**
         * @param forOperationId the forOperationId to set
         */
        public GetRecordsTimelineRequestContextBuilder forOperationId(String forOperationId) {
            this.forOperationId = forOperationId;
            return self();
        }
        /**
         * Sets last update date to the context.
         * @param lastUpdate the date
         * @return self
         */
        public GetRecordsTimelineRequestContextBuilder forLastUpdate(Date lastUpdate) {
            this.forLastUpdate = lastUpdate;
            return self();
        }
        /**
         * Request unpublished state of a record or not.
         * @param includeDrafts requested state
         * @return self
         */
        public GetRecordsTimelineRequestContextBuilder includeDrafts(boolean includeDrafts) {
            this.includeDrafts = includeDrafts;
            return self();
        }
        /**
         * @param fetchTimelineData the fetchTimelineData to set
         */
        public GetRecordsTimelineRequestContextBuilder fetchData(boolean fetchTimelineData) {
            this.fetchData = fetchTimelineData;
            return self();
        }
        /**
         * @param skipCalculations the skipCalculations to set
         */
        public GetRecordsTimelineRequestContextBuilder skipCalculations(boolean skipCalculations) {
            this.skipCalculations = skipCalculations;
            return self();
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public GetRecordsTimelinesRequestContext build() {
            return new GetRecordsTimelinesRequestContext(this);
        }
    }
}
