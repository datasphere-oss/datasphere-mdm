

package org.datasphere.mdm.data.context;

import java.util.List;
import java.util.Map;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.core.type.calculables.Calculable;
import org.datasphere.mdm.core.type.timeline.Timeline;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * Records merge interface.
 * @author Mikhail Mikhailov on Nov 6, 2019
 */
public interface MergeDuplicatesContext<C extends Calculable> extends StorageCapableContext {
    /**
     * Duplicate record keys.
     */
    StorageId SID_MERGE_DUPLICATE_KEYS = new StorageId("MERGE_DUPLICATE_KEYS");
    /**
     * Last duplicate timelines.
     */
    StorageId SID_MERGE_DUPLICATE_TIMELINES = new StorageId("MERGE_DUPLICATE_TIMELINES");
    /**
     * Discarded incomplete record keys.
     */
    StorageId SID_MERGE_DISCARDED_KEYS = new StorageId("MERGE_DISCARDED_KEYS");
    /**
     * GET duplicates.
     * @return key list
     */
    default List<RecordKeys> duplicateKeys() {
        return getFromStorage(SID_MERGE_DUPLICATE_KEYS);
    }
    /**
     * PUT duplicates.
     * @param duplicates key list
     */
    default void duplicateKeys(List<RecordKeys> duplicates) {
        putToStorage(SID_MERGE_DUPLICATE_KEYS, duplicates);
    }
    /**
     * GET discarded.
     * @return key list
     */
    default List<RecordKeys> discardedKeys() {
        return getFromStorage(SID_MERGE_DISCARDED_KEYS);
    }
    /**
     * PUT discarded.
     * @param duplicates key list
     */
    default void discardedKeys(List<RecordKeys> duplicates) {
        putToStorage(SID_MERGE_DISCARDED_KEYS, duplicates);
    }
    /**
     * Get TLs.
     * @return timeline
     */
    default Map<String, Timeline<C>> duplicateTimelines() {
        return getFromStorage(SID_MERGE_DUPLICATE_TIMELINES);
    }
    /**
     * Put TLs.
     * @param timelines timelines map
     */
    default void duplicateTimelines(Map<String, Timeline<C>> timelines) {
        putToStorage(SID_MERGE_DUPLICATE_TIMELINES, timelines);
    }
}
