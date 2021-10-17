

package org.datasphere.mdm.data.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import org.datasphere.mdm.data.po.EtalonRecordDraftStatePO;
import org.datasphere.mdm.data.po.data.RecordEtalonPO;
import org.datasphere.mdm.data.po.data.RecordOriginPO;
import org.datasphere.mdm.data.po.data.RecordOriginRemapPO;
import org.datasphere.mdm.data.po.data.RecordTimelinePO;
import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.datasphere.mdm.data.po.keys.RecordExternalKeysPO;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.datasphere.mdm.data.po.keys.RecordOriginKeyPO;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.core.type.keys.LSN;

/**
 *
 * @author Mikhail Mikhailov
 */
public interface RecordsDAO {
    /**
     * @author Mikhail Mikhailov
     * Type of ids in a list.
     */
    public enum IdSetType {
        /**
         * Etalon IDs.
         */
        ETALON_ID,
        /**
         * External IDs.
         */
        EXTERNAL_ID,
        /**
         * LSNs (of shard number : LSN).
         */
        LSN
    }
    // GET
    /**
     * A system call needed by other DAOs. Subject for removal after FPD.
     * @param extId the external ID
     * @return UUID
     */
    UUID loadSysIdByExternalId(ExternalId extId);
    /**
     * A system call needed by other DAOs. Subject for removal after FPD.
     * @param shard the shard
     * @param lsn the LSN number
     * @return UUID
     */
    UUID loadSysIdByLSN(int shard, long lsn);
    /**
     * Loads keys state by etalon id.
     * @param val etalon id
     * @return keys or null
     */
    RecordKeysPO loadRecordKeysByEtalonId(UUID val);
    /**
     * Loads keys state by external id.
     * @param externalId external id
     * @param sourceSystem source system
     * @param name entity name
     * @return keys or null
     */
    RecordKeysPO loadRecordKeysByExternalId(String externalId, String sourceSystem, String name);
    /**
     * Loads record keys by using supplied LSN.
     * @param shard shard numbe
     * @param lsn the LSN
     * @return keys or null
     */
    RecordKeysPO loadRecordKeysByLSN(int shard, long lsn);
    /**
     * Loads keys for a number of ids, peritioned by key type.
     * @param ids the id map
     * @return keys.
     */
    Map<Object, RecordKeysPO> loadRecordKeys(Map<IdSetType, List<Object>> ids);
    // UPSERT
    /**
     * Updates or insert origin records.
     * @param records the records to upsert
     * @param areNew indicates whether objects are new ones
     */
    void upsertOriginRecords(final List<RecordOriginPO> records, boolean areNew);
    /**
     * Updates or inserts etalon records.
     * @param records the records to upsert
     * @param areNew insert for new, update for existing
     */
    void upsertEtalonRecords(final List<RecordEtalonPO> records, boolean areNew);
    /**
     * Puts several versions at once
     * @param versions the versions to put
     */
    void upsertVersions(List<RecordVistoryPO> versions);
    /**
     * Updates or insert external key records.
     * @param records the records to upsert
     * @param areNew indicates whether objects are new ones or not
     */
    void upsertExternalKeys(final List<RecordExternalKeysPO> records, boolean areNew);
    /**
     * Unordered nerge support. Remap origins.
     * @param records the records to remap.
     */
    void remapOriginRecords(final List<RecordOriginRemapPO> records);
    // BULK
    /**
     * Updates or inserts etalon records.
     * Attention! All the etalon records must belong to the same shard!
     * @param shard the shard number
     * @param records the records to upsert
     * @param areNew insert for new, update for existing
     */
    void bulkInsertEtalonRecords(int shard, final List<RecordEtalonPO> records);
    /**
     * Updates or insert origin records.
     * Attention! All the etalon records must belong to the same shard!
     * @param shard the shard number
     * @param records the records to upsert
     * @param areNew indicates whether objects are new ones
     */
    void bulkInsertOriginRecords(int shard, final List<RecordOriginPO> records);
    /**
     * Inserts external keys, which now reside in a separate table.
     * @param shard the target shard
     * @param records the records
     */
    void bulkInsertExternalKeys(int shard, List<RecordExternalKeysPO> records);
    /**
     * Updates external keys.
     * @param shard the target shard
     * @param records the records
     */
    void bulkUpdateExternalKeys(int shard, List<RecordExternalKeysPO> records);
    /**
     * Updates or inserts etalon records.
     * Attention! All the etalon records must belong to the same shard!
     * @param shard the shard number
     * @param records the records to upsert
     * @param areNew insert for new, update for existing
     */
    void bulkUpdateEtalonRecords(int shard, final List<RecordEtalonPO> records);
    /**
     * Updates or insert origin records.
     * Attention! All the etalon records must belong to the same shard!
     * @param shard the shard number
     * @param records the records to upsert
     * @param areNew indicates whether objects are new ones
     */
    void bulkUpdateOriginRecords(int shard, final List<RecordOriginPO> records);
    /**
     * Puts several versions at once in COPY fashion.
     * @param shard the shard number
     * @param versions the versions to put
     */
    void bulkInsertVersions(int shard, List<RecordVistoryPO> versions);
    /**
     * Merges origin records.
     * @param shard the shard number
     * @param records the records to upsert
     */
    void bulkRemapOriginRecords(int shard, final List<RecordOriginRemapPO> records);
    /**
     * Wipes  data (char data, binary data and etalon) from the storage.
     * @param shard the shard number
     * @param records list to remove
     * @return true, if successful, false otherwise
     */
    void bulkWipeRecordData(int shard, List<RecordKeysPO> records);
    /**
     * Wipes  data (char data, binary data and etalon) from the storage.
     * @param shard the shard number
     * @param records list to remove
     * @return true, if successful, false otherwise
     */
    void bulkWipeExternalKeys(int shard, List<RecordExternalKeysPO> records);
    // END OF BULK
    // WIPE
    /**
     * Wipes  data (char data, binary data and etalon) from the storage.
     * @param records list to remove
     */
    void wipeRecordData(List<RecordKeysPO> records);
    /**
     * Wipes  data (char data, binary data and etalon) from the storage.
     * @param records list to remove
     */
    void wipeExternalIds(List<RecordExternalKeysPO> records);
    // OTHERS (Merge, Approve, etc.)
    /**
     * Does draft upsert.
     * @param drafts the drafts
     */
    void upsertEtalonStateDraft(List<EtalonRecordDraftStatePO> drafts);
    /**
     * Does etalon state cleanup.
     * @param etalonId the relation id
     * @return true if successful
     */
    boolean cleanupEtalonStateDrafts(String etalonId);
    /**
     * Puts etalon state draft.
     * @param etalonId etalon id
     * @param status status, may be null
     * @param createdBy created by, may be null
     * @return
     */
    boolean putEtalonStateDraft(String etalonId, RecordStatus status, String createdBy);
    /**
     * Loads last etalon state draft.
     * @param etalonId the id
     * @return PO object or null
     */
    EtalonRecordDraftStatePO loadLastEtalonStateDraft(String etalonId);
    /**
     * Map of etalon id and list of his active origins
     * @param etalonsIds list of etalons ids
     * @return map of origin id and list of active origins
     */
    Map<String, List<RecordOriginPO>> findAllActiveOriginsForEtlaons(List<String> etalonsIds);
    /**
     * UC: load versions as of date by etalon id. Used for display of a period.
     *
     * @param idVal the etalon id
     * @param fetchKeys will fetch record keys if true
     * @param point     the date
     * @param includeDrafts - is approver
     * @param  userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByEtalonId(UUID idVal, boolean fetchKeys, Date point, boolean includeDrafts, String userName);
    /**
     * UC: load versions as of date and as of last update date 'lud' by etalon id. Used for display of a period.
     *
     * @param idVal the etalon id
     * @param fetchKeys will fetch record keys if true
     * @param point     the date
     * @param lud      lust update date
     * @param loadDrafts - is approver
     * @param  userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByEtalonIdAndLastUpdateDate(UUID idVal, boolean fetchKeys, Date point, Date lud, boolean loadDrafts, String userName);
    /**
     * UC: load versions by etalon id as of date and as of last update date 'lud', but only if one of versions hits had updates after given date. Used for display of a period.
     *
     * @param id the etalon id
     * @param fetchKeys will fetch record keys if true
     * @param point     the date
     * @param updatesAfter has updates after this date
     * @param includeDrafts - is approver
     * @param userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByEtalonIdAndUpdatesAfter(UUID id, boolean fetchKeys, Date point, Date updatesAfter, boolean includeDrafts, String userName);
    /**
     * UC: load versions by etalon id as of date regatding time interval, restricted by operationId. Used for display of a period.
     *
     * @param id the etalon id
     * @param fetchKeys fetch keys or not
     * @param point     the date
     * @param operationId      the operation id
     * @param includeDrafts - is approver
     * @param userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByEtalonIdAndOperationId(UUID id, boolean fetchKeys, Date point, String operationId, boolean includeDrafts, String userName);
    /**
     * UC: load versions as of date by external id. Used for display of a period.
     *
     * @param externalId the external id
     * @param point     the date
     * @param includeDrafts - is approver
     * @param  userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByExternalId(ExternalId externalId, Date point, boolean includeDrafts, String userName);
    /**
     * UC: load versions as of date and as of last update date 'lud'. Used for display of a period.
     *
     * @param externalId the external id
     * @param point     the date
     * @param lud      lust update date
     * @param loadDrafts - is approver
     * @param  userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByExternalIdAndLastUpdateDate(ExternalId externalId, Date point, Date lud, boolean loadDrafts, String userName);
    /**
     * UC: load versions by external id as of date and as of last update date 'lud', but only if one of versions hits had updates after given date. Used for display of a period.
     *
     * @param externalId the external id
     * @param point     the date
     * @param updatesAfter has updates after this date
     * @param includeDrafts - is approver
     * @param userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByExternalIdAndUpdatesAfter(ExternalId externalId, Date point, Date updatesAfter, boolean includeDrafts, String userName);
    /**
     * UC: load versions by external id as of date regatding time interval, restricted by operationId. Used for display of a period.
     *
     * @param externalId the external id
     * @param point     the date
     * @param operationId      the operation id
     * @param includeDrafts - is approver
     * @param userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByExternalIdAndOperationId(ExternalId externalId, Date point, String operationId, boolean includeDrafts, String userName);
    /**
     * UC: load versions as of date by LSN. Used for display of a period.
     *
     * @param lsn the LSN id
     * @param fetchKeys will fetch record keys if true
     * @param point     the date
     * @param includeDrafts - is approver
     * @param  userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByLSN(LSN lsn, boolean fetchKeys, Date point, boolean includeDrafts, String userName);
    /**
     * UC: load versions as of date and as of last update date 'lud'. Used for display of a period.
     *
     * @param lsn the LSN id
     * @param fetchKeys will fetch record keys if true
     * @param point     the date
     * @param lud      lust update date
     * @param loadDrafts - is approver
     * @param  userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByLSNAndLastUpdateDate(LSN lsn, boolean fetchKeys, Date point, Date lud, boolean loadDrafts, String userName);
    /**
     * UC: load versions by LSN id as of date and as of last update date 'lud', but only if one of versions hits had updates after given date. Used for display of a period.
     *
     * @param lsn the LSN id
     * @param fetchKeys will fetch record keys if true
     * @param point     the date
     * @param updatesAfter has updates after this date
     * @param includeDrafts - is approver
     * @param userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByLSNAndUpdatesAfter(LSN lsn, boolean fetchKeys, Date point, Date updatesAfter, boolean includeDrafts, String userName);
    /**
     * UC: load versions by LSN id as of date regatding time interval, restricted by operationId. Used for display of a period.
     *
     * @param lsn the LSN id
     * @param fetchKeys fetch keys or not
     * @param point     the date
     * @param operationId      the operation id
     * @param includeDrafts - is approver
     * @param userName userName
     * @return list of contributing versions
     */
    RecordTimelinePO loadVersionsByLSNAndOperationId(LSN lsn, boolean fetchKeys, Date point, String operationId, boolean includeDrafts, String userName);
    /**
     * UC: load versions without any filters applied.
     * @param etalonId the etalon id
     * @param point date point
     * @return list of top contributing versions
     */
    List<RecordVistoryPO> loadVersionsUnfilterdByEtalonId(String etalonId, Date point);
    /**
     * UC: load history. Load all versions, sorted by source system, external id (origin id), revision number.
     * @param etalonId the etalon id
     * @return map with origin key as a key and its revisions as value
     */
    Map<RecordOriginKeyPO, List<RecordVistoryPO>> loadHistory(String etalonId);
    // TL new
    /**
     * UC: load contributing timeline by etalon id.
     * @param etalonId the etalon id
     * @param fetchKeys whether to fetch keys. This may bie omitted, if the caller already has the keys
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RecordTimelinePO loadTimeline(UUID etalonId, boolean fetchKeys, boolean fetchData, @Nullable Date lastUpdate, @Nullable Date updatesAfter, @Nullable String operationId);
    /**
     * UC: load contributing timeline by external id.
     * @param externalId the external id
     * @param fetchKeys whether to fetch keys. This may bie omitted, if the caller already has the keys
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RecordTimelinePO loadTimeline(ExternalId externalId, boolean fetchKeys, boolean fetchData, @Nullable Date lastUpdate, @Nullable Date updatesAfter, @Nullable String operationId);
    /**
     * UC: load contributing timeline by LSN id.
     * @param lsn the LSN id
     * @param fetchKeys whether to fetch keys. Those may bie omitted, if the caller already has the keys
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timeline
     */
    RecordTimelinePO loadTimeline(LSN lsn, boolean fetchKeys, boolean fetchData, @Nullable Date lastUpdate, @Nullable Date updatesAfter, @Nullable String operationId);
    /**
     * UC: Load contributing timeline for several records.
     * @param etalonIds record ids
     * @param fetchKeys whether to fetch keys. This may bie omitted, if the caller already has the keys
     * @param fetchData whether to load version data or not
     * @param lastUpdate load data view as it was at the time of this last update
     * @param updatesAfter load data view only if it has updates after the given date
     * @param operationId load data view as it was at the time the given operation ID was applied
     * @return timelines
     */
    Map<String, RecordTimelinePO> loadTimelines(List<String> etalonIds, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId);

    /**
     * Updates vistory status.
     * @param shard the shard number
     * @param ids the ids to update
     * @param status the status to set
     * @return true, if successful
     */
    boolean updateVistoryStatus(int shard, List<String> ids, RecordStatus status);
}
