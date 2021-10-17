

package org.datasphere.mdm.core.type.upath;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * @author Mikhail Mikhailov
 * Incomplete path - parent record with the missing attribute.
 */
public class UPathIncompletePath {
    /**
     * Parent record.
     */
    private final DataRecord parent;
    /**
     * Link to missing {@link UPathElement}.
     */
    private final UPathElement element;
    /**
     * Constructor.
     */
    public UPathIncompletePath(DataRecord parent, UPathElement element) {
        super();
        this.parent = parent;
        this.element = element;
    }
    /**
     * @return the parent
     */
    public DataRecord getParent() {
        return parent;
    }
    /**
     * @return the element
     */
    public UPathElement getElement() {
        return element;
    }
    /**
     * Generates local path from content.
     * @return local path
     */
    public String toLocalPath() {

        String parentPath = parent.toLocalPath();
        return StringUtils.isBlank(parentPath)
                ? element.getElement()
                : new StringBuilder()
                    .append(parentPath)
                    .append('.')
                    .append(element.getElement())
                    .toString();
    }
}
