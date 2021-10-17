

package org.datasphere.mdm.data.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.data.dto.RelationDigestDTO;
import org.datasphere.mdm.data.po.EtalonRelationDraftStatePO;
import org.datasphere.mdm.data.po.data.RelationEtalonPO;
import org.datasphere.mdm.data.po.data.RelationEtalonRemapFromPO;
import org.datasphere.mdm.data.po.data.RelationEtalonRemapToPO;
import org.datasphere.mdm.data.po.data.RelationOriginPO;
import org.datasphere.mdm.data.po.data.RelationOriginRemapPO;
import org.datasphere.mdm.data.po.data.RelationTimelinePO;
import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.datasphere.mdm.data.po.keys.RelationExternalKeyPO;
import org.datasphere.mdm.data.po.keys.RelationKeysPO;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.core.type.keys.LSN;
import org.datasphere.mdm.meta.type.RelativeDirection;

/**
 * @author Mikhail Mikhailov
 * Relations vistory DAO component.
 */
public interface RelationsDAO {
    // TL new
    /**
     * UC: load timeline by relation etalon id.
     * @param etalonId relation etalon id
     * @param fetchKeys tells whether to fetch keys
     * @param fetchData tells, whether to fetch data
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RelationTimelinePO loadTimeline(UUID etalonId, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);
    /**
     * UC: load contributing timeline by relation LSN id.
     * @param lsn the LSN id
     * @param fetchKeys whether to fetch keys. This may bie omitted, if the caller already has the keys
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RelationTimelinePO loadTimeline(LSN lsn, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);
    /**
     * UC: load contributing timeline by sides LSN id.
     * @param from the from LSN id
     * @param to the to LSN id
     * @param relationName the name
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RelationTimelinePO loadTimeline(LSN from, LSN to, String relationName, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);
    /**
     * UC: load contributing timeline by sides {@link ExternalId} id.
     * @param from the from {@link ExternalId} id
     * @param to the to {@link ExternalId} id
     * @param relationName the name
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RelationTimelinePO loadTimeline(ExternalId from, ExternalId to, String relationName, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);
    /**
     * UC: load contributing timeline by sides etalon id.
     * @param from the from etalon id
     * @param to the to etalon id
     * @param relationName the name
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RelationTimelinePO loadTimeline(UUID from, UUID to, String relationName, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);
    /**
     * UC: load timelines by to or from side etalon id.
     * @param recordEtalonId the side etalon id
     * @param names relation names (otherwise all)
     * @param isToSide to or from side
     * @param fetchData whether to load version data or not
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timelines
     */
    List<RelationTimelinePO> loadTimelines(UUID recordEtalonId, List<String> names, boolean isToSide, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);
    /**
     * Does origin relations upsert operation.
     * @param origins origin objects
     * @param isNew do either insert or update
     */
    void upsertOriginRelations(List<RelationOriginPO> origins, boolean isNew);
    /**
     * Does etalon relations upsert operation.
     * @param etalons the etalon object
     * @param isNew is new or not
     */
    void upsertEtalonRelations(List<RelationEtalonPO> etalons, boolean isNew);
    /**
     * Upserts from ext. keys
     * @param keys the keys
     * @param isNew new or not
     */
    void upsertFromExternalKeys(List<RelationExternalKeyPO> keys, boolean isNew);
    /**
     * Upserts to ext. keys
     * @param keys the keys
     * @param isNew new or not
     */
    void upsertToExternalKeys(List<RelationExternalKeyPO> keys, boolean isNew);
    /**
     * Draft states.
     * @param drafts the drafts
     */
    void upsertEtalonStateDraft(List<EtalonRelationDraftStatePO> drafts);
    /**
     * Bulk inserts relation etalon records to a target table via BCOPY.
     * @param shard  the shard number
     * @param records the records to insert
     */
    void bulkInsertEtalonRecords(int shard, List<RelationEtalonPO> records);
    /**
     * Bulk inserts relation origin records to a target table via BCOPY.
     * @param shard  the shard number
     * @param records the records to insert
     */
    void bulkInsertOriginRecords(int shard, List<RelationOriginPO> records);
    /**
     * Bulk inserts relation vistory records to a target table via BCOPY.
     * @param shard  the shard number
     * @param versions the records to insert
     */
    void bulkInsertVersions(int shard, List<RelationVistoryPO> versions);
    /**
     * Bulk insert from side "external keys" - from - name - to mappings.
     * @param shard the shard number
     * @param records the records
     */
    void bulkInsertFromExternalKeys(int shard, List<RelationExternalKeyPO> records);
    /**
     * Bulk insert to side "external keys" - to - name - from mappings.
     * @param shard the shard number
     * @param records the records
     */
    void bulkInsertToExternalKeys(int shard, List<RelationExternalKeyPO> records);
    /**
     * Bulk inserts relation etalon records to mass update tables.
     * @param shard  the shard number
     * @param records the records
     */
    void bulkUpdateEtalonRecords(int shard, List<RelationEtalonPO> records);
    /**
     * Bulk update relation etalon records to mass update tables.
     * @param shard  the shard number
     * @param records the records
     */
    void bulkUpdateOriginRecords(int shard, List<RelationOriginPO> records);
    /**
     * Bulk wipe reldata.
     * @param shard the shard number
     * @param keys the keys to wipe
     */
    void bulkWipeRelationData(int shard, List<RelationKeysPO> keys);
    /**
     * Bulk wipe from side keys.
     * @param shard the target shard
     * @param records the keys
     */
    void bulkWipeFromExternalKeys(int shard, List<RelationExternalKeyPO> records);
    /**
     * Bulk wipe to side keys.
     * @param shard the target shard
     * @param records the keys
     */
    void bulkWipeToExternalKeys(int shard, List<RelationExternalKeyPO> records);
    /**
     * Bulk remap 'from' side of several relations from a looser duplicate to new 'from' winner id.
     * @param shard the target shard number
     * @param records the records to process
     */
    void bulkRemapEtalonFromRecords(int shard, List<RelationEtalonRemapFromPO> records);
    /**
     * Bulk remap 'to' side of several relations from a looser duplicate to new 'to' winner id.
     * @param shard the target shard number
     * @param records the records to process
     */
    void bulkRemapEtalonToRecords(int shard, List<RelationEtalonRemapToPO> records);
    /**
     * Bulk remap (reparent from one etalon id -> another etalon id) origins of several relations from looser duplicates to new winner ids.
     * @param shard the target shard number
     * @param records the records to process
     */
    void bulkRemapOriginRecords(int shard, List<RelationOriginRemapPO> records);
    /**
     * Does etalon state cleanup.
     * @param relationEtalonId the relation id
     * @return true if successful
     */
    boolean cleanupEtalonStateDrafts(UUID relationEtalonId);
    /**
     * Loads last etalon state draft.
     * @param etalonId the id
     * @return PO object or null
     */
    EtalonRelationDraftStatePO loadLastEtalonStateDraft(String etalonId);

    /**
     * Puts a number of relations versions.
     * @param versions list of versions
     * @return true, if successful, false otherwise
     */
    void upsertVersions(List<RelationVistoryPO> versions);
    /**
     * Loads relations versions by etalon ID, relation name, view side, from and to dates and list of allowed statuses.
     * @param recordEtalonId the etalon id
     * @param relationName relation name
     * @param sourceSystems source systems
     * @param date from date
     * @param count limit the result with the given count
     * @param from start from the given record
     * @return map
     */
    // FIXME Move DTO from here
    RelationDigestDTO loadDigestDestinationEtalonIds(
            UUID recordEtalonId, String relationName, RelativeDirection viewSide, Map<String, Integer> sourceSystems, Date date, int count, int from);
    /**
     * Loads all versions for a relation etalon id (needed for etalon calculation).
     * @param relationEtalonId _relation_ etalon id
     * @param asOf as of date
     * @param includeDraftVersions include draft versions into view or not
     * @return list of versions
     */
    RelationTimelinePO loadRelationVersions(UUID relationEtalonId, Date asOf, boolean includeDraftVersions);
    /**
     * Loads versions by operation id.
     * @param relationEtalonId
     * @param asOf
     * @param operationId
     * @param includeDraftVersions
     * @return
     */
    RelationTimelinePO loadRelationVersions(UUID relationEtalonId, Date asOf, Date lud, boolean includeDraftVersions);
    /**
     * Loads versions by operation id.
     * @param relationEtalonId
     * @param asOf
     * @param operationId
     * @param includeDraftVersions
     * @return
     */
    RelationTimelinePO loadRelationVersions(UUID relationEtalonId, Date asOf, String operationId, boolean includeDraftVersions);
    /**
     * Loads all versions for a relation LSN (needed for etalon calculation).
     * @param relationLSN _relation_ etalon LSN
     * @param asOf as of date
     * @param includeDraftVersions include draft versions into view or not
     * @return list of versions
     */
    RelationTimelinePO loadRelationVersions(LSN relationLSN, Date asOf, boolean includeDraftVersions);
    /**
     * Loads versions by operation id.
     * @param relationLSN
     * @param asOf
     * @param operationId
     * @param includeDraftVersions
     * @return
     */
    RelationTimelinePO loadRelationVersions(LSN relationLSN, Date asOf, Date lud, boolean includeDraftVersions);
    /**
     * Loads versions by operation id.
     * @param relationLSN
     * @param asOf
     * @param operationId
     * @param includeDraftVersions
     * @return
     */
    RelationTimelinePO loadRelationVersions(LSN relationLSN, Date asOf, String operationId, boolean includeDraftVersions);

    /**
     * Returns etalon relations by from etalon id and status.
     * @param recordEtalonId the etalon id
     * @param statuses statuses
     * @param side the side of the given etalon id
     * @return list
     */
    List<RelationEtalonPO> loadEtalonRelations(
            UUID recordEtalonId, String relationName, List<RecordStatus> statuses, RelativeDirection side);
    /**
     * Loads relation etalon ids, mapped to their relname.
     * @param recordEtalonId the record id
     * @param relationNames relation names. May be empty. All relations are fetched in this case
     * @param side from or to
     * @return map
     */
    Map<String, List<UUID>> loadMappedRelationEtalonIds(UUID recordEtalonId, Collection<String> relationNames, RelativeDirection side);
    /**
     * Load relations that have etalon id as a 'To' key.
     *
     * @param relationEtalonIds
     *            etalon id.
     * @return List with relations.
     */
    List<RelationEtalonPO> loadEtalonRelations(List<UUID> relationEtalonIds);
    /**
     * Returns count by rel name.
     * @param recordEtalonId
     * @param names
     * @param status
     * @return
     */
    Collection<Pair<String, Long>> countEtalonsByFromNamesAndStatus(UUID recordEtalonId, List<String> names, RecordStatus status);
    /**
     * Check exist data for relation by name
     * @param relName relation name
     * @return true if data exist, else false
     */
    boolean checkHasDataByRelationName(String relName);
    /**
     * Remaps a relation from one owner to another by from side.
     * @param pos list of objects
     */
    void remapEtalonFromRecords(List<RelationEtalonRemapFromPO> pos);
    /**
     * Remaps a relation from one owner to another by to side.
     * @param pos list of objects
     */
    void remapEtalonToRecords(List<RelationEtalonRemapToPO> pos);
    /**
     * Remap (reparent from one etalon id -> another etalon id) origins of several relations from looser duplicates to new winner ids.
     * @param records the records to process
     */
    void remapOriginRecords(List<RelationOriginRemapPO> records);
    /**
     * Loads keys (both relation and sides) by relation LSN id.
     * @param shard the shard
     * @param lsn the LSN
     * @return keys
     */
    RelationKeysPO loadKeysByLSN(int shard, long lsn);
    /**
     * Loads keys (both relation and sides) by relation etalon id.
     * @param etalonId etalon id
     * @return keys
     */
    RelationKeysPO loadKeysByEtalonId(UUID etalonId);
    /**
     * Loads relation keys (both relation and sides) by relation name and sides external ids.
     * @param fromId external id of the from side
     * @param toId external id of the to side
     * @param relationName name of the relation
     * @return keys
     */
    RelationKeysPO loadKeysByRecordsExternalIds(ExternalId fromId, ExternalId toId, String relationName);
    /**
     * Loads relation keys (both relation and sides) by record etalon ids.
     * @param etalonIdFrom etalon id from
     * @param etalonIdTo etalon id to
     * @param relationName name of the relation
     * @return keys
     */
    RelationKeysPO loadKeysByRecordsEtalonIds(UUID etalonIdFrom, UUID etalonIdTo, String relationName);
    /**
     * Loads relation keys (both relation and sides) by record etalon side LSN ids.
     * @param fromShard the from side shard
     * @param fromLSN the from side LSN
     * @param toShard the to side shard
     * @param toLSN the to side LSN
     * @param relationName name of the relation
     * @return keys
     */
    RelationKeysPO loadKeysByRecordsLSNs(int fromShard, long fromLSN, int toShard, long toLSN, String relationName);
    /**
     * Loads all keys by record etalon id.
     * @param recordEtalonId the record etalon id
     * @return collection of keys
     */
    List<RelationKeysPO> loadAllKeysByRecordEtalonId(UUID recordEtalonId, RelativeDirection side);
    /**
     * Wipes relation records.
     * @param relations the relation records to wipe
     */
    void wipeRelationData(List<RelationKeysPO> relations);
    /**
     * Wipes the from side
     * @param keys the keys.
     */
    void wipeFromExternalKeys(List<RelationExternalKeyPO> keys);
    /**
     * Wipes the to side.
     * @param keys the keys
     */
    void wipeToExternalKeys(List<RelationExternalKeyPO> keys);
    /**
     * Mark all relation with relationName as INACTIVE
     * @param relationName - relation name
     */
    // void deactivateRelationsByName(String relationName);
}
