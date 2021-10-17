package org.datasphere.mdm.data.context;

import java.util.Objects;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.data.type.keys.RelationModificationBoxKey;
import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov on May 3, 2020
 */
public interface RelationFromIdentityContext extends RelationIdentityContext {
    /**
     * The from record key SID.
     */
    StorageId SID_FROM_KEYS = new StorageId("FROM_KEYS");
    /**
     * Gets from record keys from context storage.
     * @return keys or null if not set
     */
    default RecordKeys fromKeys() {
        return getFromStorage(SID_FROM_KEYS);
    }
    /**
     * Sets the from key.
     * @return keys the keys
     */
    default void fromKeys(RecordKeys keys) {
        putToStorage(SID_FROM_KEYS, keys);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default RelativeDirection getDirection() {
        return RelativeDirection.FROM;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default String toBoxKey() {

        String boxKey = RelationIdentityContext.super.toBoxKey();
        if (Objects.isNull(boxKey)) {

            RecordKeys from = fromKeys();
            RecordKeys to = keys();
            String externalIdFrom = from != null ? from.getOriginKey().getExternalId() : null;
            String externalIdTo = to != null ? to.getOriginKey().getExternalId() : getExternalId();
            String sourceSystem = from != null ? from.getOriginKey().getSourceSystem() : null;

            if (Objects.nonNull(externalIdFrom) && Objects.nonNull(externalIdTo) && Objects.nonNull(sourceSystem)) {
                return RelationModificationBoxKey.toRelationBoxKey(externalIdFrom, externalIdTo, sourceSystem);
            }
        }

        return boxKey;
    }
}
