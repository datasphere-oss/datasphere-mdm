

package org.datasphere.mdm.core.type.data.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.core.type.data.Attribute;
import org.datasphere.mdm.core.type.data.TypeOfChange;

/**
 * The attribute diff.
 * @author Mikhail Mikhailov on Nov 1, 2019
 */
public class SimpleAttributesDiff {
    /**
     * The diff table.
     */
    private final Map<TypeOfChange, Map<String, Attribute>> table;
    /**
     * Constructor.
     */
    public SimpleAttributesDiff(Map<TypeOfChange, Map<String, Attribute>> table) {
        super();
        this.table = table;
    }
    /**
     * Empty check.
     * @return true, if empty, false otherwise
     */
    public boolean isEmpty() {
        return MapUtils.isEmpty(table);
    }
    /**
     * Returns diff as table.
     * @return map
     */
    public Map<String, Map<TypeOfChange, Attribute>> asAttributesTable() {
        return invert();
    }
    /**
     * Returns diff as table.
     * @return map
     */
    public Map<TypeOfChange, Map<String, Attribute>> asChangesTable() {
        return MapUtils.isEmpty(table) ? Collections.emptyMap() : table;
    }
    /**
     * Inverts diff map.
    *
    * @param map
    *            the map to invert
    * @return inverted map
    */
   private Map<String, Map<TypeOfChange, Attribute>> invert() {

       if (MapUtils.isEmpty(table)) {
           return Collections.emptyMap();
       }

       Map<String, Map<TypeOfChange, Attribute>> result = new HashMap<>();
       for (Entry<TypeOfChange, Map<String, Attribute>> row : table.entrySet()) {
           for (Entry<String, Attribute> column : row.getValue().entrySet()) {
               result.put(column.getKey(), Collections.singletonMap(row.getKey(), column.getValue()));
           }
       }

       return result;
   }
}
