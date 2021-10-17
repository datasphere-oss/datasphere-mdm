

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.List;
import java.util.Map;

import org.datasphere.mdm.data.type.data.EtalonRecord;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.data.type.keys.RecordOriginKey;

/**
 * @author Mikhail Mikhailov
 * Get recordS result DTO.
 */
public class GetRecordsDTO {

    /**
     * 0 or more origin records.
     */
    private List<EtalonRecord> etalons;
    /**
     * Relations.
     */
    private Map<EtalonRecord, Map<RelationStateDTO, List<GetRelationDTO>>> relations;

    // TODO: @Modules
//    /**
//     * Classifiers.
//     */
//    private Map<EtalonRecord, Map<String, List<GetClassifierDTO>>> classifiers;

    /**
     * Origins.
     */
    private Map<EtalonRecord, List<RecordOriginKey>> originKeys;
    /**
     * Origins.
     */
    private Map<EtalonRecord, List<OriginRecord>> origins;
    /**
     * Constructor.
     */
    public GetRecordsDTO() {
        super();
    }
    /**
     * @return the list of {@link EtalonRecord}
     */
    public List<EtalonRecord> getEtalons() {
        return etalons;
    }
    /**
     * @param etalons the etalons to set
     */
    public void setEtalons(List<EtalonRecord> etalons) {
        this.etalons = etalons;
    }
    /**
     * @return the relations
     */
    public Map<EtalonRecord, Map<RelationStateDTO, List<GetRelationDTO>>> getRelations() {
        return relations;
    }
    /**
     * @param relations the relations to set
     */
    public void setRelations(Map<EtalonRecord, Map<RelationStateDTO, List<GetRelationDTO>>> relations) {
        this.relations = relations;
    }

    // TODO: @Modules
//    /**
//     * @return the classifiers
//     */
//    public Map<EtalonRecord, Map<String, List<GetClassifierDTO>>> getClassifiers() {
//        return classifiers;
//    }
//    /**
//     * @param classifiers the classifiers to set
//     */
//    public void setClassifiers(Map<EtalonRecord, Map<String, List<GetClassifierDTO>>> classifiers) {
//        this.classifiers = classifiers;
//    }

    /**
     * @return the origins
     */
    public Map<EtalonRecord, List<OriginRecord>> getOrigins() {
        return origins;
    }
    /**
     * @param origins the origins to set
     */
    public void setOrigins(Map<EtalonRecord, List<OriginRecord>> origins) {
        this.origins = origins;
    }

    /**
     * @return the originKeys
     */
    public Map<EtalonRecord, List<RecordOriginKey>> getOriginKeys() {
        return originKeys;
    }
    /**
     * @param originKeys the originKeys to set
     */
    public void setOriginKeys(Map<EtalonRecord, List<RecordOriginKey>> originKeys) {
        this.originKeys = originKeys;
    }

}
