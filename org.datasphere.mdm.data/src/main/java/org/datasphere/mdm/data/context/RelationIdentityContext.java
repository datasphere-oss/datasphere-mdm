

/**
 *
 */
package org.datasphere.mdm.data.context;

import java.util.Objects;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.type.data.RelationType;
import org.datasphere.mdm.data.type.keys.RelationKeys;
import org.datasphere.mdm.data.type.keys.RelationModificationBoxKey;
import org.datasphere.mdm.data.type.keys.RelationOriginKey;
import org.datasphere.mdm.core.type.keys.LSN;
import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov
 * Adds some relation keys to identity context.
 */
public interface RelationIdentityContext extends RecordIdentityContext, RelationModificationBoxKey {
    /**
     * Relation keys SID.
     */
    StorageId SID_RELATION_KEYS = new StorageId("RELATION_KEYS");

    /**
     * Relation name SID.
     */
    StorageId SID_RELATION_NAME =  new StorageId("RELATION_NAME");
    /**
     * Relation type SID.
     */
    StorageId SID_RELATION_TYPE =  new StorageId("RELATION_TYPE");
    /**
     * Gets the relation etalon id.
     * @return the relationEtalonKey
     */
    String getRelationEtalonKey();
    /**
     * Local sequence number supplied by request. May be null.
     * @return the number or null
     */
    @Nullable
    Long getRelationLsn();
    /**
     * Shard number. Part of LSN. May be null.
     * @return the shard number
     */
    @Nullable
    Integer getRelationShard();
    /**
     * The LSN object at whole or null, if nothing is set.
     * @return LSN object or null
     */
    @Nullable
    LSN getRelationLsnAsObject();
    /**
     * Gets the resolution direction of the context.
     * @return resolution relative direction
     */
    RelativeDirection getDirection();
    /**
     * Gets the relation origin id.
     * @deprecated The field is deprecated. Resolution by this field is turned off, since this field doesn't support sharded layout.
     * @return the relationOriginKey
     */
    @Deprecated
    String getRelationOriginKey();
    /**
     * Gets relation keys from context storage.
     * @return keys or null if not set
     */
    default RelationKeys relationKeys() {
        return getFromStorage(SID_RELATION_KEYS);
    }
    /**
     * Gets the keys id.
     * @return keys id
     */
    default void relationKeys(RelationKeys keys) {
        putToStorage(SID_RELATION_KEYS, keys);
    }
    /**
     * Gets resolved relation name.
     * @return name
     */
    default String relationName() {
        return getFromStorage(SID_RELATION_NAME);
    }
    /**
     * Put resolved relation name.
     * @param name the name
     */
    default void relationName(String name) {
        putToStorage(SID_RELATION_NAME, name);
    }
    /**
     * Gets resolved relation type.
     * @return name
     */
    default RelationType relationType() {
        return getFromStorage(SID_RELATION_TYPE);
    }
    /**
     * Put resolved relation type.
     * @param type relation type
     */
    default void relationType(RelationType type) {
        putToStorage(SID_RELATION_TYPE, type);
    }
    /**
     * Tells, whether this context is identified by relation etalon id.
     * @return true if so, false otherwise
     */
    default boolean isRelationEtalonKey() {
        return StringUtils.isNotBlank(getRelationEtalonKey());
    }
    /**
     * Checks for LSN identifier being present.
     * @return true, if so, false otherwise
     */
    default boolean isRelationLsnKey() {
        return Objects.nonNull(getRelationLsnAsObject());
    }
    /**
     * Tells, whether this context is identified by relation origin id.
     * @return true if so, false otherwise
     */
    @Deprecated
    default boolean isRelationOriginKey() {
        return StringUtils.isNotBlank(getRelationOriginKey());
    }
    /**
     * Context is generally usable.
     * @return true if so, false otherwise
     */
    default boolean isValidRelationKey() {
        return this.isRelationEtalonKey()
            || this.isRelationLsnKey()
            || this.isRelationOriginKey();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default String toBoxKey() {

        RelationKeys keys = relationKeys();
        if (Objects.nonNull(keys)) {

            RelationOriginKey key = keys.getOriginKey();
            String externalIdFrom = key.getFrom() != null ? key.getFrom().getExternalId() : null;
            String externalIdTo = key.getTo().getExternalId();
            String sourceSystem = key.getSourceSystem();

            if (Objects.nonNull(externalIdFrom) && Objects.nonNull(externalIdTo) && Objects.nonNull(sourceSystem)) {
                return RelationModificationBoxKey.toRelationBoxKey(externalIdFrom, externalIdTo, sourceSystem);
            }
        }

        return null;
    }
}
