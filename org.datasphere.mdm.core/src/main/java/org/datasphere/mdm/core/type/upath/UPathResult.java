

package org.datasphere.mdm.core.type.upath;

import java.util.ArrayList;
import java.util.List;

import org.datasphere.mdm.core.type.data.Attribute;

/**
 * @author Mikhail Mikhailov
 * Simple upath result.
 */
public class UPathResult {
    /**
     * Collection mode.
     */
    private final UPathApplicationMode mode;
    /**
     * Attributes.
     */
    private final List<Attribute> attributes = new ArrayList<>(8);
    /**
     * Incomplete paths.
     */
    private final List<UPathIncompletePath> incomplete = new ArrayList<>(8);
    /**
     * Constructor.
     * @param mode the mode, which has been used while attribute collecting.
     */
    public UPathResult(UPathApplicationMode mode) {
        super();
        this.mode = mode;
    }
    /**
     * @return the attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }
    /**
     * @return the incomplete
     */
    public List<UPathIncompletePath> getIncomplete() {
        return incomplete;
    }
    /**
     * @return the mode
     */
    public UPathApplicationMode getMode() {
        return mode;
    }
    /**
     * Simple empty indicator.
     * @return true, if both records and attributes are empty
     */
    public boolean isEmpty() {
        return attributes.isEmpty() && incomplete.isEmpty();
    }
}
