
package org.datasphere.mdm.data.configuration;

import org.datasphere.mdm.data.module.DataModule;
import org.datasphere.mdm.system.type.namespace.NameSpace;
import org.datasphere.mdm.system.util.TextUtils;

/**
 * @author Mikhail Mikhailov on Mar 26, 2021
 * Data namespaces.
 */
public final class DataNamespace {
    /**
     * Lookups namespace.
     */
    public static final NameSpace LOOKUP = NameSpace.of("lookup", DataModule.MODULE_ID,
            () -> TextUtils.getText(DataModule.MODULE_ID + ".lookup.namespace.display.name"),
            () -> TextUtils.getText(DataModule.MODULE_ID + ".lookup.namespace.description"));
    /**
     * Registers namespace.
     */
    public static final NameSpace REGISTER = NameSpace.of("register", DataModule.MODULE_ID,
            () -> TextUtils.getText(DataModule.MODULE_ID + ".register.namespace.display.name"),
            () -> TextUtils.getText(DataModule.MODULE_ID + ".register.namespace.description"));
    /**
     * Relations namespace.
     */
    public static final NameSpace RELATION = NameSpace.of("relation", DataModule.MODULE_ID,
            () -> TextUtils.getText(DataModule.MODULE_ID + ".relation.namespace.display.name"),
            () -> TextUtils.getText(DataModule.MODULE_ID + ".relation.namespace.description"));
    /**
     * The values as array.
     */
    private static final NameSpace[] VALUES = new NameSpace[] {
            LOOKUP,
            REGISTER,
            RELATION
    };
    /**
     * Constructor.
     */
    private DataNamespace() {
        super();
    }
    /**
     * Gets values at whole.
     * @return values
     */
    public static NameSpace[] values() {
        return VALUES;
    }
}
