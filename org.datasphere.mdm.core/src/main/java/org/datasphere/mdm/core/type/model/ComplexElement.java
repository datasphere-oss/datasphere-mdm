

package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov
 * Properties for complex attribute and its descedants.
 */
public interface ComplexElement {
    /**
     * Minimum number of records in this complex attribute.
     * @return the min count
     */
    int getMinCount();
    /**
     * Maximum number of records in this complex attribute.
     * @return the max count
     */
    int getMaxCount();
    /**
     * Name of the nested entity, defining attributes structure for this complex attribute.
     * @return nested entity name
     */
    String getNestedEntityName();
}
