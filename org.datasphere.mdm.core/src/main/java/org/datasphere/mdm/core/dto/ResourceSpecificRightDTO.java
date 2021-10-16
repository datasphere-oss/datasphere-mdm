
package org.datasphere.mdm.core.dto;

import java.io.Serializable;

import org.datasphere.mdm.core.type.security.Right;

/**
 * @author Mikhail Mikhailov
 * Resource specific rights (extended by merge and resore).
 */
public class ResourceSpecificRightDTO extends RightDTO implements Serializable {

    /** Virtual "restore" right */
    private boolean restore;

    /** Virtual "merge" right */
    private boolean merge;

    /**
     * SVUID.
     */
    private static final long serialVersionUID = -7386646577993018469L;

    /**
     * Constructor.
     */
    public ResourceSpecificRightDTO() {
        super();
    }

    /**
     * Constructor.
     * @param other
     */
    public ResourceSpecificRightDTO(Right other) {
        super(other);
    }

    /**
     * @return the restore
     */
    public boolean isRestore() {
        return restore;
    }

    /**
     * @param restore the restore to set
     */
    public void setRestore(boolean restore) {
        this.restore = restore;
    }

    /**
     * @return the merge
     */
    public boolean isMerge() {
        return merge;
    }

    /**
     * @param merge the merge to set
     */
    public void setMerge(boolean merge) {
        this.merge = merge;
    }
}
