

package org.datasphere.mdm.data.dto;

import org.datasphere.mdm.data.type.keys.RecordKeys;

/**
 * @author Mikhail Mikhailov
 * Keys join DTO.
 */
public class KeysJoinDTO {
    /**
     * Aknowlegement.
     */
    private final boolean aknowleged;
    /**
     * New keys state.
     */
    private final RecordKeys keys;
    /**
     * Constructor.
     */
    public KeysJoinDTO(boolean aknowleged, RecordKeys keys) {
        super();
        this.aknowleged = aknowleged;
        this.keys = keys;
    }
    /**
     * @return the aknowleged
     */
    public boolean isAknowleged() {
        return aknowleged;
    }
    /**
     * @return the keys
     */
    public RecordKeys getKeys() {
        return keys;
    }

}
