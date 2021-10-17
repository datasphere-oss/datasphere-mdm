

package org.datasphere.mdm.data.context;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.type.keys.RecordEtalonKey;
import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.data.type.keys.RecordOriginKey;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.core.type.keys.LSN;
import org.datasphere.mdm.system.context.AbstractCompositeRequestContext;

/**
 * @author Mikhail Mikhailov
 * Base class, implementing key resolution interface.
 */
public abstract class AbstractRecordIdentityContext extends AbstractCompositeRequestContext
    implements RecordIdentityContext {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -5651795326189175383L;
    /**
     * Etalon key.
     */
    protected final String etalonKey;
    /**
     * Origin key.
     */
    protected final String originKey;
    /**
     * Origin foreign id.
     */
    protected ExternalId externalId;
    /**
     * Local (to partition) sequence number.
     */
    protected final LSN lsn;
    /**
     * Constructor.
     */
    protected AbstractRecordIdentityContext(AbstractRecordIdentityContextBuilder<?> b) {
        super(b);
        this.etalonKey = StringUtils.isBlank(b.etalonKey) ? null : b.etalonKey;
        this.originKey = StringUtils.isBlank(b.originKey) ? null : b.originKey;
        this.lsn = b.lsn != null && b.shard != null
                ? LSN.of(b.shard, b.lsn)
                : null;
        this.externalId = StringUtils.isNotBlank(b.externalId) || StringUtils.isNotBlank(b.sourceSystem) || StringUtils.isNotBlank(b.entityName)
                ? ExternalId.of(StringUtils.trim(b.externalId), StringUtils.trim(b.entityName), StringUtils.trim(b.sourceSystem))
                : null; // Need this to support ext ID (re) setting / id generation.
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getEtalonKey() {
        return etalonKey;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getOriginKey() {
        return originKey;
    }
    /**
     * @return the sourceSystem
     */
    @Override
    public String getSourceSystem() {
        return externalId != null ? externalId.getSourceSystem() : null;
    }
    /**
     * @return the entityName
     */
    @Override
    public String getEntityName() {
        return externalId != null ? externalId.getEntityName() : null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getExternalId() {
        return externalId != null ? externalId.getId() : null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalId getExternalIdAsObject() {
        return externalId;
    }
    /**
     * @return the gsn
     */
    @Override
    public Long getLsn() {
        return lsn != null ? lsn.getLsn() : null;
    }
    /**
     * @return the shard
     */
    @Override
    public Integer getShard() {
        return lsn != null ? lsn.getShard() : null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LSN getLsnAsObject() {
        return lsn;
    }
    /**
     * @author Mikhail Mikhailov
     *
     * @param <X> the concrete builder class
     */
    public abstract static class AbstractRecordIdentityContextBuilder<X extends AbstractRecordIdentityContextBuilder<X>>
        extends AbstractCompositeRequestContextBuilder<X> {
        /**
         * Shard number.
         */
        protected Integer shard;
        /**
         * Global sequence number.
         */
        protected Long lsn;
        /**
         * Etalon key.
         */
        protected String etalonKey;
        /**
         * Origin key.
         */
        protected String originKey;
        /**
         * Origin foreign id.
         */
        protected String externalId;
        /**
         * Entity name.
         */
        protected String entityName;
        /**
         * Source system name.
         */
        protected String sourceSystem;
        /**
         * Constructor.
         */
        protected AbstractRecordIdentityContextBuilder() {
            super();
        }
        /**
         * Constructor.
         * @param other the object to copy
         */
        protected AbstractRecordIdentityContextBuilder(AbstractRecordIdentityContext other) {
            super(other);
            this.etalonKey = other.etalonKey;
            this.originKey = other.originKey;
            this.sourceSystem = other.externalId != null ? other.externalId.getSourceSystem() : null;
            this.entityName = other.externalId != null ? other.externalId.getEntityName() : null;
            this.externalId = other.externalId != null ? other.externalId.getId() : null;
            this.lsn = other.lsn != null ? other.lsn.getLsn() : null;
            this.shard = other.lsn != null ? other.lsn.getShard() : null;
            ofKeys(other.keys());
        }

        private void ofKeys(RecordKeys keys) {

            if (Objects.isNull(keys)) {
                return;
            }

            ofEtalonKey(keys);
            ofOriginKey(keys);
        }

        private void ofEtalonKey(RecordKeys keys) {

            if (Objects.isNull(keys.getEtalonKey())) {
                return;
            }

            if (Objects.isNull(this.etalonKey)) {
                this.etalonKey = keys.getEtalonKey().getId();
            }

            if (Objects.isNull(this.lsn)) {
                this.lsn = keys.getEtalonKey().getLsn();
            }

            if (Objects.isNull(this.shard)) {
                this.shard = keys.getShard();
            }
        }

        private void ofOriginKey(RecordKeys keys) {

            if (Objects.isNull(keys.getOriginKey())) {
                return;
            }

            if (Objects.isNull(this.originKey)) {
                this.originKey = keys.getOriginKey().getId();
            }

            if (Objects.isNull(this.sourceSystem)) {
                this.sourceSystem = keys.getOriginKey().getSourceSystem();
            }

            if (Objects.isNull(this.entityName)) {
                this.entityName = keys.getOriginKey().getEntityName();
            }

            if (Objects.isNull(this.externalId)) {
                this.externalId = keys.getOriginKey().getExternalId();
            }
        }

        /**
         * @param etalonKey the etalonKey to set
         */
        public X etalonKey(String etalonKey) {
            this.etalonKey = etalonKey;
            return self();
        }
        /**
         * @param originKey the etalonKey to set
         */
        public X originKey(String originKey) {
            this.originKey = originKey;
            return self();
        }
        /**
         * @param externalId the externalId to set
         */
        public X externalId(ExternalId externalId) {
            this.externalId = externalId != null ? externalId.getId() : null;
            this.sourceSystem = externalId != null ? externalId.getSourceSystem() : null;
            this.entityName = externalId != null ? externalId.getEntityName() : null;
            return self();
        }
        /**
         * @param externalId the externalId to set
         */
        public X externalId(String externalId) {
            this.externalId = externalId;
            return self();
        }
        /**
         * @param entityName the entityName to set
         */
        public X entityName(String entityName) {
            this.entityName = entityName;
            return self();
        }
        /**
         * @param sourceSystem the sourceSystem to set
         */
        public X sourceSystem(String sourceSystem) {
            this.sourceSystem = sourceSystem;
            return self();
        }
        /**
         * @param gsn the gsn to set
         */
        public X lsn(LSN lsn) {
            this.lsn = lsn != null ? lsn.getLsn() : null;
            this.shard = lsn != null ? lsn.getShard() : null;
            return self();
        }
        /**
         * @param lsn the lsn to set
         */
        public X lsn(Long lsn) {
            this.lsn = lsn;
            return self();
        }
        /**
         * @param shard the shard to set
         */
        public X shard(Integer shard) {
            this.shard = shard;
            return self();
        }
        /**
         * @param etalonKey the etalonKey to set
         */
        public X etalonKey(RecordEtalonKey etalonKey) {
            this.etalonKey = etalonKey != null ? etalonKey.getId() : null;
            return self();
        }
        /**
         * @param originKey the originKey to set
         */
        public X originKey(RecordOriginKey originKey) {
            this.originKey = originKey != null ? originKey.getId() : null;
            this.externalId = originKey != null ? originKey.getExternalId() : null;
            this.sourceSystem = originKey != null ? originKey.getSourceSystem() : null;
            this.entityName = originKey != null ? originKey.getEntityName() : null;
            return self();
        }
    }
}
