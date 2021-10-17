

package org.datasphere.mdm.core.util;

import java.util.Objects;

import org.datasphere.mdm.core.type.data.Attribute;
import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * @author Mikhail Mikhailov on Jun 7, 2021
 */
public final class DataRecordUtils {

    /**
     * Constructor.
     */
    private DataRecordUtils() {
        super();
    }

    public static DataRecord topLevelFromAttribute(Attribute attr) {

        if (Objects.isNull(attr)) {
            return null;
        }

        return topLevelFromRecord(attr.getRecord());
    }

    public static DataRecord topLevelFromRecord(DataRecord dr) {

        if (Objects.isNull(dr)) {
            return null;
        }

        while (Objects.nonNull(dr) && !dr.isTopLevel()) {
            dr = dr.getParentRecord();
        }

        return dr;
    }
}
