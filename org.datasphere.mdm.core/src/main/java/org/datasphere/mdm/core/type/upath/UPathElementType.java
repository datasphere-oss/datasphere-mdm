

package org.datasphere.mdm.core.type.upath;

/**
 * @author Mikhail Mikhailov
 * UPath element type.
 */
public enum UPathElementType {
    /**
     * The expression is an array subscript.
     */
    SUBSCRIPT,
    /**
     * The element is an expression (such as attrName{subAttrName:'May match value'} ).
     */
    EXPRESSION,
    /**
     * Just collects attribute values.
     */
    COLLECTOR
}