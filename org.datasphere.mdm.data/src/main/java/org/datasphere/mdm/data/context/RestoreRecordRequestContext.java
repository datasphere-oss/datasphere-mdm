

package org.datasphere.mdm.data.context;

import java.util.Date;

import org.datasphere.mdm.data.service.segments.records.restore.RecordRestoreStartExecutor;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.core.context.DataRecordContext;
import org.datasphere.mdm.core.context.MutableValidityRangeContext;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.system.context.DraftAwareContext;
import org.datasphere.mdm.system.context.DraftIdResettingContext;
import org.datasphere.mdm.system.context.SetupAwareContext;

/**
 * @author Mikhail Mikhailov
 * Record upsert.
 */
public class RestoreRecordRequestContext
    extends AbstractRecordIdentityContext
    implements
        DataRecordContext,
        ExternalIdResettingContext,
        MutableValidityRangeContext,
        ReadWriteTimelineContext<OriginRecord>,
        ReadWriteDataContext<OriginRecord>,
        OperationTypeContext,
        AccessRightContext,
        DraftAwareContext,
        DraftIdResettingContext,
        BatchAwareContext,
        SetupAwareContext {

    /**
     * Generated SVUID.
     */
    private static final long serialVersionUID = 6651928422821780602L;
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
    private final transient DataRecord record;
    /**
     * Last update date to use (optional).
     */
    private final Date lastUpdate;
    /**
     * For a particular date (as of).
     */
    private final Date forDate;
    /**
     * Set range from.
     */
    private Date validFrom;
    /**
     * Set range to.
     */
    private Date validTo;
    /**
     * The box key.
     */
    protected String boxKey;
    /**
     * Constructor.
     */
    protected RestoreRecordRequestContext(RestoreRecordRequestContextBuilder b) {
        super(b);

        this.record = b.record;
        this.forDate = b.forDate;
        this.lastUpdate = b.lastUpdate;
        this.validFrom = b.validFrom;
        this.validTo = b.validTo;
        this.draftId = b.draftId;
        this.parentDraftId = b.parentDraftId;

        flags.set(DataContextFlags.FLAG_IS_PERIOD_RESTORE, b.restorePeriod);
        flags.set(DataContextFlags.FLAG_IS_DATA_RESTORE, b.restoreData);
        flags.set(DataContextFlags.FLAG_BATCH_OPERATION, b.batchOperation);
        flags.set(DataContextFlags.FLAG_IS_MODIFIED, b.modified);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RecordRestoreStartExecutor.SEGMENT_ID;
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
        return record;
    }

    /**
     * @return the forDate
     */
    public Date getForDate() {
        return forDate;
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
     * @return define that is period restore request.
     */
    public boolean isPeriodRestore() {
        return flags.get(DataContextFlags.FLAG_IS_PERIOD_RESTORE);
    }
    /**
     * Special flag, telling the pipeline, that this context is one of the data upserts before final record restore.
     * It is incompatible with periodRestore flag.
     * No record status updates and no validations are performed, if this flag is supplied.
     * @return true, if is data restore, else false
     */
    public boolean isDataRestore() {
        return flags.get(DataContextFlags.FLAG_IS_DATA_RESTORE);
    }
    /**
     * Record modified hint.
     * @return true, if modified, false otherwise
     */
    public boolean isModified() {
        return flags.get(DataContextFlags.FLAG_IS_MODIFIED);
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
     * Builder shortcut.
     * @return builder
     */
    public static RestoreRecordRequestContextBuilder builder() {
        return new RestoreRecordRequestContextBuilder();
    }

    /**
     * Re-packaging builder shorthand.
     * @return builder
     */
    public static RestoreRecordRequestContextBuilder builder(RestoreRecordRequestContext other) {
        return new RestoreRecordRequestContextBuilder(other);
    }

    /**
     * @author Mikhail Mikhailov
     * Context builder.
     */
    public static class RestoreRecordRequestContextBuilder extends AbstractRecordIdentityContextBuilder<RestoreRecordRequestContextBuilder> {
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
        private DataRecord record;
        /**
         * The TL point.
         */
        private Date forDate;
        /**
         * Last update date to use (optional).
         */
        private Date lastUpdate;
        /**
         * Set range from.
         */
        private Date validFrom;
        /**
         * Set range to.
         */
        private Date validTo;
        /**
         * define that is restore period request.
         */
        private boolean restorePeriod;
        /**
         * Special flag, telling the pipeline, that this context is one of the data upserts before final record restore.
         * It is incompatible with periodRestore flag.
         * No record status updates and no validations are performed, if this flag is supplied.
         */
        private boolean restoreData;
        /**
         * Tells the callee, whether the record was modified.
         */
        private boolean modified;
        /**
         * This context is participating in a batch upsert. Collect artifacts instead of upserting immediately.
         */
        private boolean batchOperation;
        /**
         * Constructor.
         */
        protected RestoreRecordRequestContextBuilder() {
            super();
        }
        /**
         * Constructor.
         */
        protected RestoreRecordRequestContextBuilder(RestoreRecordRequestContext other) {
            super(other);
            this.record = other.record;
            this.lastUpdate = other.lastUpdate;
            this.validFrom = other.validFrom;
            this.validTo = other.validTo;
            this.restorePeriod = other.flags.get(DataContextFlags.FLAG_IS_PERIOD_RESTORE);
            this.batchOperation = other.flags.get(DataContextFlags.FLAG_BATCH_OPERATION);
            this.modified = other.getFlag(DataContextFlags.FLAG_IS_MODIFIED);
        }
        /**
         * Sets draft id
         * @param draftId the draft id
         * @return self
         */
        public RestoreRecordRequestContextBuilder draftId(Long draftId) {
            this.draftId = draftId;
            return self();
        }
        /**
         * Sets parent draft id
         * @param parentDraftId the parent draft id
         * @return self
         */
        public RestoreRecordRequestContextBuilder parentDraftId(Long parentDraftId) {
            this.parentDraftId = parentDraftId;
            return self();
        }
        /**
         * @param record the golden record to set
         */
        public RestoreRecordRequestContextBuilder record(DataRecord record) {
            this.record = record;
            return this;
        }
        /**
         * @param lastUpdate the last update to set
         */
        public RestoreRecordRequestContextBuilder lastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }
        /**
         * @param forDate the forDate to set
         */
        public RestoreRecordRequestContextBuilder forDate(Date forDate) {
            this.forDate = forDate;
            return this;
        }
        /**
         * @param batchUpsert the flag
         * @return self
         */
        public RestoreRecordRequestContextBuilder batchOperation(boolean batchUpsert) {
            this.batchOperation = batchUpsert;
            return this;
        }
        /**
         * @param validFrom the range from to set
         */
        public RestoreRecordRequestContextBuilder validFrom(Date validFrom) {
            this.validFrom = validFrom;
            return this;
        }
        /**
         * @param validTo the range to to set
         */
        public RestoreRecordRequestContextBuilder validTo(Date validTo) {
            this.validTo = validTo;
            return this;
        }
        /**
         * Tells, that this context is a period restore request.
         * @param periodRestore the flag
         * @return self
         */
        public RestoreRecordRequestContextBuilder periodRestore(boolean periodRestore) {
            this.restorePeriod = periodRestore;
            return this;
        }
        /**
         * Special flag, telling the pipeline, that this context is one of the data upserts before final record restore.
         * It is incompatible with periodRestore flag.
         * No record status updates and no validations are performed, if this flag is supplied.
         * @param dataRestore is data restore context or not
         */
        public RestoreRecordRequestContextBuilder dataRestore(boolean dataRestore) {
            this.restoreData = dataRestore;
            return self();
        }
        /**
         * Tells the callee, whether the record was modified.
         * @param modified the flag
         * @return self
         */
        public RestoreRecordRequestContextBuilder modified(boolean modified) {
            this.modified = modified;
            return this;
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
            return record;
        }
        /**
         * Builds a context.
         * @return a new context
         */
        @Override
        public RestoreRecordRequestContext build() {
            return new RestoreRecordRequestContext(this);
        }
    }
}
