
package org.datasphere.mdm.core.type.data.extended;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * It is a special type, describing attributes mappings.
 * Used for merge preview.
 */
public class ExtendedRecord {
    /**
     * Entity name
     */
    private final String entityName;
    /**
     * Record
     */
    private final DataRecord record;
    /**
     * Map where key is a attribute name and value it is a etalon id or origin id.
     */
    private Map<String, String> attributeWinnersMap = new HashMap<>();

    public ExtendedRecord(@Nonnull DataRecord record, @Nonnull String entityName) {
        this.record = record;
        this.entityName = entityName;
    }

    /**
     * Etalon Record
     *
     * @return
     */
    public DataRecord getRecord() {
        return record;
    }

    /**
     * @return attributeWinnersMap
     */
    public Map<String, String> getAttributeWinnersMap() {
        return attributeWinnersMap;
    }

    /**
     * @param attributeWinnersMap - filled or partially filled  attributeWinnersMap
     */
    public void addAllWinnersAttributes(Map<String, String> attributeWinnersMap) {
        this.attributeWinnersMap.putAll(attributeWinnersMap);
    }

    /**
     * add to attribute winner map new attribute
     *
     * @param recordId - record id
     * @param attrName - attr name
     */
    public void addWinnerAttribute(String attrName, String recordId) {
        attributeWinnersMap.put(attrName, recordId);
    }

    /**
     * Entity name
     *
     * @return
     */
    public String getEntityName() {
        return entityName;
    }
}
