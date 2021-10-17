

package org.datasphere.mdm.data.dao.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.sql.DataSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.core.dao.template.QueryTemplateDescriptor;
import org.datasphere.mdm.core.dao.template.QueryTemplates;
import org.datasphere.mdm.core.dao.vendor.VendorDataType;
import org.datasphere.mdm.core.dao.vendor.VendorUtils;
import org.datasphere.mdm.core.dao.vendor.VendorUtils.CopyDataOutputStream;
import org.datasphere.mdm.core.type.data.DataShift;
import org.datasphere.mdm.core.type.data.OperationType;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.core.type.keys.LSN;
import org.datasphere.mdm.core.util.SecurityUtils;
import org.datasphere.mdm.data.dao.RecordsDAO;
import org.datasphere.mdm.data.dao.RelationsDAO;
import org.datasphere.mdm.data.dao.rm.EtalonRelationDraftStateRowMapper;
import org.datasphere.mdm.data.dao.rm.EtalonRelationRowMapper;
import org.datasphere.mdm.data.dao.rm.RelationKeysRowMapper;
import org.datasphere.mdm.data.dao.rm.RelationTimelineRowMapper;
import org.datasphere.mdm.data.dao.rm.RelationVistoryRowMapper;
import org.datasphere.mdm.data.dto.RelationDigestDTO;
import org.datasphere.mdm.data.exception.DataExceptionIds;
import org.datasphere.mdm.data.exception.DataProcessingException;
import org.datasphere.mdm.data.po.EtalonRelationDraftStatePO;
import org.datasphere.mdm.data.po.data.RecordTimelinePO;
import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.datasphere.mdm.data.po.data.RelationEtalonPO;
import org.datasphere.mdm.data.po.data.RelationEtalonRemapFromPO;
import org.datasphere.mdm.data.po.data.RelationEtalonRemapToPO;
import org.datasphere.mdm.data.po.data.RelationOriginPO;
import org.datasphere.mdm.data.po.data.RelationOriginRemapPO;
import org.datasphere.mdm.data.po.data.RelationTimelinePO;
import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.datasphere.mdm.data.po.keys.RecordOriginKeyPO;
import org.datasphere.mdm.data.po.keys.RelationExternalKeyPO;
import org.datasphere.mdm.data.po.keys.RelationKeysPO;
import org.datasphere.mdm.data.po.keys.RelationOriginKeyPO;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.serialization.DataSerializer;
import org.datasphere.mdm.data.type.data.RelationType;
import org.datasphere.mdm.data.util.StorageUtils;
import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.system.type.format.DumpTargetFormat;
import org.datasphere.mdm.system.type.runtime.MeasurementPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Mikhailov
 * Relations DAO implementation.
 */
@Repository
public class RelationsDAOImpl extends BaseStorageDAOImpl implements RelationsDAO {
    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RelationsDAOImpl.class);
    /**
     * The DRD.
     */
    @Autowired
    private RecordsDAO dataRecordsDAO;
    /**
     * @author Mikhail Mikhailov
     * The queries.
     */
    enum RelationRecordsQuery implements QueryTemplateDescriptor {
        LOAD_KEYS_BY_LSN_SQL("loadKeysByLSNSQL", true),
        LOAD_KEYS_BY_ETALON_ID_SQL("loadKeysByEtalonIdSQL", true),
        LOAD_SYS_KEY_BY_FROM_TO_AND_NAME_SQL("loadSysKeyByFromToAndNameSQL", true),
        LOAD_SYS_KEYS_BY_FROM_AND_NAMES_SQL("loadSysKeyByFromAndNamesSQL", true),
        LOAD_SYS_KEYS_BY_FROM_SQL("loadSysKeyByFromSQL", true),
        LOAD_SYS_KEYS_BY_TO_AND_NAMES_SQL("loadSysKeyByToAndNamesSQL", true),
        LOAD_SYS_KEYS_BY_TO_SQL("loadSysKeyByToSQL", true),
        LOAD_TIMELINE_BY_ETALON_ID_SQL("loadTimelineByEtalonIdSQL", true),
        LOAD_TIMELINE_BY_LSN_SQL("loadTimelineByLSNSQL", true),
        PUT_RELATION_VERSION_JAXB_SQL("putRelationVersionJaxbSQL", true),
        PUT_RELATION_VERSION_PROTOSTUFF_SQL("putRelationVersionProtostuffSQL", true),
        CHECK_EXIST_BY_RELNAME_SQL("checkExistByRelNameSQL", true),
        LOAD_RELATION_VERSIONS_BY_ETALON_ID_SQL("loadRelationVersionsByEtalonIdSQL", true),
        LOAD_RELATION_VERSIONS_BY_LSN_SQL("loadRelationVersionsByLSNSQL", true),
        LOAD_ETALON_RELATIONS_BY_IDS_SQL("loadEtalonRelationsByIdsSQL", true),
        INSERT_ORIGIN_RELATION_SQL("insertOriginRelationSQL", true),
        UPDATE_ORIGIN_RELATION_SQL("updateOriginRelationSQL", true),
        INSERT_ETALON_RELATION_SQL("insertEtalonRelationSQL", true),
        UPDATE_ETALON_RELATION_SQL("updateEtalonRelationSQL", true),
        INSERT_ETALON_RELATION_STATE_DRAFT_SQL("insertEtalonRelationStateDraft", false),
        CLEANUP_ETALON_RELATION_STATE_DRAFT_SQL("cleanupEtalonRelationStateDrafts", false),
        LOAD_LAST_ETALON_RELATION_STATE_DRAFT_SQL("loadLastEtalonRelationStateDraftByEtalonIdSQL", false),
        DELETE_VISTORY_BY_ORIGIN_ID_SQL("deleteVistoryByOriginIdSQL", true),
        DELETE_ORIGIN_BY_ID_SQL("deleteOriginByIdSQL", true),
        DELETE_ETALON_BY_ID_SQL("deleteEtalonByIdSQL", true),
        DELETE_FROM_EXT_IDS_SQL("deleteFromExtIdsSQL", true),
        DELETE_TO_EXT_IDS_SQL("deleteToExtIdsSQL", true),
        CHANGE_ETALONS_STATUS_BATCH_SQL("changeEtalonsStatusBatchSQL", true),
        CHANGE_ORIGINS_STATUS_BATCH_SQL("changeOriginsStatusBatchSQL", true),
        INSERT_FROM_EXTERNAL_KEY_SQL("insertFromExternalKeySQL", true),
        INSERT_TO_EXTERNAL_KEY_SQL("insertToExternalKeySQL", true),
        REMAP_FROM_ETALON_RELATION_SQL("remapFromEtalonRelationSQL", true),
        REMAP_TO_ETALON_RELATION_SQL("remapToEtalonRelationSQL", true),
        REMAP_ORIGIN_RELATION_SQL("remapOriginRelationSQL", false),
        REMAP_VISTORY_RECORDS_SQL("remapVistoryRecordsSQL", false),
        CUT_VISTORY_BY_ORIGIN_ID_SQL("cutVistoryByOriginIdSQL", true)
        ;

        private RelationRecordsQuery(String code, boolean distributed) {
            this.code = code;
            this.distributed = distributed;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public String getCode() {
            return code;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isDistributed() {
            return distributed;
        }

        public static QueryTemplates toTemplates(Properties p) {
            return QueryTemplateDescriptor.toTemplates(RelationRecordsQuery.values(), new EnumMap<>(RelationRecordsQuery.class), p);
        }

        /**
         * The code.
         */
        private String code;
        /**
         * Distributed or not.
         */
        private boolean distributed;
    }
    /**
     * The templates.
     */
    private QueryTemplates templates;
    /**
     * External utility support.
     */
    @Autowired
    public RelationsDAOImpl(
            @Qualifier("storageDataSource") final DataSource dataSource,
            @Qualifier("relations-sql") Properties sql) {
        super(dataSource);
        templates = RelationRecordsQuery.toTemplates(sql);
        hook(this::initHook);
    }

    /**
     * Processes partition templates, when the cluster gets initialized.
     * @return true, if successful, false otherwise
     */
    private void initHook(DataClusterPO cluster) {
        templates.init(cluster.getNumberOfShards());
    }
    //-------------------------- Keys -------------------------------------------------------------
    private UUID loadSysIdByFromToAndName(UUID from, UUID to, String name) {
        MeasurementPoint.start();
        try {
            int shard = StorageUtils.shard(from);
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(RelationRecordsQuery.LOAD_SYS_KEY_BY_FROM_TO_AND_NAME_SQL, shard),
                    rs -> rs.next() ? rs.getObject(1, UUID.class) : null,
                    shard, from, name, to);
        } finally {
            MeasurementPoint.stop();
        }
    }

    private Map<String, List<UUID>> loadSysIdsByFromSpec(UUID from, Collection<String> names) {
        MeasurementPoint.start();
        try {

            int shard = StorageUtils.shard(from);
            if (CollectionUtils.isEmpty(names)) {
                return shardSelect(shard)
                        .jdbcTemplate()
                        .query(templates.getQuery(RelationRecordsQuery.LOAD_SYS_KEYS_BY_FROM_SQL, shard),
                            rs -> {

                                Map<String, List<UUID>> result = new HashMap<>();
                                while (rs.next()) {

                                    UUID val = rs.getObject(1, UUID.class);
                                    String name = rs.getString(2);

                                    result.computeIfAbsent(name, k -> new ArrayList<UUID>()).add(val);
                                }

                                return result;
                            },
                            shard, from);
            }

            try (Connection c = shardSelect(shard).dataSource().getConnection()) {

                Array a = c.createArrayOf("varchar", names.toArray());
                return shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_SYS_KEYS_BY_FROM_AND_NAMES_SQL, shard),
                        rs -> {

                            Map<String, List<UUID>> result = new HashMap<>();
                            while (rs.next()) {

                                UUID val = rs.getObject(1, UUID.class);
                                String name = rs.getString(2);

                                result.computeIfAbsent(name, k -> new ArrayList<UUID>()).add(val);
                            }

                            return result;
                        },
                        shard, from, a);

            } catch (SQLException e) {
                LOGGER.warn("Cannot create relation names array.", e);
                return Collections.emptyMap();
            }
        } finally {
            MeasurementPoint.stop();
        }
    }

    private Map<String, List<UUID>> loadSysIdsByToSpec(UUID to, Collection<String> names) {
        MeasurementPoint.start();
        try {

            int shard = StorageUtils.shard(to);
            if (CollectionUtils.isEmpty(names)) {
                return shardSelect(shard)
                        .jdbcTemplate()
                        .query(templates.getQuery(RelationRecordsQuery.LOAD_SYS_KEYS_BY_TO_SQL, shard),
                            rs -> {

                                Map<String, List<UUID>> result = new HashMap<>();
                                while (rs.next()) {

                                    UUID val = rs.getObject(1, UUID.class);
                                    String name = rs.getString(2);

                                    result.computeIfAbsent(name, k -> new ArrayList<UUID>()).add(val);
                                }

                                return result;
                            },
                            shard, to);
            }

            try (Connection c = shardSelect(shard).dataSource().getConnection()) {

                Array a = c.createArrayOf("varchar", names.toArray());
                return shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_SYS_KEYS_BY_TO_AND_NAMES_SQL, shard),
                        rs -> {

                            Map<String, List<UUID>> result = new HashMap<>();
                            while (rs.next()) {

                                UUID val = rs.getObject(1, UUID.class);
                                String name = rs.getString(2);

                                result.computeIfAbsent(name, k -> new ArrayList<UUID>()).add(val);
                            }

                            return result;
                        },
                        shard, to, a);

            } catch (SQLException e) {
                LOGGER.warn("Cannot create relation names array.", e);
                return Collections.emptyMap();
            }
        } finally {
            MeasurementPoint.stop();
        }
    }

    // TODO: Remove this after FPD implemented.
    private RecordKeysPO postLoadRecordKeys(String id) {
        return dataRecordsDAO.loadRecordKeysByEtalonId(UUID.fromString(id));
    }

    // TODO: Remove this after FPD implemented.
    private void postProcessContainmentKeys(RelationKeysPO keys) {

        if (keys.getRelationType() != RelationType.CONTAINS) {
            return;
        }

        RecordKeysPO toKeys = keys.getToKeys();
        List<Pair<RelationOriginKeyPO, RecordOriginKeyPO>> ordered = toKeys.getOriginKeys()
                .stream()
                .map(rokpo -> {

                    RelationOriginKeyPO match = keys.getOriginKeys().stream()
                        .filter(relpo -> relpo.getToKey().equals(rokpo.getId()))
                        .findFirst().orElse(null);

                    return Pair.of(match, rokpo);
                })
                .collect(Collectors.toList());

        for (Pair<RelationOriginKeyPO, RecordOriginKeyPO> hit : ordered) {

            if (hit.getKey() != null) {

                hit.getKey().setRevision(hit.getValue().getRevision());
                hit.getKey().setUpdateDate(hit.getValue().getUpdateDate());
                hit.getKey().setUpdatedBy(hit.getValue().getUpdatedBy());
            } else {

                RecordOriginKeyPO enrichment = hit.getValue();
                RelationOriginKeyPO relpo = new RelationOriginKeyPO();
                relpo.setId(enrichment.getId());
                relpo.setInitialOwner(enrichment.getInitialOwner());
                relpo.setCreateDate(enrichment.getCreateDate());
                relpo.setCreatedBy(enrichment.getCreatedBy());
                relpo.setEnrichment(enrichment.isEnrichment());
                relpo.setFromKey(null); // No value for from side
                relpo.setRevision(enrichment.getRevision());
                relpo.setSourceSystem(enrichment.getSourceSystem());
                relpo.setStatus(enrichment.getStatus());
                relpo.setToKey(enrichment.getId());
                relpo.setUpdateDate(enrichment.getUpdateDate());
                relpo.setUpdatedBy(enrichment.getUpdatedBy());

                keys.getOriginKeys().add(relpo);
            }
        }

        keys.setApproved(toKeys.isApproved());
        keys.setUpdateDate(toKeys.getUpdateDate());
        keys.setUpdatedBy(keys.getUpdatedBy());
    }
    /**
     * Loads and converts record versions for 'CONTAINS' type relations.
     * @param timeline the timeline
     */
    // TODO: Remove this after FPD implemented.
    private void postProcessContainmentVersions(RelationTimelinePO timeline, Date asOf, String operationId, boolean includeDraftVersions) {

        // This implies, that a containment timeline
        // must always be loaded together with its keys.
        if (timeline.getKeys().getRelationType() != RelationType.CONTAINS) {
            return;
        }

        RelationKeysPO keys = timeline.getKeys();
        RecordTimelinePO rtl = dataRecordsDAO.loadVersionsByEtalonIdAndOperationId(
                UUID.fromString(keys.getToKeys().getId()),
                false, asOf, operationId, includeDraftVersions, SecurityUtils.getCurrentUserName());

        if (Objects.isNull(rtl) || CollectionUtils.isEmpty(rtl.getVistory())) {
            return;
        }

        Map<String, RelationOriginKeyPO> toKeysToOriginIds = keys.getOriginKeys().stream()
                .collect(Collectors.toMap(rokpo -> rokpo.getToKey().toString(), Function.identity()));

        List<RelationVistoryPO> v = new ArrayList<>();
        for (RecordVistoryPO rvpo : rtl.getVistory()) {

            RelationVistoryPO converted = new RelationVistoryPO();
            converted.setCreateDate(rvpo.getCreateDate());
            converted.setCreatedBy(rvpo.getCreatedBy());
            converted.setData(rvpo.getData());
            converted.setEnrichment(rvpo.isEnrichment());
            converted.setId(rvpo.getId());
            converted.setMajor(rvpo.getMajor());
            converted.setMinor(rvpo.getMinor());
            converted.setName(keys.getName());
            converted.setOperationId(rvpo.getOperationId());
            converted.setOperationType(rvpo.getOperationType());

            RelationOriginKeyPO relationOriginKey = toKeysToOriginIds.get(rvpo.getOriginId());

            converted.setOriginId(relationOriginKey == null || relationOriginKey.getId() == null ? rvpo.getOriginId() : relationOriginKey.getId().toString());
            converted.setOriginStatus(relationOriginKey == null ? null : relationOriginKey.getStatus());
            converted.setRevision(rvpo.getRevision());
            converted.setShard(rvpo.getShard());
            converted.setShift(rvpo.getShift());
            converted.setSourceSystem(rvpo.getSourceSystem());
            converted.setStatus(rvpo.getStatus());
            converted.setUpdateDate(rvpo.getUpdateDate());
            converted.setUpdatedBy(rvpo.getUpdatedBy());
            converted.setValidFrom(rvpo.getValidFrom());
            converted.setValidTo(rvpo.getValidTo());

            v.add(converted);
        }

        timeline.setVistory(v);
    }
    /**
     * Loads and converts record versions for 'CONTAINS' type relations.
     * @param timeline the timeline
     */
    // TODO: Remove this after FPD implemented.
    private void postProcessContainmentTimeline(RelationTimelinePO timeline, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        // This implies, that a containment timeline
        // must always be loaded together with its keys.
        if (timeline.getKeys().getRelationType() != RelationType.CONTAINS) {
            return;
        }

        RelationKeysPO keys = timeline.getKeys();
        RecordTimelinePO rtl = dataRecordsDAO.loadTimeline(
                UUID.fromString(keys.getToKeys().getId()), false, fetchData, lastUpdate, updatesAfter, operationId);

        if (Objects.isNull(rtl) || CollectionUtils.isEmpty(rtl.getVistory())) {
            return;
        }

        Map<String, RelationOriginKeyPO> toKeysToOriginIds = keys.getOriginKeys().stream()
                .collect(Collectors.toMap(rokpo -> rokpo.getToKey().toString(), Function.identity()));

        List<RelationVistoryPO> v = new ArrayList<>();
        for (RecordVistoryPO rvpo : rtl.getVistory()) {

            RelationVistoryPO converted = new RelationVistoryPO();
            converted.setCreateDate(rvpo.getCreateDate());
            converted.setCreatedBy(rvpo.getCreatedBy());
            converted.setData(rvpo.getData());
            converted.setEnrichment(rvpo.isEnrichment());
            converted.setId(rvpo.getId());
            converted.setMajor(rvpo.getMajor());
            converted.setMinor(rvpo.getMinor());
            converted.setName(keys.getName());
            converted.setOperationId(rvpo.getOperationId());
            converted.setOperationType(rvpo.getOperationType());

            RelationOriginKeyPO relationOriginKey = toKeysToOriginIds.get(rvpo.getOriginId());

            converted.setOriginId(relationOriginKey == null || relationOriginKey.getId() == null ? rvpo.getOriginId() : relationOriginKey.getId().toString());
            converted.setOriginStatus(relationOriginKey == null ? null : relationOriginKey.getStatus());
            converted.setRevision(rvpo.getRevision());
            converted.setShard(rvpo.getShard());
            converted.setShift(rvpo.getShift());
            converted.setSourceSystem(rvpo.getSourceSystem());
            converted.setStatus(rvpo.getStatus());
            converted.setUpdateDate(rvpo.getUpdateDate());
            converted.setUpdatedBy(rvpo.getUpdatedBy());
            converted.setValidFrom(rvpo.getValidFrom());
            converted.setValidTo(rvpo.getValidTo());

            v.add(converted);
        }

        timeline.setVistory(v);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO loadKeysByLSN(int shard, long lsn) {
        MeasurementPoint.start();
        try {

            RelationKeysPO result = shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(RelationRecordsQuery.LOAD_KEYS_BY_LSN_SQL, shard),
                    RelationKeysRowMapper.DEFAULT_FIRST_RESULT_EXTRACTOR,
                    shard, lsn);

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getFromKeys())) {
                result.setFromKeys(postLoadRecordKeys(result.getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getToKeys())) {
                result.setToKeys(postLoadRecordKeys(result.getToKeys().getId()));
                postProcessContainmentKeys(result);
            }

            return result;

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO loadKeysByEtalonId(UUID etalonId) {
        MeasurementPoint.start();
        try {

            int shard = StorageUtils.shard(etalonId);
            RelationKeysPO result = shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(RelationRecordsQuery.LOAD_KEYS_BY_ETALON_ID_SQL, shard),
                    RelationKeysRowMapper.DEFAULT_FIRST_RESULT_EXTRACTOR,
                    etalonId);

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getFromKeys())) {
                result.setFromKeys(postLoadRecordKeys(result.getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getToKeys())) {
                result.setToKeys(postLoadRecordKeys(result.getToKeys().getId()));
                postProcessContainmentKeys(result);
            }

            return result;
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO loadKeysByRecordsExternalIds(ExternalId fromId, ExternalId toId, String relationName) {
        MeasurementPoint.start();
        try {

            UUID fromTarget = dataRecordsDAO.loadSysIdByExternalId(fromId);
            if (Objects.isNull(fromTarget)) {
                return null;
            }

            UUID toTarget = dataRecordsDAO.loadSysIdByExternalId(toId);
            if (Objects.isNull(toTarget)) {
                return null;
            }

            return loadKeysByRecordsEtalonIds(fromTarget, toTarget, relationName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO loadKeysByRecordsEtalonIds(UUID etalonIdFrom, UUID etalonIdTo, String relationName) {

        MeasurementPoint.start();
        try {

            UUID relationEtalonId = loadSysIdByFromToAndName(etalonIdFrom, etalonIdTo, relationName);
            if (Objects.isNull(relationEtalonId)) {
                return null;
            }

            return loadKeysByEtalonId(relationEtalonId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO loadKeysByRecordsLSNs(int fromShard, long fromLSN, int toShard, long toLSN,
            String relationName) {

        MeasurementPoint.start();
        try {


            UUID fromTarget = dataRecordsDAO.loadSysIdByLSN(fromShard, fromLSN);
            if (Objects.isNull(fromTarget)) {
                return null;
            }

            UUID toTarget = dataRecordsDAO.loadSysIdByLSN(toShard, toLSN);
            if (Objects.isNull(toTarget)) {
                return null;
            }

            return loadKeysByRecordsEtalonIds(fromTarget, toTarget, relationName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RelationKeysPO> loadAllKeysByRecordEtalonId(UUID recordEtalonId, RelativeDirection side) {
        // TODO Implement!!!
        return Collections.emptyList();
    }

    //-------------------------- Timelines -------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadTimeline(UUID etalonId, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        MeasurementPoint.start();
        try {

            Timestamp lastUpdateTimestamp = lastUpdate == null ? null : new Timestamp(lastUpdate.getTime());
            Timestamp updatesAfterTimestamp = updatesAfter == null ? null : new Timestamp(updatesAfter.getTime());

            int shard = StorageUtils.shard(etalonId);
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_TIMELINE_BY_ETALON_ID_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
//============================
//                          Keys
//============================
                            fetchKeys,
                            etalonId,
//============================
//                          Operation ID LUD
//============================
                            operationId,
                            operationId,
                            etalonId,
//============================
//                          Records
//============================
                            etalonId,
                            operationId,
                            lastUpdateTimestamp,
                            operationId,
                            lastUpdateTimestamp,
                            fetchData,
                            shard,
                            updatesAfterTimestamp,
                            updatesAfterTimestamp);

            // FIXME: Post process clsf keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process clsf keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentTimeline(result, fetchData, lastUpdate, updatesAfter, operationId);
            }

            return result;

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadTimeline(LSN lsn, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {
        MeasurementPoint.start();
        try {

            Timestamp lastUpdateTimestamp = lastUpdate == null ? null : new Timestamp(lastUpdate.getTime());
            Timestamp updatesAfterTimestamp = updatesAfter == null ? null : new Timestamp(updatesAfter.getTime());

            RelationTimelinePO result = shardSelect(lsn.getShard())
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_TIMELINE_BY_LSN_SQL, lsn.getShard()),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
//============================
//                          Keys
//============================
                            fetchKeys,
                            lsn.getShard(),
                            lsn.getLsn(),
//============================
//                          Operation ID LUD
//============================
                            operationId,
                            operationId,
                            lsn.getShard(),
                            lsn.getLsn(),
//============================
//                          Records
//============================
                            lsn.getShard(),
                            lsn.getLsn(),
                            operationId,
                            lastUpdateTimestamp,
                            operationId,
                            lastUpdateTimestamp,
                            fetchData,
                            lsn.getShard(),
                            updatesAfterTimestamp,
                            updatesAfterTimestamp);

            // FIXME: Post process clsf keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process clsf keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentTimeline(result, fetchData, lastUpdate, updatesAfter, operationId);
            }

            return result;

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadTimeline(LSN from, LSN to, String relationName, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {
        MeasurementPoint.start();
        try {

            UUID fromTarget = dataRecordsDAO.loadSysIdByLSN(from.getShard(), from.getLsn());
            if (Objects.isNull(fromTarget)) {
                return null;
            }

            UUID toTarget = dataRecordsDAO.loadSysIdByLSN(to.getShard(), to.getLsn());
            if (Objects.isNull(toTarget)) {
                return null;
            }

            UUID target = loadSysIdByFromToAndName(fromTarget, toTarget, relationName);
            if (Objects.isNull(target)) {
                return null;
            }

            return loadTimeline(target, true, fetchData, lastUpdate, updatesAfter, operationId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadTimeline(ExternalId from, ExternalId to, String relationName, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {
        MeasurementPoint.start();
        try {

            UUID fromTarget = dataRecordsDAO.loadSysIdByExternalId(from);
            if (Objects.isNull(fromTarget)) {
                return null;
            }

            UUID toTarget = dataRecordsDAO.loadSysIdByExternalId(to);
            if (Objects.isNull(toTarget)) {
                return null;
            }

            UUID target = loadSysIdByFromToAndName(fromTarget, toTarget, relationName);
            if (Objects.isNull(target)) {
                return null;
            }

            return loadTimeline(target, true, fetchData, lastUpdate, updatesAfter, operationId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadTimeline(UUID from, UUID to, String relationName, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {
        MeasurementPoint.start();
        try {

            UUID target = loadSysIdByFromToAndName(from, to, relationName);
            if (Objects.isNull(target)) {
                return null;
            }

            return loadTimeline(target, true, fetchData, lastUpdate, updatesAfter, operationId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RelationTimelinePO>
        loadTimelines(UUID recordEtalonId, List<String> names, boolean isToSide, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        MeasurementPoint.start();
        try {

            Map<String, List<UUID>> relationIds = isToSide
                    ? loadSysIdsByToSpec(recordEtalonId, names)
                    : loadSysIdsByFromSpec(recordEtalonId, names);

            if (MapUtils.isEmpty(relationIds)) {
                return Collections.emptyList();
            }

            return relationIds.values().stream()
                .flatMap(Collection::stream)
                .map(key -> loadTimeline(key, true, fetchData, lastUpdate, updatesAfter, operationId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadRelationVersions(UUID relationEtalonId, Date asOf, boolean includeDraftVersions) {

        MeasurementPoint.start();
        try {

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            String user = SecurityUtils.getCurrentUserName();
            Timestamp point = VendorUtils.coalesce(asOf);
            int shard = StorageUtils.shard(relationEtalonId);
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_RELATION_VERSIONS_BY_ETALON_ID_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
                                    true, // fetch_keys
                                    relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, // etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id
                                    null, relationEtalonId, null, relationEtalonId, null, // operation_id, etalon_id, operation_id, etalon_id, operation_id,
                                    null, point, // lud, point
                                    includeDraftVersions, user, // is_approver, user_name,
                                    null, null // updates_after, updates_after
                            );

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentVersions(result, asOf, null, includeDraftVersions);
            }

            return result;
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadRelationVersions(UUID relationEtalonId, Date asOf, Date lud,
            boolean includeDraftVersions) {

        MeasurementPoint.start();
        try {

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            String user = SecurityUtils.getCurrentUserName();
            Timestamp point = VendorUtils.coalesce(asOf);
            int shard = StorageUtils.shard(relationEtalonId);
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_RELATION_VERSIONS_BY_ETALON_ID_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
                                    true, // fetch_keys
                                    relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, // etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id
                                    null, relationEtalonId, null, relationEtalonId, null, // operation_id, etalon_id, operation_id, etalon_id, operation_id,
                                    lud, point, // lud, point
                                    includeDraftVersions, user, // is_approver, user_name,
                                    null, null // updates_after, updates_after
                            );

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentVersions(result, asOf, null, includeDraftVersions);
            }

            return result;
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadRelationVersions(UUID relationEtalonId, Date asOf, String operationId, boolean includeDraftVersions) {

        MeasurementPoint.start();
        try {

         // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            String user = SecurityUtils.getCurrentUserName();
            Timestamp point = VendorUtils.coalesce(asOf);
            int shard = StorageUtils.shard(relationEtalonId);
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_RELATION_VERSIONS_BY_ETALON_ID_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
                                    true, // fetch_keys
                                    relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, relationEtalonId, // etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id
                                    operationId, relationEtalonId, operationId, relationEtalonId, operationId, // operation_id, etalon_id, operation_id, etalon_id, operation_id,
                                    null, point, // lud, point
                                    includeDraftVersions, user, // is_approver, user_name,
                                    null, null // updates_after, updates_after
                            );

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process clsf keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentVersions(result, asOf, operationId, includeDraftVersions);
            }

            return result;

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadRelationVersions(LSN relationLSN, Date asOf, boolean includeDraftVersions) {

        MeasurementPoint.start();
        try {

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            String user = SecurityUtils.getCurrentUserName();
            Timestamp point = VendorUtils.coalesce(asOf);
            int shard = relationLSN.getShard();
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_RELATION_VERSIONS_BY_LSN_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
                                    true, // fetch_keys
                                    relationLSN.getShard(), relationLSN.getLsn(), // shard, lsn
                                    null, null, relationLSN.getShard(), relationLSN.getLsn(), // operation_id, operation_id, shard, lsn,
                                    relationLSN.getShard(), relationLSN.getLsn(), // shard, lsn
                                    null, null, point, // operation_id, lud, point
                                    includeDraftVersions, user, // is_approver, user_name,
                                    null, null // updates_after, updates_after
                            );

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentVersions(result, asOf, null, includeDraftVersions);
            }

            return result;
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadRelationVersions(LSN relationLSN, Date asOf, Date lud, boolean includeDraftVersions) {

        MeasurementPoint.start();
        try {

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            String user = SecurityUtils.getCurrentUserName();
            Timestamp point = VendorUtils.coalesce(asOf);
            int shard = relationLSN.getShard();
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_RELATION_VERSIONS_BY_LSN_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
                                    true, // fetch_keys
                                    relationLSN.getShard(), relationLSN.getLsn(), // shard, lsn
                                    null, null, relationLSN.getShard(), relationLSN.getLsn(), // operation_id, operation_id, shard, lsn,
                                    relationLSN.getShard(), relationLSN.getLsn(), // shard, lsn
                                    null, lud, point, // operation_id, lud, point
                                    includeDraftVersions, user, // is_approver, user_name,
                                    null, null // updates_after, updates_after
                            );

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentVersions(result, asOf, null, includeDraftVersions);
            }

            return result;
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO loadRelationVersions(LSN relationLSN, Date asOf, String operationId,
            boolean includeDraftVersions) {

        MeasurementPoint.start();
        try {

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            String user = SecurityUtils.getCurrentUserName();
            Timestamp point = VendorUtils.coalesce(asOf);
            int shard = relationLSN.getShard();
            RelationTimelinePO result = shardSelect(shard)
                    .jdbcTemplate()
                    .query(templates.getQuery(RelationRecordsQuery.LOAD_RELATION_VERSIONS_BY_LSN_SQL, shard),
                            rs -> rs.next() ? RelationTimelineRowMapper.DEFAULT_RELATION_TIMELINE_ROW_MAPPER.mapRow(rs, 0) : null,
                                    true, // fetch_keys
                                    relationLSN.getShard(), relationLSN.getLsn(), // shard, lsn
                                    operationId, operationId, relationLSN.getShard(), relationLSN.getLsn(), // operation_id, operation_id, shard, lsn,
                                    relationLSN.getShard(), relationLSN.getLsn(), // shard, lsn
                                    operationId, null, point, // operation_id, lud, point
                                    includeDraftVersions, user, // is_approver, user_name,
                                    null, null // updates_after, updates_after
                            );

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getFromKeys())) {
                result.getKeys().setFromKeys(postLoadRecordKeys(result.getKeys().getFromKeys().getId()));
            }

            // FIXME: Post process rel keys for ISO schema
            if (Objects.nonNull(result) && Objects.nonNull(result.getKeys()) && Objects.nonNull(result.getKeys().getToKeys())) {
                result.getKeys().setToKeys(postLoadRecordKeys(result.getKeys().getToKeys().getId()));
                postProcessContainmentKeys(result.getKeys());
                postProcessContainmentVersions(result, asOf, null, includeDraftVersions);
            }

            return result;
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * TOOD: Remove
     * {@inheritDoc}
     */
    @Override
    public List<RelationEtalonPO> loadEtalonRelations(List<UUID> relationEtalonIds) {

        if (CollectionUtils.isEmpty(relationEtalonIds)) {
            return Collections.emptyList();
        }

        return relationEtalonIds.stream()
            .collect(Collectors.groupingBy(StorageUtils::shard))
            .entrySet()
            .stream()
            .map(entry -> {
                        // todo HPE.  it is bad, too match connection for one query
                        try (Connection cn = shardSelect(entry.getKey()).bareConnection()) {
                            Array ids = cn.createArrayOf("uuid", entry.getValue().toArray());
                            return shardSelect(entry.getKey())
                                    .jdbcTemplate()
                                    .query(templates.getQuery(RelationRecordsQuery.LOAD_ETALON_RELATIONS_BY_IDS_SQL, entry.getKey()),
                                            EtalonRelationRowMapper.DEFAULT_ETALON_RELATION_ROW_MAPPER, ids);
                        } catch (SQLException e) {
                            LOGGER.error("Failed to wipe external ids.", e);
                            return Collections.<RelationEtalonPO>emptyList();
                        }
                    }
                )
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RelationEtalonPO> loadEtalonRelations(UUID recordEtalonId, String relationName, List<RecordStatus> statuses, RelativeDirection side) {
        MeasurementPoint.start();
        try {

            Map<String, List<UUID>> relationIds = Collections.emptyMap();
            if (RelativeDirection.FROM == side) {
                relationIds = loadSysIdsByFromSpec(recordEtalonId, Collections.singletonList(relationName));
            } else if (RelativeDirection.TO == side) {
                relationIds = loadSysIdsByToSpec(recordEtalonId, Collections.singletonList(relationName));
            }

            if (MapUtils.isEmpty(relationIds)) {
                return Collections.emptyList();
            }

            List<RelationEtalonPO> result = loadEtalonRelations(relationIds.values().stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList()));

            return result.stream()
                    .filter(po -> CollectionUtils.isEmpty(statuses) || statuses.contains(po.getStatus()))
                    .collect(Collectors.toList());

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<UUID>> loadMappedRelationEtalonIds(UUID recordEtalonId, Collection<String> relationNames,
            RelativeDirection side) {

        MeasurementPoint.start();
        try {

            if (RelativeDirection.FROM == side) {
                return loadSysIdsByFromSpec(recordEtalonId, relationNames);
            } else if (RelativeDirection.TO == side) {
                return loadSysIdsByToSpec(recordEtalonId, relationNames);
            }

            return Collections.emptyMap();
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationDigestDTO loadDigestDestinationEtalonIds(UUID recordEtalonId, String relationName,

            RelativeDirection viewSide, Map<String, Integer> sourceSystems, Date asOf, int count, int from) {

        List<RelationEtalonPO> relations
            = loadEtalonRelations(recordEtalonId, relationName,
                    Collections.singletonList(RecordStatus.ACTIVE), viewSide);

        List<String> result = Collections.emptyList();
        long totalCount = 0;

        if (CollectionUtils.isNotEmpty(relations)) {
            totalCount = relations.size();
            result = relations.stream()
                    .map(relation -> viewSide == RelativeDirection.TO ? relation.getToEtalonId() : relation.getFromEtalonId())
                    .collect(Collectors.toList());
        }

        return new RelationDigestDTO(result, totalCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertOriginRelations(List<RelationOriginPO> origins, boolean isNew) {

        MeasurementPoint.start();
        try {

            int shard = origins.get(0).getShard();
            int[][] result;
            if (isNew) {

                result = shardSelect(shard)
                        .jdbcTemplate()
                        .batchUpdate(templates.getQuery(RelationRecordsQuery.INSERT_ORIGIN_RELATION_SQL, shard),
                                origins, origins.size(), (ps, origin) -> {

                        ps.setObject(1, UUID.fromString(origin.getId()));
                        ps.setObject(2, UUID.fromString(origin.getEtalonId()));
                        ps.setObject(3, origin.getInitialOwner());
                        ps.setInt(4, origin.getShard());
                        ps.setString(5, origin.getName());
                        ps.setObject(6, UUID.fromString(origin.getFromOriginId()));
                        ps.setObject(7, UUID.fromString(origin.getToOriginId()));
                        ps.setString(8, origin.getSourceSystem());
                        ps.setTimestamp(9, VendorUtils.coalesce(origin.getCreateDate()));
                        ps.setString(10, origin.getCreatedBy());
                        ps.setString(11, origin.getStatus() == null ? RecordStatus.ACTIVE.name() : origin.getStatus().name());
                        ps.setBoolean(12, origin.isEnrichment());
                    });
            } else {

                result = shardSelect(shard  )
                        .jdbcTemplate()
                        .batchUpdate(templates.getQuery(RelationRecordsQuery.UPDATE_ORIGIN_RELATION_SQL, shard),
                                origins, origins.size(), (ps, origin) -> {

                        ps.setTimestamp(1, VendorUtils.coalesce(origin.getUpdateDate()));
                        ps.setString(2, origin.getUpdatedBy());
                        ps.setString(3, origin.getStatus() == null ? RecordStatus.ACTIVE.name() : origin.getStatus().name());
                        ps.setInt(4, origin.getShard());
                        ps.setObject(5, UUID.fromString(origin.getId()));
                    });
            }

            if (result.length == 0 || result[0].length != origins.size()) {
                final String message = "Relation origin record upsert failed. Stopping.";
                LOGGER.warn(message);
                throw new DataProcessingException(message, DataExceptionIds.EX_DATA_RELATIONS_BATCH_UPSERT_ORIGIN_FAILED);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertEtalonRelations(List<RelationEtalonPO> etalons, boolean isNew) {

        if (CollectionUtils.isEmpty(etalons)) {
            return;
        }

        MeasurementPoint.start();
        try {

            int shard = etalons.get(0).getShard();
//            Map<Boolean, List<RelationEtalonPO>> split
//                = etalons.stream().collect(Collectors.partitioningBy(e -> e.getApproval() == ApprovalState.PENDING));
            Map<Boolean, List<RelationEtalonPO>> split = Collections.singletonMap(Boolean.FALSE, etalons);
            // Insert
            if (isNew) {

//                for (Entry<Boolean, List<RelationEtalonPO>> entry : split.entrySet()) {

//                    if (entry.getValue().isEmpty()) {
//                        continue;
//                    }

                    // 1. Ordinary upsert
                    shardSelect(shard)
                        .jdbcTemplate()
                        .batchUpdate(templates.getQuery(RelationRecordsQuery.INSERT_ETALON_RELATION_SQL, shard), etalons, etalons.size(), (ps, etalon) -> {
                            ps.setInt(1, etalon.getShard());
                            ps.setObject(2, UUID.fromString(etalon.getId()));
                            ps.setString(3, etalon.getName());
                            ps.setObject(4, UUID.fromString(etalon.getFromEtalonId()));
                            ps.setObject(5, UUID.fromString(etalon.getToEtalonId()));
                            ps.setTimestamp(6, VendorUtils.coalesce(etalon.getCreateDate()));
                            ps.setString(7, etalon.getCreatedBy());
                            ps.setString(8, etalon.getStatus() == null ? RecordStatus.ACTIVE.name() : etalon.getStatus().name());
                            ps.setString(9, etalon.getRelationType() == null ? null : etalon.getRelationType().name());
                            ps.setString(10, etalon.getOperationId());
                        });

            // Update
            } else {

                for (Entry<Boolean, List<RelationEtalonPO>> entry : split.entrySet()) {

                    if (entry.getValue().isEmpty()) {
                        continue;
                    }

                    if (entry.getKey()) {

                        upsertEtalonStateDraft(entry.getValue().stream()
                                .map(po -> {
                                    EtalonRelationDraftStatePO erdspo = new EtalonRelationDraftStatePO();
                                    erdspo.setEtalonId(po.getId());
                                    erdspo.setStatus(po.getStatus());
                                    erdspo.setCreatedBy(po.getCreatedBy());
                                    erdspo.setCreateDate(po.getCreateDate());
                                    return erdspo;
                                })
                                .collect(Collectors.toList()));
                    } else {

                        shardSelect(shard)
                            .jdbcTemplate()
                            .batchUpdate(templates.getQuery(RelationRecordsQuery.UPDATE_ETALON_RELATION_SQL, shard), etalons, etalons.size(), (ps, etalon) -> {
                                ps.setTimestamp(1, VendorUtils.coalesce(etalon.getUpdateDate()));
                                ps.setString(2, etalon.getUpdatedBy());
                                ps.setString(3, etalon.getStatus() == null ? RecordStatus.ACTIVE.name() : etalon.getStatus().name());
                                ps.setString(4, etalon.getOperationId());
                                ps.setInt(5, etalon.getShard());
                                ps.setObject(6, UUID.fromString(etalon.getId()));
                            });
                    }
                }

            }

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertFromExternalKeys(List<RelationExternalKeyPO> keys, boolean isNew) {

        if (CollectionUtils.isEmpty(keys)) {
            return;
        }

        MeasurementPoint.start();
        try {

            // Insert only so far
            keys.stream()
                .collect(Collectors.groupingBy(RelationExternalKeyPO::getFromShard))
                .entrySet()
                .stream()
                .forEach(entry ->
                    shardSelect(entry.getKey())
                        .jdbcTemplate()
                        .batchUpdate(templates.getQuery(RelationRecordsQuery.INSERT_FROM_EXTERNAL_KEY_SQL, entry.getKey()),
                            entry.getValue(), entry.getValue().size(), (ps, v) -> {
                                ps.setInt(1, v.getFromShard());
                                ps.setObject(2, v.getFromRecordEtalonId());
                                ps.setString(3, v.getRelationName());
                                ps.setObject(4, v.getToRecordEtalonId());
                                ps.setObject(5, v.getRelationEtalonId());
                        })
                );

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertToExternalKeys(List<RelationExternalKeyPO> keys, boolean isNew) {

        if (CollectionUtils.isEmpty(keys)) {
            return;
        }

        MeasurementPoint.start();
        try {

            // Insert only so far
            keys.stream()
                .collect(Collectors.groupingBy(RelationExternalKeyPO::getToShard))
                .entrySet()
                .stream()
                .forEach(entry ->
                    shardSelect(entry.getKey())
                        .jdbcTemplate()
                        .batchUpdate(templates.getQuery(RelationRecordsQuery.INSERT_TO_EXTERNAL_KEY_SQL, entry.getKey()),
                            entry.getValue(), entry.getValue().size(), (ps, v) -> {
                                ps.setInt(1, v.getToShard());
                                ps.setObject(2, v.getToRecordEtalonId());
                                ps.setString(3, v.getRelationName());
                                ps.setObject(4, v.getFromRecordEtalonId());
                                ps.setObject(5, v.getRelationEtalonId());
                        })
                );

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertVersions(List<RelationVistoryPO> upsert) {

        if (CollectionUtils.isEmpty(upsert)) {
            return;
        }

        MeasurementPoint.start();
        try {

            upsert.stream()
                .collect(Collectors.groupingBy(RelationVistoryPO::getShard))
                .forEach((shard, versions) -> {

                String statement = templates.getQuery(RelationRecordsQuery.PUT_RELATION_VERSION_PROTOSTUFF_SQL, shard);
                int[][] result = shardSelect(shard)
                    .jdbcTemplate()
                    .batchUpdate(statement, versions, versions.size(), (ps, version) -> {

                        if (version.getShard() != shard) {
                            throwShardNumbersDoesNotMatchException(shard, version.getShard());
                        }

                        Timestamp from = version.getValidFrom() == null ? null : new Timestamp(version.getValidFrom().getTime());
                        Timestamp to = version.getValidTo() == null ? null : new Timestamp(version.getValidTo().getTime());
                        Timestamp ts = VendorUtils.coalesce(version.getCreateDate());

                        String status = version.getStatus() == null ? RecordStatus.ACTIVE.name() : version.getStatus().name();
                        String shift = version.getShift() == null ? DataShift.PRISTINE.name() : version.getShift().name();
                        String operationType = version.getOperationType() == null ? OperationType.DIRECT.name() : version.getOperationType().name();

                        ps.setString(1, version.getId());
                        ps.setString(2, version.getOriginId());
                        ps.setInt(3, version.getShard());
                        ps.setString(4, version.getOriginId()); // Revision
                        ps.setTimestamp(5, from);
                        ps.setTimestamp(6, to);

//                        if (platformConfiguration.getDumpTargetFormat() == DumpTargetFormat.JAXB) {
//                            ps.setString(7, DataSerializer.dumpOriginRelationToJaxb(version.getData()));
//                        } else if (platformConfiguration.getDumpTargetFormat() == DumpTargetFormat.PROTOSTUFF) {
                        if (dumpTargetFormat.getValue() == DumpTargetFormat.PROTOSTUFF) {
                            ps.setBytes(7, DataSerializer.toProtostuff(version.getData()));
                        }

                        ps.setTimestamp(8, ts);
                        ps.setString(9, version.getCreatedBy());
                        ps.setString(10, status);
                        ps.setString(11, shift);
                        ps.setString(12, operationType);
                        ps.setString(13, version.getOperationId());
                        ps.setInt(14, version.getMajor());
                        ps.setInt(15, version.getMinor());
                    });

                if (result.length == 0 || result[0].length != versions.size()) {
                    final String message = "Cannot insert relation version record(s).";
                    LOGGER.warn(message);
                    throw new DataProcessingException(message, DataExceptionIds.EX_DATA_RELATIONS_UPSERT_VERSION_FAILED);
                }
            });

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cleanupEtalonStateDrafts(UUID relationEtalonId) {

        boolean success = false;
        MeasurementPoint.start();
        try {
            success = jdbcTemplate.update(templates.getQuery(RelationRecordsQuery.CLEANUP_ETALON_RELATION_STATE_DRAFT_SQL),
                    relationEtalonId) > 0;
        } finally {
            MeasurementPoint.stop();
        }

        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertEtalonStateDraft(List<EtalonRelationDraftStatePO> drafts) {
        MeasurementPoint.start();
        try {

            jdbcTemplate.batchUpdate(templates.getQuery(RelationRecordsQuery.INSERT_ETALON_RELATION_STATE_DRAFT_SQL),
                    drafts, drafts.size(),
                    (ps, pending) -> {
                        ps.setString(1, pending.getEtalonId());
                        ps.setString(2, pending.getEtalonId());
                        ps.setString(3, pending.getStatus() == null ? RecordStatus.ACTIVE.name() : pending.getStatus().name());
                        ps.setString(4, pending.getCreatedBy());
                        ps.setTimestamp(5, pending.getCreateDate() == null ? new Timestamp(System.currentTimeMillis())
                                : new Timestamp(pending.getCreateDate().getTime()));
                    });

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EtalonRelationDraftStatePO loadLastEtalonStateDraft(String etalonId) {

        MeasurementPoint.start();
        try {
            return jdbcTemplate.query(templates.getQuery(RelationRecordsQuery.LOAD_LAST_ETALON_RELATION_STATE_DRAFT_SQL),
                    EtalonRelationDraftStateRowMapper.DEFAULT_FIRST_RESULT_EXTRACTOR,
                    etalonId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertEtalonRecords(int shard, List<RelationEtalonPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String prolog = new StringBuilder().append("copy relation_etalons_p")
                    .append(shard)
                    .append(" (shard, id, name, etalon_id_from, etalon_id_to, create_date, created_by, status, reltype, operation_id) from stdin binary")
                    .toString();

            final VendorDataType[] types = {
                VendorDataType.INT4,
                VendorDataType.UUID,
                VendorDataType.CHAR,
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.TIMESTAMP,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR
            };

            final Object[] params = new Object[types.length];

            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, prolog)) {

                for (RelationEtalonPO record : records) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(shard, record.getShard());
                    }

                    params[0] = record.getShard();
                    params[1] = UUID.fromString(record.getId());
                    params[2] = record.getName();
                    params[3] = UUID.fromString(record.getFromEtalonId());
                    params[4] = UUID.fromString(record.getToEtalonId());
                    params[5] = record.getCreateDate();
                    params[6] = record.getCreatedBy();
                    params[7] = record.getStatus() == null ? RecordStatus.ACTIVE.name() : record.getStatus().name();
                    params[8] = record.getRelationType() == null ? null : record.getRelationType().name();
                    params[9] = record.getOperationId();

                    VendorUtils.bulkAppend(stream, types, params);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Relation etalons insert failed!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertOriginRecords(int shard, List<RelationOriginPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String prolog = new StringBuilder()
                    .append("copy relation_origins_p")
                    .append(shard)
                    .append(" (id, etalon_id, initial_owner, shard, name, origin_id_from, origin_id_to, source_system, create_date, created_by, status, enrichment) from stdin binary")
                    .toString();

            final VendorDataType[] types = {
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.INT4,
                VendorDataType.CHAR,
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.CHAR,
                VendorDataType.TIMESTAMP,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.BOOLEAN
            };

            final Object[] params = new Object[types.length];

            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, prolog)) {

                for (RelationOriginPO record : records) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(shard, record.getShard());
                    }

                    params[0] = UUID.fromString(record.getId());
                    params[1] = UUID.fromString(record.getEtalonId());
                    params[2] = record.getInitialOwner();
                    params[3] = record.getShard();
                    params[4] = record.getName();
                    params[5] = UUID.fromString(record.getFromOriginId());
                    params[6] = UUID.fromString(record.getToOriginId());
                    params[7] = record.getSourceSystem();
                    params[8] = record.getCreateDate();
                    params[9] = record.getCreatedBy();
                    params[10] = record.getStatus() == null ? RecordStatus.ACTIVE.name() : record.getStatus().name();
                    params[11] = record.isEnrichment();

                    VendorUtils.bulkAppend(stream, types, params);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Relation origins insert failed!", e);
            }
        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertFromExternalKeys(int shard, List<RelationExternalKeyPO> records) {

        MeasurementPoint.start();
        try {

            final String keysProlog = new StringBuilder().append("copy relation_from_keys_p")
                    .append(shard)
                    .append(" (shard, from_id, name, to_id, etalon_id) from stdin binary")
                    .toString();

            final VendorDataType[] keysTypes = {
                    VendorDataType.INT4,
                    VendorDataType.UUID,
                    VendorDataType.CHAR,
                    VendorDataType.UUID,
                    VendorDataType.UUID
            };

            final Object[] keysParams = new Object[keysTypes.length];
            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, keysProlog)) {

                for (RelationExternalKeyPO record : records) {

                    if (record.getFromShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getFromShard(), shard);
                    }

                    keysParams[0] = record.getFromShard();
                    keysParams[1] = record.getFromRecordEtalonId();
                    keysParams[2] = record.getRelationName();
                    keysParams[3] = record.getToRecordEtalonId();
                    keysParams[4] = record.getRelationEtalonId();

                    VendorUtils.bulkAppend(stream, keysTypes, keysParams);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Bulk insert relation 'from' side external keys!", e);
            }
        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertToExternalKeys(int shard, List<RelationExternalKeyPO> records) {

        MeasurementPoint.start();
        try {

            final String keysProlog = new StringBuilder().append("copy relation_to_keys_p")
                    .append(shard)
                    .append(" (shard, to_id, name, from_id, etalon_id) from stdin binary")
                    .toString();

            final VendorDataType[] keysTypes = {
                    VendorDataType.INT4,
                    VendorDataType.UUID,
                    VendorDataType.CHAR,
                    VendorDataType.UUID,
                    VendorDataType.UUID
            };

            final Object[] keysParams = new Object[keysTypes.length];
            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, keysProlog)) {

                for (RelationExternalKeyPO record : records) {

                    if (record.getToShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getToShard(), shard);
                    }

                    keysParams[0] = record.getToShard();
                    keysParams[1] = record.getToRecordEtalonId();
                    keysParams[2] = record.getRelationName();
                    keysParams[3] = record.getFromRecordEtalonId();
                    keysParams[4] = record.getRelationEtalonId();

                    VendorUtils.bulkAppend(stream, keysTypes, keysParams);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Bulk insert relation 'to' side external keys!", e);
            }
        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkUpdateEtalonRecords(int shard, List<RelationEtalonPO> records) {

        MeasurementPoint.start();
        try {

            UUID[] ids = new UUID[records.size()];
            Timestamp[] updateDates = new Timestamp[records.size()];
            String[] updatedBy = new String[records.size()];
            String[] statuses = new String[records.size()];

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                ids[i] = UUID.fromString(records.get(i).getId());
                updateDates[i] = VendorUtils.coalesce(records.get(i).getUpdateDate());
                updatedBy[i] = records.get(i).getUpdatedBy();
                statuses[i] = records.get(i).getStatus() == null ? RecordStatus.ACTIVE.name() : records.get(i).getStatus().name();
            }

            try (Connection c = shardSelect(shard).dataSource().getConnection()) {

                Array ia = c.createArrayOf("uuid", ids);
                Array da = c.createArrayOf("timestamptz", updateDates);
                Array ua = c.createArrayOf("varchar", updatedBy);
                Array sa = c.createArrayOf("record_status", statuses);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(RelationRecordsQuery.CHANGE_ETALONS_STATUS_BATCH_SQL, shard), ia, da, ua, sa);

            } catch (SQLException e) {
                LOGGER.warn("Cannot create relation etalons update array.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkUpdateOriginRecords(int shard, List<RelationOriginPO> records) {

        MeasurementPoint.start();
        try {

            UUID[] ids = new UUID[records.size()];
            Timestamp[] updateDates = new Timestamp[records.size()];
            String[] updatedBy = new String[records.size()];
            String[] statuses = new String[records.size()];

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                ids[i] = UUID.fromString(records.get(i).getId());
                updateDates[i] = VendorUtils.coalesce(records.get(i).getUpdateDate());
                updatedBy[i] = records.get(i).getUpdatedBy();
                statuses[i] = records.get(i).getStatus() == null ? RecordStatus.ACTIVE.name() : records.get(i).getStatus().name();
            }

            try (Connection c = shardSelect(shard).dataSource().getConnection()) {

                Array ia = c.createArrayOf("uuid", ids);
                Array da = c.createArrayOf("timestamptz", updateDates);
                Array ua = c.createArrayOf("varchar", updatedBy);
                Array sa = c.createArrayOf("record_status", statuses);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(RelationRecordsQuery.CHANGE_ORIGINS_STATUS_BATCH_SQL, shard), ia, da, ua, sa, shard);

            } catch (SQLException e) {
                LOGGER.warn("Cannot create relation origins update array.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertVersions(int shard, List<RelationVistoryPO> versions) {

        if (CollectionUtils.isEmpty(versions)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String statement = "copy relation_vistory_p" + shard +
                " (id, origin_id, shard, revision, valid_from, valid_to," +
                " data_b," +
                " create_date, created_by, status, shift, operation_type, operation_id, major, minor) from stdin binary";

            final VendorDataType[] types = {
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.INT4,
                VendorDataType.INT4,
                VendorDataType.TIMESTAMP,
                VendorDataType.TIMESTAMP,
                VendorDataType.BYTEA,
                VendorDataType.TIMESTAMP,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.INT4,
                VendorDataType.INT4
            };

            final Object[] params = new Object[types.length];

            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, statement)) {

                for (RelationVistoryPO record : versions) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(shard, record.getShard());
                    }

                    params[0] = UUID.fromString(record.getId());
                    params[1] = UUID.fromString(record.getOriginId());
                    params[2] = record.getShard();
                    params[3] = record.getRevision();
                    params[4] = record.getValidFrom();
                    params[5] = record.getValidTo();

                    if (dumpTargetFormat.getValue() == DumpTargetFormat.PROTOSTUFF) {
                        params[6] = DataSerializer.toProtostuff(record.getData());
                    }

                    params[7] = record.getCreateDate();
                    params[8] = record.getCreatedBy();
                    params[9] = record.getStatus() == null ? RecordStatus.ACTIVE.name() : record.getStatus().name();
                    params[10] = record.getShift() == null ? DataShift.PRISTINE.name() : record.getShift().name();
                    params[11] = record.getOperationType() == null ? OperationType.DIRECT.name() : record.getOperationType().name();
                    params[12] = record.getOperationId();
                    params[13] = record.getMajor();
                    params[14] = record.getMinor();

                    VendorUtils.bulkAppend(stream, types, params);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Relation versions insert failed!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    private void bulkInsertRawVersions(int shard, List<RelationVistoryPO> versions) {

        if (CollectionUtils.isEmpty(versions)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String statement = "copy relation_vistory_p" + shard +
                " (id, origin_id, shard, revision, valid_from, valid_to," +
                " data_b," +
                " create_date, created_by, status, shift, operation_type, operation_id, major, minor) from stdin binary";

            final VendorDataType[] types = {
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.INT4,
                VendorDataType.INT4,
                VendorDataType.TIMESTAMP,
                VendorDataType.TIMESTAMP,
                VendorDataType.BYTEA,
                VendorDataType.TIMESTAMP,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.INT4,
                VendorDataType.INT4
            };

            final Object[] params = new Object[types.length];

            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, statement)) {

                for (RelationVistoryPO record : versions) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(shard, record.getShard());
                    }

                    params[0] = UUID.fromString(record.getId());
                    params[1] = UUID.fromString(record.getOriginId());
                    params[2] = record.getShard();
                    params[3] = record.getRevision();
                    params[4] = record.getValidFrom();
                    params[5] = record.getValidTo();

                    if (dumpTargetFormat.getValue() == DumpTargetFormat.PROTOSTUFF) {
                        params[6] = record.getProtostuffRawData();
                    }

                    params[7] = record.getCreateDate();
                    params[8] = record.getCreatedBy();
                    params[9] = record.getStatus().name();
                    params[10] = record.getShift().name();
                    params[11] = record.getOperationType().name();
                    params[12] = record.getOperationId();
                    params[13] = record.getMajor();
                    params[14] = record.getMinor();

                    VendorUtils.bulkAppend(stream, types, params);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Relation versions insert failed!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkRemapEtalonFromRecords(int shard, List<RelationEtalonRemapFromPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            UUID[] ids = new UUID[records.size()];
            UUID[] fromEtalonIds = new UUID[records.size()];
            Timestamp[] updateDates = new Timestamp[records.size()];
            String[] updatedBy = new String[records.size()];
            String[] operationIds = new String[records.size()];

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                ids[i] = UUID.fromString(records.get(i).getId());
                fromEtalonIds[i] = UUID.fromString(records.get(i).getNewEtalonIdFrom());
                updateDates[i] = VendorUtils.coalesce(records.get(i).getUpdateDate());
                updatedBy[i] = records.get(i).getUpdatedBy();
                operationIds[i] = records.get(i).getOperationId();
            }

            try (Connection c = shardSelect(shard).bareConnection()) {

                Array idsArray = c.createArrayOf("uuid", ids);
                Array fromEtalonIdsArray = c.createArrayOf("uuid", fromEtalonIds);
                Array updateDatesArray = c.createArrayOf("timestamptz", updateDates);
                Array updatedByArray = c.createArrayOf("varchar", updatedBy);
                Array operationIdsArray = c.createArrayOf("varchar", operationIds);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(RelationRecordsQuery.REMAP_TO_ETALON_RELATION_SQL, shard),
                            idsArray, fromEtalonIdsArray, updateDatesArray, updatedByArray, operationIdsArray);

            } catch (SQLException e) {
                LOGGER.error("Failed to remap relations to side.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkRemapEtalonToRecords(int shard, List<RelationEtalonRemapToPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            UUID[] ids = new UUID[records.size()];
            UUID[] toEtalonIds = new UUID[records.size()];
            Timestamp[] updateDates = new Timestamp[records.size()];
            String[] updatedBy = new String[records.size()];
            String[] operationIds = new String[records.size()];

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                ids[i] = UUID.fromString(records.get(i).getId());
                toEtalonIds[i] = UUID.fromString(records.get(i).getNewEtalonIdTo());
                updateDates[i] = VendorUtils.coalesce(records.get(i).getUpdateDate());
                updatedBy[i] = records.get(i).getUpdatedBy();
                operationIds[i] = records.get(i).getOperationId();
            }

            try (Connection c = shardSelect(shard).bareConnection()) {

                Array idsArray = c.createArrayOf("uuid", ids);
                Array toEtalonIdsArray = c.createArrayOf("uuid", toEtalonIds);
                Array updateDatesArray = c.createArrayOf("timestamptz", updateDates);
                Array updatedByArray = c.createArrayOf("varchar", updatedBy);
                Array operationIdsArray = c.createArrayOf("varchar", operationIds);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(RelationRecordsQuery.REMAP_TO_ETALON_RELATION_SQL, shard),
                            idsArray, toEtalonIdsArray, updateDatesArray, updatedByArray, operationIdsArray);

            } catch (SQLException e) {
                LOGGER.error("Failed to remap relations to side.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkRemapOriginRecords(int shard, List<RelationOriginRemapPO> remapList) {

        if (CollectionUtils.isEmpty(remapList)) {
            return;
        }

        MeasurementPoint.start();
        try {

            int sourceNode = StorageUtils.node(shard);

            // Group by local move possibility
            Map<Boolean, List<RelationOriginRemapPO>> partitionedByMoveType = remapList.stream()
                    .collect(Collectors.partitioningBy(r -> StorageUtils.node(r.getNewShard()) == sourceNode));

            partitionedByMoveType.forEach((sameNode, records) -> {

                if (CollectionUtils.isEmpty(records)) {
                    return;
                }

                UUID[] originIds = new UUID[records.size()];
                UUID[] newEtalonIds = new UUID[records.size()];
                Integer[] newShards = new Integer[records.size()];
                Timestamp[] updateDates = new Timestamp[records.size()];
                String[] updatedBy = new String[records.size()];

                for (int i = 0; i < records.size(); i++) {

                    if (records.get(i).getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                    }

                    originIds[i] = UUID.fromString(records.get(i).getId());
                    newEtalonIds[i] = records.get(i).getNewEtalonId();
                    newShards[i] = records.get(i).getNewShard();
                    updateDates[i] = VendorUtils.coalesce(records.get(i).getUpdateDate());
                    updatedBy[i] = records.get(i).getUpdatedBy();
                }

                try (Connection c = shardSelect(shard).bareConnection()) {

                    Array originIdsArray = c.createArrayOf("uuid", originIds);
                    Array newEtalonIdsArray = c.createArrayOf("uuid", newEtalonIds);
                    Array newShardsArray = c.createArrayOf("int4", newShards);
                    Array updateDatesArray = c.createArrayOf("timestamptz", updateDates);
                    Array updatedByArray = c.createArrayOf("varchar", updatedBy);

                    // Move locally
                    if (sameNode) {

                        shardSelect(shard)
                            .jdbcTemplate()
                            .update(templates.getQuery(RelationRecordsQuery.REMAP_ORIGIN_RELATION_SQL),
                                    originIdsArray, newEtalonIdsArray, newShardsArray, updateDatesArray, updatedByArray, shard);

                        shardSelect(shard)
                            .jdbcTemplate()
                            .update(templates.getQuery(RelationRecordsQuery.REMAP_VISTORY_RECORDS_SQL),
                                    originIdsArray,
                                    newShardsArray,
                                    shard);
                    // Cut and paste to a new node
                    } else {

                        // Origin to new shard map
                        Map<String, Integer> originsToNewShard = new HashMap<>();

                        // Transform to new inserts. Origins must be completely filled out.
                        Map<Integer, List<RelationOriginPO>> newOrigins = records.stream()
                                .map(r -> {

                                    originsToNewShard.put(r.getId(), r.getNewShard());

                                    r.setShard(r.getNewShard());
                                    r.setEtalonId(r.getNewEtalonId().toString());
                                    return r;
                                })
                                .collect(Collectors.groupingBy(RelationOriginPO::getShard));

                        // Cut vistory objects.
                        Map<Integer, List<RelationVistoryPO>> newVistory = shardSelect(shard)
                            .jdbcTemplate()
                            .query(templates.getQuery(RelationRecordsQuery.CUT_VISTORY_BY_ORIGIN_ID_SQL, shard), rs -> {

                                Map<Integer, List<RelationVistoryPO>> result = new HashMap<>();
                                while (rs.next()) {
                                    RelationVistoryPO po = RelationVistoryRowMapper.RAW_PROTOSTUFF_ROW_MAPPER.mapRow(rs, 0);
                                    po.setShard(originsToNewShard.get(po.getOriginId()));
                                    result.computeIfAbsent(po.getShard(), k -> new ArrayList<RelationVistoryPO>()).add(po);
                                }

                                return result;
                            }, originIdsArray);


                        shardSelect(shard)
                            .jdbcTemplate()
                            .update(templates.getQuery(RelationRecordsQuery.DELETE_ORIGIN_BY_ID_SQL, shard), shard, originIdsArray);

                        // Put to new target.
                        newOrigins.forEach(this::bulkInsertOriginRecords);
                        newVistory.forEach(this::bulkInsertRawVersions);
                    }

                } catch (SQLException e) {
                    LOGGER.error("Failed to remap relations to side.", e);
                }
            });

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkWipeRelationData(int shard, List<RelationKeysPO> keys) {

        if (CollectionUtils.isEmpty(keys)) {
            return;
        }

        MeasurementPoint.start();
        try (Connection c = shardSelect(shard).bareConnection()) {

            List<UUID> originIds = new ArrayList<>(keys.size() * 2);
            List<UUID> etalonIds = new ArrayList<>(keys.size());

            for (int i = 0; i < keys.size(); i++) {

                if (keys.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(keys.get(i).getShard(), shard);
                }

                etalonIds.add(UUID.fromString(keys.get(i).getId()));
                for (int j = 0; keys.get(i).getOriginKeys() != null && j < keys.get(i).getOriginKeys().size(); j++) {
                    originIds.add(keys.get(i).getOriginKeys().get(j).getId());
                }
            }

            JdbcTemplate ujt = shardSelect(shard).jdbcTemplate();
            if (CollectionUtils.isNotEmpty(originIds)) {

                Array oia = c.createArrayOf("uuid", originIds.toArray(new UUID[originIds.size()]));

                ujt.update(templates.getQuery(RelationRecordsQuery.DELETE_VISTORY_BY_ORIGIN_ID_SQL, shard), oia);
                ujt.update(templates.getQuery(RelationRecordsQuery.DELETE_ORIGIN_BY_ID_SQL, shard), shard, oia);
            }

            if (CollectionUtils.isNotEmpty(etalonIds)) {

                Array eia = c.createArrayOf("uuid", etalonIds.toArray(new UUID[etalonIds.size()]));
                ujt.update(templates.getQuery(RelationRecordsQuery.DELETE_ETALON_BY_ID_SQL, shard), eia);
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to wipe relation data.", e);
        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkWipeFromExternalKeys(int shard, List<RelationExternalKeyPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            UUID[] fromIds = new UUID[records.size()];
            UUID[] toIds = new UUID[records.size()];
            String[] names = new String[records.size()];
            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getFromShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getFromShard(), shard);
                }

                fromIds[i] = records.get(i).getFromRecordEtalonId();
                names[i] = records.get(i).getRelationName();
                toIds[i] = records.get(i).getToRecordEtalonId();
            }

            try (Connection c = shardSelect(shard).dataSource().getConnection()) {

                Array fa = c.createArrayOf("uuid", fromIds);
                Array na = c.createArrayOf("varchar", names);
                Array ta = c.createArrayOf("uuid", toIds);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(RelationRecordsQuery.DELETE_FROM_EXT_IDS_SQL, shard),
                            fa, na, ta, shard);

            } catch (SQLException e) {
                LOGGER.error("Failed to wipe relation from external ids.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkWipeToExternalKeys(int shard, List<RelationExternalKeyPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try (Connection c = shardSelect(shard).dataSource().getConnection()) {

            UUID[] toIds = new UUID[records.size()];
            UUID[] fromIds = new UUID[records.size()];
            String[] names = new String[records.size()];
            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getToShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getToShard(), shard);
                }

                toIds[i] = records.get(i).getToRecordEtalonId();
                names[i] = records.get(i).getRelationName();
                fromIds[i] = records.get(i).getFromRecordEtalonId();
            }

            Array ta = c.createArrayOf("uuid", toIds);
            Array na = c.createArrayOf("varchar", names);
            Array fa = c.createArrayOf("uuid", fromIds);

            shardSelect(shard)
                .jdbcTemplate()
                .update(templates.getQuery(RelationRecordsQuery.DELETE_TO_EXT_IDS_SQL, shard),
                        ta, na, fa, shard);

        } catch (SQLException e) {
            LOGGER.error("Failed to wipe relation to external ids.", e);
        } finally {
            MeasurementPoint.stop();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Pair<String, Long>> countEtalonsByFromNamesAndStatus(UUID recordEtalonId, List<String> names,
            RecordStatus status) {

        MeasurementPoint.start();
        try {

            Map<String, List<UUID>> relationIds = loadSysIdsByFromSpec(recordEtalonId, names);
            if (MapUtils.isEmpty(relationIds)) {
                return Collections.emptyList();
            }

            List<RelationEtalonPO> etalons = loadEtalonRelations(relationIds.values().stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList()));

            if (CollectionUtils.isEmpty(etalons)) {
                return Collections.emptyList();
            }

            return etalons.stream()
                .filter(po -> Objects.isNull(status) ? po.getStatus() == RecordStatus.ACTIVE : po.getStatus() == status)
                .collect(Collectors.groupingBy(RelationEtalonPO::getName))
                .entrySet()
                .stream()
                .map(e -> Pair.of(e.getKey(), Long.valueOf(e.getValue().size())))
                .collect(Collectors.toList());

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkHasDataByRelationName(String relName) {

        // This is a bad method and bad implementation.
        // Suppose, it is not called from places other then MM (MetaDraftValidation) import.
        return IntStream.range(0, StorageUtils.numberOfShards())
            .parallel()
            .anyMatch(shard -> shardSelect(shard)
                .jdbcTemplate()
                .queryForObject(templates.getQuery(RelationRecordsQuery.CHECK_EXIST_BY_RELNAME_SQL, shard), Boolean.class, relName));
    }

    @Override
    public void remapEtalonFromRecords(List<RelationEtalonRemapFromPO> pos) {

        if (CollectionUtils.isEmpty(pos)) {
            return;
        }

        pos.stream()
            .collect(Collectors.groupingBy(RelationEtalonRemapFromPO::getShard))
            .forEach(this::bulkRemapEtalonFromRecords);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remapEtalonToRecords(List<RelationEtalonRemapToPO> pos) {

        if (CollectionUtils.isEmpty(pos)) {
            return;
        }

        pos.stream()
            .collect(Collectors.groupingBy(RelationEtalonRemapToPO::getShard))
            .forEach(this::bulkRemapEtalonToRecords);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remapOriginRecords(List<RelationOriginRemapPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        records.stream()
            .collect(Collectors.groupingBy(RelationOriginRemapPO::getShard))
            .forEach(this::bulkRemapOriginRecords);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wipeRelationData(List<RelationKeysPO> relations) {

        if (CollectionUtils.isEmpty(relations)) {
            return;
        }

        relations.stream()
            .collect(Collectors.groupingBy(RelationKeysPO::getShard))
            .forEach(this::bulkWipeRelationData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wipeFromExternalKeys(List<RelationExternalKeyPO> keys) {

        if (CollectionUtils.isEmpty(keys)) {
            return;
        }

        keys.stream()
            .collect(Collectors.groupingBy(RelationExternalKeyPO::getFromShard))
            .forEach(this::bulkWipeFromExternalKeys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wipeToExternalKeys(List<RelationExternalKeyPO> keys) {

        if (CollectionUtils.isEmpty(keys)) {
            return;
        }

        keys.stream()
            .collect(Collectors.groupingBy(RelationExternalKeyPO::getToShard))
            .forEach(this::bulkWipeToExternalKeys);
    }

}
