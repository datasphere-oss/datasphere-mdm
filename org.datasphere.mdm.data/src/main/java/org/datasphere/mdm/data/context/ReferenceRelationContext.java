

package org.datasphere.mdm.data.context;

import java.util.List;

import org.datasphere.mdm.data.type.data.OriginRelation;
import org.datasphere.mdm.core.type.timeline.Timeline;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov on Dec 9, 2019
 */
public interface ReferenceRelationContext extends StorageCapableContext {
    /**
     * Previous state of the references from this relation type and this record..
     */
    StorageId SID_PREVIOUS_REFERENCES = new StorageId("PREVIOUS_REFERENCES");
    /**
     * Next state of the references from this relation type and this record..
     */
    StorageId SID_NEXT_REFERENCES = new StorageId("NEXT_REFERENCES");
    /**
     * Get previous refs state
     * @return state
     */
    default List<Timeline<OriginRelation>> previousReferences() {
        return getFromStorage(SID_PREVIOUS_REFERENCES);
    }
    /**
     * Get previous refs state
     * @return state
     */
    default List<Timeline<OriginRelation>> nextReferences() {
        return getFromStorage(SID_NEXT_REFERENCES);
    }
    /**
     * Put previous state
     * @param timelines the state
     */
    default void previousReferences(List<Timeline<OriginRelation>> timelines) {
        putToStorage(SID_PREVIOUS_REFERENCES, timelines);
    }
    /**
     * Put previous state
     * @param timelines the state
     */
    default void nextReferences(List<Timeline<OriginRelation>> timelines) {
        putToStorage(SID_NEXT_REFERENCES, timelines);
    }
}
