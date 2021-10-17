package org.datasphere.mdm.data.context;

import java.util.Objects;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.data.type.keys.RelationModificationBoxKey;
import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.system.context.StorageId;

/**
 * The 'TO' resolution context.
 * @author Mikhail Mikhailov on May 3, 2020
 */
public interface RelationToIdentityContext extends RelationIdentityContext {
    /**
     * The to record key SID.
     */
    StorageId SID_TO_KEYS = new StorageId("TO_KEYS");

    /**
     * Gets to record keys from context storage.
     * @return keys or null if not set
     */
    default RecordKeys toKeys() {
        return getFromStorage(SID_TO_KEYS);
    }
    /**
     * Sets the to keys.
     * @return keys the keys
     */
    default void toKeys(RecordKeys keys) {
        putToStorage(SID_TO_KEYS, keys);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default RelativeDirection getDirection() {
        return RelativeDirection.TO;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default String toBoxKey() {

        String boxKey = RelationIdentityContext.super.toBoxKey();
        if (Objects.isNull(boxKey)) {

            RecordKeys from = keys();
            RecordKeys to = toKeys();
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
