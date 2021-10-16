
package org.datasphere.mdm.core.service.impl.upath;

import org.datasphere.mdm.core.type.upath.UPathElement;
import org.datasphere.mdm.core.type.upath.UPathElementType;

/**
 * @author Mikhail Mikhailov
 * UPath element base.
 */
public abstract class AbstractElementImpl implements UPathElement {
    /**
     * Path element.
     */
    private final String element;
    /**
     * Type of element.
     */
    private final UPathElementType type;
    /**
     * Constructor.
     * @param element the original element
     * @param type element type {@link UPathElementType}.
     */
    protected AbstractElementImpl(String element, UPathElementType type) {
        super();
        this.element = element;
        this.type = type;
    }
    /**
     * @return the element
     */
    @Override
    public String getElement() {
        return element;
    }
    /**
     * @return the type
     */
    @Override
    public UPathElementType getType() {
        return type;
    }
}