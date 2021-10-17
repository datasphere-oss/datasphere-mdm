

package org.datasphere.mdm.core.type.upath;

/**
 * @author Mikhail Mikhailov
 * Some UPath constants.
 */
public final class UPathConstants {
    /**
     * Constructor.
     */
    private UPathConstants() {
        super();
    }
    /**
     * Internal name of the root element
     */
    public static final String UPATH_ROOT_NAME = "{}";
    /**
     * The 'record' constant, used in expression evaluations.
     */
    public static final String UPATH_RECORD_NAME = "record";
    /**
     * Path separator char.
     */
    public static final char UPATH_SEPARATOR_CHAR = '.';
    /**
     * Start of the UPath filtering expression.
     */
    public static final char UPATH_EXPRESSION_START = '{';
    /**
     * End of the UPath filtering expression.
     */
    public static final char UPATH_EXPRESSION_END = '}';
    /**
     * Mid of the UPath filtering expression.
     */
    public static final char UPATH_EXPRESSION_MID = ':';
    /**
     * Start of the UPath subscript expression.
     */
    public static final char UPATH_SUBSCRIPT_START = '[';
    /**
     * End of the UPath subscript expression.
     */
    public static final char UPATH_SUBSCRIPT_END = ']';
    /**
     * UPath escape symbol.
     */
    public static final char UPATH_ESCAPE_CHAR = '\\';
}
