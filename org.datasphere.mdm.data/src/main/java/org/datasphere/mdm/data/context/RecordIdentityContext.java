

package org.datasphere.mdm.data.context;

import java.util.Objects;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.data.type.keys.RecordModificationBoxKey;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.core.type.keys.LSN;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov
 *
 * Record identifying context.
 * Fields are used for record identification.
 * The identity is available as {@link RecordKeys} in case of success.
 */
public interface RecordIdentityContext extends StorageCapableContext, RecordModificationBoxKey {
    /**
     * Record keys SID.
     */
    StorageId SID_RECORD_KEYS = new StorageId("RECORD_KEYS");
    /**
     * Resolved record keys.
     * Null for new and invalid records.
     * @return {@link RecordKeys} instance
     */
    @Nullable
    default RecordKeys keys() {
        return getFromStorage(SID_RECORD_KEYS);
    }
    /**
     * Puts the record keys.
     * @param keys the keys to save
     */
    default void keys(RecordKeys keys) {
        putToStorage(SID_RECORD_KEYS, keys);
    }
    /**
     * System etalon key, supplied by request for identification. May be null.
     * @return the etalon key
     */
    @Nullable
    String getEtalonKey();
    /**
     * System origin key, supplied by request for identification. May be null.
     * @deprecated The field is deprecated. Resolution by this field is turned off, since this field doesn't support sharded layout.
     * @return the origin key
     */
    @Deprecated(forRemoval = true)
    @Nullable
    String getOriginKey();
    /**
     * Returns source system external identifier supplied by request for identification.
     * Part of origin key.
     * May be null.
     * @return the source system external identifier
     */
    @Nullable
    String getExternalId();
    /**
     * Returns the entity (register / dictionary) identifier supplied by request for identification.
     * Part of origin key.
     * May be null.
     * @return the entity (register / dictionary) identifier
     */
    @Nullable
    String getEntityName();
    /**
     * Returns the source system name supplied by request for identification.
     * Part of origin key.
     * May be null.
     * @return the source system name
     */
    @Nullable
    String getSourceSystem();
    /**
     * Gets external id as object
     * @return external id as object or null
     */
    @Nullable
    ExternalId getExternalIdAsObject();
    /**
     * Local sequence number supplied by request. May be null.
     * @return the number or null
     */
    @Nullable
    Long getLsn();
    /**
     * Shard number. Part of LSN. May be null.
     * @return the shard number
     */
    @Nullable
    Integer getShard();
    /**
     * The LSN object at whole or null, if nothing is set.
     * @return LSN object or null
     */
    @Nullable
    LSN getLsnAsObject();
    /**
     * Context is usable.
     * @return true if so, false otherwise
     */
    default boolean isValidRecordKey() {
        return this.isEtalonRecordKey()
            || this.isOriginExternalId()
            || this.isLsnKey()
            || this.isOriginRecordKey()
            || this.isEnrichmentKey();
    }
    /**
     * The context is based on an etalon key.
     * @return true if so, false otherwise
     */
    default boolean isEtalonRecordKey() {
        return StringUtils.isNotBlank(getEtalonKey());
    }
    /**
     * The context is based on an origin key.
     * @return true if so, false otherwise
     */
    @Deprecated
    default boolean isOriginRecordKey() {
        return StringUtils.isNotBlank(getOriginKey());
    }
    /**
     * The context is based on an external id, source system and entity name combination.
     * @return true if so, false otherwise
     */
    default boolean isOriginExternalId() {
        return Objects.nonNull(getExternalIdAsObject()) && getExternalIdAsObject().isValid();
    }
    /**
     * Checks for GSN identifier being present.
     * @return true, if so, false otherwise
     */
    default boolean isLsnKey() {
        return Objects.nonNull(getLsnAsObject());
    }
    /**
     * The context has a special enrichment identity.
     * @return true if the context is an enrichment, false otherwise
     */
    default boolean isEnrichmentKey() {
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default String toBoxKey() {
        RecordKeys keys = keys();
        String selectedSourceSystem = keys == null ? getSourceSystem() : keys.getOriginKey().getSourceSystem();
        String selectedExternalId = keys == null ? getExternalId() : keys.getOriginKey().getExternalId();
        return RecordModificationBoxKey.toRecordBoxKey(selectedSourceSystem, selectedExternalId);
    }
}
