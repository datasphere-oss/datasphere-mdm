package org.datasphere.mdm.data.context;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.data.service.segments.relations.restore.RelationRestoreStartExecutor;
import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * @author Mikhail Mikhailov on May 3, 2020
 */
public class RestoreToRelationRequestContext
    extends AbstractRelationIdentityContext
    implements
            RelationToIdentityContext,
            RelationRestoreContext {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -5614833553851688251L;
    /**
     * A possibly set draft id.
     */
    private Long draftId;
    /**
     * A possibly set parent draft id.
     */
    private final Long parentDraftId;
    /**
     * Relations to.
     */
    private final transient DataRecord record;
    /**
     * Set range from.
     */
    private Date validFrom;
    /**
     * Set range to.
     */
    private Date validTo;
    /**
     * Last update date to use (optional).
     */
    private final Date lastUpdate;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * Hints.
     */
    private Map<RestoreRelationHint, Object> hints;
    /**
     * Constructor.
     * @param b the builder
     */
    private RestoreToRelationRequestContext(RestoreToRelationRequestContextBuilder b) {
        super(b);
        this.toKeys(b.toKeys);
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;
        this.record = b.record;
        this.forDate = b.forDate;
        this.lastUpdate = b.lastUpdate;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.hints = b.hints;

        flags.set(DataContextFlags.FLAG_IS_PERIOD_RESTORE, b.restorePeriod);
        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RelationRestoreStartExecutor.SEGMENT_ID;
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
     * @return the relation
     */
    @Override
    public DataRecord getRecord() {
        return record;
    }
    /**
     * @return the forDate
     */
    @Override
    public Date getForDate() {
        return forDate;
    }
    /**
     * @return the lastUpdate
     */
    @Override
    public Date getLastUpdate() {
        return lastUpdate;
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
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public<T> T getHint(RestoreRelationHint h) {
        return MapUtils.isEmpty(hints) ? null : (T) hints.get(h);
    }
    /**
     * The builder.
     * @return builder
     */
    public static RestoreToRelationRequestContextBuilder builder() {
        return new RestoreToRelationRequestContextBuilder();
    }
    /**
     * The builder.
     * @author Mikhail Mikhailov on May 3, 2020
     */
    public static class RestoreToRelationRequestContextBuilder extends AbstractRelationIdentityContextBuilder<RestoreToRelationRequestContextBuilder> {
        /**
         * The draft id.
         */
        private Long draftId;
        /**
         * The parent draft id.
         */
        private Long parentDraftId;
        /**
         * Relations to.
         */
        private DataRecord record;
        /**
         * The to-side keys.
         */
        private RecordKeys toKeys;
        /**
         * Set range from.
         */
        private Date validFrom;
        /**
         * Set range to.
         */
        private Date validTo;
        /**
         * The TL point.
         */
        private Date forDate;
        /**
         * Last update date to use (optional).
         */
        private Date lastUpdate;
        /**
         * define that is restore period request.
         */
        private boolean restorePeriod;
        /**
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * Hints.
         */
        private Map<RestoreRelationHint, Object> hints;
        /**
         * Constructor.
         */
        private RestoreToRelationRequestContextBuilder() {
            super();
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public RestoreToRelationRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public RestoreToRelationRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }

        /**
         * @return the relation
         */
        public RestoreToRelationRequestContextBuilder record(DataRecord record) {
            this.record = record;
            return self();
        }
        /**
         * Sets the to-side keys.
         * Relevant for a single rel merge only.
         * In all other cases happens automatically.
         * @param keys the to side keys
         * @return self
         */
        public RestoreToRelationRequestContextBuilder toKeys(RecordKeys keys) {
            this.toKeys = keys;
            return self();
        }
        /**
         * @param validFrom the range from to set
         */
        public RestoreToRelationRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return this;
        }
        /**
         * @param validTo the range to to set
         */
        public RestoreToRelationRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return this;
        }
        /**
         * @param lastUpdate the last update to set
         */
        public RestoreToRelationRequestContextBuilder lastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }
        /**
         * @param forDate the forDate to set
         */
        public RestoreToRelationRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return this;
        }
        /**
         * @param batchUpsert the flag
         * @return self
         */
        public RestoreToRelationRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return this;
        }
        /**
         * define that that is a period restore request.
         * @param periodRestore
         * @return self
         */
        public RestoreToRelationRequestContextBuilder periodRestore(boolean periodRestore) {
            this.restorePeriod = periodRestore;
            return this;
        }
        /**
         * Set restore hint.
         * @param h the hint
         * @param value the value
         */
        public RestoreToRelationRequestContextBuilder hint(RestoreRelationHint h, Object value) {
            if (Objects.nonNull(h)) {
                if (Objects.isNull(hints)) {
                    hints = new EnumMap<>(RestoreRelationHint.class);
                }

                hints.put(h, value);
            }
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public RestoreToRelationRequestContext build() {
            return new RestoreToRelationRequestContext(this);
        }
    }
}
