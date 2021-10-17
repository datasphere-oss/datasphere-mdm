

package org.datasphere.mdm.data.dao.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.data.dao.RecordsDAO;
import org.datasphere.mdm.data.dao.rm.EtalonRecordDraftStateRowMapper;
import org.datasphere.mdm.data.dao.rm.ExtendedRecordVistoryRowMapper;
import org.datasphere.mdm.data.dao.rm.OriginRecordRowMapper;
import org.datasphere.mdm.data.dao.rm.RecordKeysRowMapper;
import org.datasphere.mdm.data.dao.rm.RecordTimelineRowMapper;
import org.datasphere.mdm.data.dao.rm.RecordVistoryRowMapper;
import org.datasphere.mdm.data.exception.DataExceptionIds;
import org.datasphere.mdm.data.exception.DataProcessingException;
import org.datasphere.mdm.data.po.EtalonRecordDraftStatePO;
import org.datasphere.mdm.data.po.data.RecordEtalonPO;
import org.datasphere.mdm.data.po.data.RecordOriginPO;
import org.datasphere.mdm.data.po.data.RecordOriginRemapPO;
import org.datasphere.mdm.data.po.data.RecordTimelinePO;
import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.datasphere.mdm.data.po.keys.RecordExternalKeysPO;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.datasphere.mdm.data.po.keys.RecordOriginKeyPO;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.serialization.DataSerializer;
import org.datasphere.mdm.data.util.StorageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.datasphere.mdm.core.dao.LargeObjectsDAO;
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
import org.datasphere.mdm.system.type.format.DumpTargetFormat;
import org.datasphere.mdm.system.type.runtime.MeasurementPoint;

/**
 * @author Mikhail Mikhailov Data records DAO.
 */
@Repository
public class RecordsDAOImpl extends BaseStorageDAOImpl implements RecordsDAO {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordsDAOImpl.class);
    /**
     * Empty (unresolved) key indicator.
     */
    private static final Object UNRESOLVED = new Object();

    /**
     * @author Mikhail Mikhailov
     * The queries.
     */
    enum DataRecordsQuery implements QueryTemplateDescriptor {
        CHANGE_ETALONS_STATUS_BATCH_SQL("changeEtalonsStatusBatchSQL", true),
        CHANGE_ORIGINS_STATUS_BATCH_SQL("changeOriginsStatusBatchSQL", true),
        INSERT_ETALON_SQL("insertEtalonSQL", true),
        UPDATE_ETALON_SQL("updateEtalonSQL", true),
        INSERT_ORIGIN_SQL("insertOriginSQL", true),
        UPDATE_ORIGIN_SQL("updateOriginSQL", true),
        REMAP_ORIGIN_RECORDS_SQL("remapOriginRecordsSQL", false),
        REMAP_VISTORY_RECORDS_SQL("remapVistoryRecordsSQL", false),
        INSERT_EXTERNAL_KEY_SQL("insertExternalKeySQL", true),
        UPDATE_EXTERNAL_KEY_SQL("updateExternalKeySQL", true),
        UPDATE_EXTERNAL_KEYS_SQL("updateExternalKeysSQL", true),
        UPDATE_VERSIONS_STATUS_SQL("updateVersionsStatusSQL", true),
        INSERT_ETALON_STATE_DRAFT_SQL("insertEtalonStateDraftSQL", false),
        CLEANUP_ETALON_STATE_DRAFTS_SQL("cleanupEtalonStateDraftsSQL", false),
        LOAD_LAST_ETALON_STATE_DRAFT_BY_ETALON_ID_SQL("loadLastEtalonStateDraftByEtalonIdSQL", false),

        LOAD_DATA_ID_BY_EXTERNAL_ID_SQL("loadDataIdByExternalIdSQL", true),
        LOAD_DATA_IDS_BY_EXTERNAL_IDS_SQL("loadDataIdsByExternalIdsSQL", true),
        LOAD_DATA_ID_BY_LSN_SQL("loadDataIdByLSNSQL", true),

        LOAD_KEYS_BY_ETALON_ID_SQL("loadKeysByEtalonIdSQL", true),
        LOAD_KEYS_BY_ETALON_IDS_SQL("loadKeysByEtalonIdsSQL", true),
        LOAD_KEYS_BY_LSNS_SQL("loadKeysByLSNsSQL", false),

        CUT_VISTORY_BY_ORIGIN_ID_SQL("cutVistoryByOriginIdSQL", true),

        DELETE_VISTORY_BY_ORIGIN_ID_SQL("deleteVistoryByOriginIdSQL", true),
        DELETE_ORIGIN_BY_ID_SQL("deleteOriginByIdSQL", true),
        DELETE_ETALON_BY_ID_SQL("deleteEtalonByIdSQL", true),
        DELETE_EXTERNAL_KEY_BY_COMPACTED_ID_SQL("deleteExternalKeyByCompactedIdSQL", true),
        PUT_VERSION_JAXB_SQL("putVersionJaxbSQL", true),
        PUT_VERSION_PROTOSTUFF_SQL("putVersionProtostuffSQL", true),
        LOAD_VERSIONS_BY_ETALON_ID_SQL("loadVersionsByEtalonIdSQL", true),
        LOAD_UNFILTERED_VERSIONS_BY_ETALON_ID_SQL("loadUnfilteredVersionsByEtalonIdSQL", true),
        LOAD_HISTORY_VERSIONS_BY_ETALON_ID_SQL("loadHistoryVersionsByEtalonIdSQL", true),
        LOAD_ALL_ORIGINS_FOR_ETLAONS_QUERY("loadAllOriginsForEtlaonsQuery", true),
        LOAD_TIMELINE_BY_ETALON_ID_SQL("loadTimelineByEtalonIdSQL", true);

        private DataRecordsQuery(String code, boolean distributed) {
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
            return QueryTemplateDescriptor.toTemplates(DataRecordsQuery.values(), new EnumMap<>(DataRecordsQuery.class), p);
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
     * Templates.
     */
    private QueryTemplates templates;
    /**
     * LOB DAO for wipe support.
     */
    @Autowired
    private LargeObjectsDAO largeObjectsDAO;
    /**
     * External utility support.
     */
    @Autowired
    public RecordsDAOImpl(
        @Qualifier("storageDataSource") final DataSource dataSource,
        @Qualifier("records-sql") final Properties sql) {
        super(dataSource);
        this.templates = DataRecordsQuery.toTemplates(sql);
        hook(this::initHook);
    }

    /**
     * Processes partition templates, when the cluster gets initialized.
     *
     * @return true, if successful, false otherwise
     */
    private void initHook(DataClusterPO cluster) {
        templates.init(cluster.getNumberOfShards());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertOriginRecords(List<RecordOriginPO> records, boolean areNew) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        records.stream()
            .collect(Collectors.groupingBy(RecordOriginPO::getShard))
            .forEach((shard, origins) -> {
                if (areNew) {
                    bulkInsertOriginRecords(shard, origins);
                } else {
                    bulkUpdateOriginRecords(shard, origins);
                }
            });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertExternalKeys(List<RecordExternalKeysPO> records, boolean areNew) {

        MeasurementPoint.start();
        try {

            if (areNew) {

                records.stream()
                    .collect(Collectors.groupingBy(RecordExternalKeysPO::getShard))
                    .forEach((shard, pos) ->
                        shardSelect(shard)
                            .jdbcTemplate()
                            .batchUpdate(templates.getQuery(DataRecordsQuery.INSERT_EXTERNAL_KEY_SQL, shard),
                                pos, pos.size(), (ps, record) -> {

                                    ps.setInt(1, record.getShard());
                                    ps.setString(2, record.getCompactKey());
                                    ps.setObject(3, record.getEtalonId());
                                })
                    );

            } else {

                records.stream()
                    .collect(Collectors.groupingBy(RecordExternalKeysPO::getShard))
                    .forEach((shard, pos) ->
                        shardSelect(shard)
                            .jdbcTemplate()
                            .batchUpdate(templates.getQuery(DataRecordsQuery.UPDATE_EXTERNAL_KEY_SQL, shard),
                                pos, pos.size(), (ps, record) -> {

                                    ps.setObject(1, record.getEtalonId());
                                    ps.setInt(2, record.getShard());
                                    ps.setString(3, record.getCompactKey());
                                })
                    );
            }
        } finally {
            MeasurementPoint.stop();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertEtalonRecords(List<RecordEtalonPO> records, boolean isNew) {

        MeasurementPoint.start();
        try {

            if (isNew) {

                int shard = records.get(0).getShard();

                // 1. Insert etalon record
                int[][] result = shardSelect(shard)
                    .jdbcTemplate()
                    .batchUpdate(templates.getQuery(DataRecordsQuery.INSERT_ETALON_SQL, shard), records, records.size(),
                        (ps, record) -> {

                            ps.setString(1, record.getName());
                            ps.setInt(2, record.getShard());
                            ps.setTimestamp(3, new Timestamp(record.getCreateDate().getTime()));
                            ps.setString(4, record.getCreatedBy());
                            ps.setString(5, record.getId());
                            ps.setString(6, record.getStatus() == null ? RecordStatus.ACTIVE.name()
                                : record.getStatus().name());
                            ps.setString(7, record.getOperationId());
                        });

                if (result[0].length != records.size()) {
                    throw new DataProcessingException("Cannot insert etalon records. Records inserted {}, input {}.",
                        DataExceptionIds.EX_DATA_ETALON_INSERT_FAILED, result.length, records.size());
                }
            } else {
                int shard = records.get(0).getShard();
                int[][] result = shardSelect(shard)
                    .jdbcTemplate()
                    .batchUpdate(templates.getQuery(DataRecordsQuery.UPDATE_ETALON_SQL, shard),
                        records, records.size(), (ps, record) -> {

                            ps.setTimestamp(1, VendorUtils.coalesce(record.getUpdateDate()));
                            ps.setString(2, record.getUpdatedBy());
                            ps.setString(3, record.getStatus() != null ? record.getStatus().name() : RecordStatus.ACTIVE.name());
                            ps.setString(4, record.getOperationId());
                            ps.setString(5, record.getId());
                        });

                if (result[0].length != records.size()) {
                    throw new DataProcessingException("Key doesn't exist or version is too old.",
                        DataExceptionIds.EX_DATA_ETALON_UPDATE_FAILED);
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
    public void upsertVersions(final List<RecordVistoryPO> upsert) {

        if (CollectionUtils.isEmpty(upsert)) {
            return;
        }

        MeasurementPoint.start();
        try {

            upsert.stream()
                .collect(Collectors.groupingBy(RecordVistoryPO::getShard))
                .forEach((shard, versions) -> {

                    String statement = templates.getQuery(DataRecordsQuery.PUT_VERSION_PROTOSTUFF_SQL, shard);
                    int[][] updates = shardSelect(shard)
                        .jdbcTemplate()
                        .batchUpdate(statement, versions, versions.size(), (ps, po) -> {

                            Timestamp createDate = po.getCreateDate() != null ? new Timestamp(po.getCreateDate().getTime()) : null;
                            Timestamp from = po.getValidFrom() != null ? new Timestamp(po.getValidFrom().getTime()) : null;
                            Timestamp to = po.getValidTo() != null ? new Timestamp(po.getValidTo().getTime()) : null;
                            String status = po.getStatus() == null ? RecordStatus.ACTIVE.name() : po.getStatus().name();
                            String shift = po.getShift() == null ? DataShift.PRISTINE.name() : po.getShift().name();
                            String operationType = po.getOperationType() == null ? OperationType.DIRECT.name() : po.getOperationType().name();

                            // IDs
                            ps.setString(1, po.getId());
                            ps.setString(2, po.getOriginId());
                            ps.setInt(3, po.getShard());

                            // Revision selection
                            ps.setString(4, po.getOriginId());
                            ps.setInt(5, po.getShard());
                            ps.setTimestamp(6, from);
                            ps.setTimestamp(7, to);

                            // Data
                            if (dumpTargetFormat.getValue() == DumpTargetFormat.PROTOSTUFF) {
                                ps.setBytes(8, DataSerializer.toProtostuff(po.getData()));
                            }

                            // Mark
                            ps.setTimestamp(9, createDate);
                            ps.setString(10, po.getCreatedBy());

                            // Flags
                            ps.setString(11, status);
                            ps.setString(12, shift);
                            ps.setString(13, operationType);
                            ps.setString(14, po.getOperationId());
                            ps.setInt(15, platformConfiguration.getPlatformMajor());
                            ps.setInt(16, platformConfiguration.getPlatformMinor());

                        });

                    if (updates.length == 0 || updates[0].length != versions.size()) {
                        throw new DataProcessingException("Batch insert vistory records [{}] failed.",
                            DataExceptionIds.EX_DATA_INSERT_VISTORY_BATCH_FAILED,
                            versions.size());
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
    public void remapOriginRecords(List<RecordOriginRemapPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            // The "bulk' is not a bulk really.
            // Use this method to process input.
            Map<Integer, List<RecordOriginRemapPO>> keys = records.stream()
                .collect(Collectors.groupingBy(RecordOriginRemapPO::getShard));

            for (Entry<Integer, List<RecordOriginRemapPO>> entry : keys.entrySet()) {
                bulkRemapOriginRecords(entry.getKey(), entry.getValue());
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertEtalonRecords(int shard, List<RecordEtalonPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String prolog = new StringBuilder()
                .append("copy record_etalons_p")
                .append(shard)
                .append(" (id, shard, name, create_date, created_by, status) from stdin binary")
                .toString();

            final VendorDataType[] types = {
                VendorDataType.UUID,
                VendorDataType.INT4,
                VendorDataType.CHAR,
                VendorDataType.TIMESTAMP,
                VendorDataType.CHAR,
                VendorDataType.CHAR
            };

            final Object[] params = new Object[types.length];
            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, prolog)) {

                for (RecordEtalonPO record : records) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getShard(), shard);
                    }

                    params[0] = UUID.fromString(record.getId());
                    params[1] = record.getShard();
                    params[2] = record.getName();
                    params[3] = record.getCreateDate();
                    params[4] = record.getCreatedBy();
                    params[5] = record.getStatus() == null ? RecordStatus.ACTIVE.name() : record.getStatus().name();
                    VendorUtils.bulkAppend(stream, types, params);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Bulk insert record etalons!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertOriginRecords(int shard, List<RecordOriginPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String originsProlog = new StringBuilder()
                .append("copy record_origins_p")
                .append(shard)
                .append(" (id, shard, etalon_id, initial_owner, name, source_system, external_id, create_date, created_by, status, enrichment) from stdin binary")
                .toString();

            final VendorDataType[] originTypes = {
                VendorDataType.UUID,
                VendorDataType.INT4,
                VendorDataType.UUID,
                VendorDataType.UUID,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.TIMESTAMP,
                VendorDataType.CHAR,
                VendorDataType.CHAR,
                VendorDataType.BOOLEAN
            };

            final Object[] originParams = new Object[originTypes.length];
            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, originsProlog)) {

                for (RecordOriginPO record : records) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getShard(), shard);
                    }

                    originParams[0] = UUID.fromString(record.getId());
                    originParams[1] = record.getShard();
                    originParams[2] = UUID.fromString(record.getEtalonId());
                    originParams[3] = record.getInitialOwner();
                    originParams[4] = record.getName();
                    originParams[5] = record.getSourceSystem();
                    originParams[6] = record.getExternalId();
                    originParams[7] = record.getCreateDate();
                    originParams[8] = record.getCreatedBy();
                    originParams[9] = record.getStatus() == null ? RecordStatus.ACTIVE.name() : record.getStatus().name();
                    originParams[10] = record.isEnrichment();

                    VendorUtils.bulkAppend(stream, originTypes, originParams);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Bulk insert record origins!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertExternalKeys(int shard, List<RecordExternalKeysPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String keysProlog = new StringBuilder().append("copy record_external_keys_p")
                .append(shard)
                .append(" (ext_shard, ext_key, etalon_id) from stdin binary")
                .toString();

            final VendorDataType[] keysTypes = {
                VendorDataType.INT4,
                VendorDataType.CHAR,
                VendorDataType.UUID
            };

            final Object[] keysParams = new Object[keysTypes.length];
            try (Connection connection = shardSelect(shard).dataSource().getConnection();
                 CopyDataOutputStream stream = VendorUtils.bulkStart(connection, keysProlog)) {

                for (RecordExternalKeysPO record : records) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getShard(), shard);
                    }

                    keysParams[0] = record.getShard();
                    keysParams[1] = record.getCompactKey();
                    keysParams[2] = record.getEtalonId();

                    VendorUtils.bulkAppend(stream, keysTypes, keysParams);
                }

                VendorUtils.bulkFinish(stream);
            } catch (SQLException e) {
                LOGGER.error("Bulk insert record external keys!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkInsertVersions(int shard, List<RecordVistoryPO> versions) {

        if (CollectionUtils.isEmpty(versions)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String statement = "copy record_vistory_p" + shard +
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

                for (RecordVistoryPO record : versions) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getShard(), shard);
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
                LOGGER.error("SQL exception caught!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    private void bulkInsertRawVersions(int shard, List<RecordVistoryPO> versions) {

        if (CollectionUtils.isEmpty(versions)) {
            return;
        }

        MeasurementPoint.start();
        try {

            final String statement = "copy record_vistory_p" + shard +
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

                for (RecordVistoryPO record : versions) {

                    if (record.getShard() != shard) {
                        throwShardNumbersDoesNotMatchException(record.getShard(), shard);
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
                LOGGER.error("SQL exception caught!", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkUpdateEtalonRecords(int shard, List<RecordEtalonPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            UUID[] ids = new UUID[records.size()];
            Timestamp[] updateDates = new Timestamp[records.size()];
            String[] updatedBy = new String[records.size()];
            String[] statuses = new String[records.size()];
            String[] operationIds = new String[records.size()];

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                ids[i] = UUID.fromString(records.get(i).getId());
                updateDates[i] = VendorUtils.coalesce(records.get(i).getUpdateDate());
                updatedBy[i] = records.get(i).getUpdatedBy();
                statuses[i] = records.get(i).getStatus() == null ? RecordStatus.ACTIVE.name() : records.get(i).getStatus().name();
                operationIds[i] = records.get(i).getOperationId();
            }

            try (Connection c = shardSelect(shard).dataSource().getConnection()) {

                Array ia = c.createArrayOf("uuid", ids);
                Array da = c.createArrayOf("timestamptz", updateDates);
                Array ua = c.createArrayOf("varchar", updatedBy);
                Array sa = c.createArrayOf("varchar", statuses);
                Array oa = c.createArrayOf("varchar", operationIds);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(DataRecordsQuery.CHANGE_ETALONS_STATUS_BATCH_SQL, shard), ia, da, ua, sa, oa);

            } catch (SQLException e) {
                LOGGER.warn("Cannot create record etalons update array.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkUpdateOriginRecords(int shard, List<RecordOriginPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

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
                    .update(templates.getQuery(DataRecordsQuery.CHANGE_ORIGINS_STATUS_BATCH_SQL, shard), ia, da, ua, sa, shard);

            } catch (SQLException e) {
                LOGGER.warn("Cannot create record origins update array.", e);
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkWipeRecordData(int shard, List<RecordKeysPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try (Connection c = shardSelect(shard).dataSource().getConnection()) {

            List<UUID> originIds = new ArrayList<>(records.size() * 2);
            List<UUID> etalonIds = new ArrayList<>(records.size());

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                etalonIds.add(UUID.fromString(records.get(i).getId()));
                for (int j = 0; records.get(i).getOriginKeys() != null && j < records.get(i).getOriginKeys().size(); j++) {
                    originIds.add(records.get(i).getOriginKeys().get(j).getId());
                }
            }

            JdbcTemplate ujt = shardSelect(shard).jdbcTemplate();
            if (CollectionUtils.isNotEmpty(originIds)) {

                largeObjectsDAO.cleanForSubjectIds(originIds.stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()));

                Array oia = c.createArrayOf("uuid", originIds.toArray(new UUID[originIds.size()]));

                ujt.update(templates.getQuery(DataRecordsQuery.DELETE_VISTORY_BY_ORIGIN_ID_SQL, shard), oia);
                ujt.update(templates.getQuery(DataRecordsQuery.DELETE_ORIGIN_BY_ID_SQL, shard), shard, oia);
            }

            if (CollectionUtils.isNotEmpty(etalonIds)) {

                Array eia = c.createArrayOf("uuid", etalonIds.toArray(new UUID[etalonIds.size()]));
                ujt.update(templates.getQuery(DataRecordsQuery.DELETE_ETALON_BY_ID_SQL, shard), eia);
            }

        } catch (SQLException e) {
            LOGGER.error("Failed to wipe record data.", e);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkWipeExternalKeys(int shard, List<RecordExternalKeysPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try (Connection c = shardSelect(shard).bareConnection()) {

            String[] externalIds = new String[records.size()];
            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                externalIds[i] = records.get(i).getCompactKey();
            }

            Array extida = c.createArrayOf("varchar", externalIds);

            shardSelect(shard)
                .jdbcTemplate()
                .update(templates.getQuery(DataRecordsQuery.DELETE_EXTERNAL_KEY_BY_COMPACTED_ID_SQL, shard), shard, extida);

        } catch (SQLException e) {
            LOGGER.error("Failed to wipe external ids.", e);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bulkRemapOriginRecords(int shard, List<RecordOriginRemapPO> remapList) {

        if (CollectionUtils.isEmpty(remapList)) {
            return;
        }

        MeasurementPoint.start();
        try {

            int sourceNode = StorageUtils.node(shard);

            // Group by local move possibility
            Map<Boolean, List<RecordOriginRemapPO>> partitionedByMoveType = remapList.stream()
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

                try (Connection connection = shardSelect(shard).bareConnection()) {

                    Array originIdsArray = connection.createArrayOf("uuid", originIds);
                    Array newEtalonIdsArray = connection.createArrayOf("uuid", newEtalonIds);
                    Array newShardsArray = connection.createArrayOf("int4", newShards);
                    Array updateDatesArray = connection.createArrayOf("timestamptz", updateDates);
                    Array updatedByArray = connection.createArrayOf("varchar", updatedBy);

                    // Move locally
                    if (sameNode) {

                        shardSelect(shard)
                            .jdbcTemplate()
                            .update(templates.getQuery(DataRecordsQuery.REMAP_ORIGIN_RECORDS_SQL),
                                originIdsArray,
                                newEtalonIdsArray,
                                newShardsArray,
                                updateDatesArray,
                                updatedByArray,
                                shard);

                        shardSelect(shard)
                            .jdbcTemplate()
                            .update(templates.getQuery(DataRecordsQuery.REMAP_VISTORY_RECORDS_SQL),
                                originIdsArray,
                                newShardsArray,
                                shard);

                        // Cut and paste to a new node
                    } else {

                        // Origin to new shard map
                        Map<String, Integer> originsToNewShard = new HashMap<>();

                        // Transform to new inserts. Origins must be completely filled out.
                        Map<Integer, List<RecordOriginPO>> newOrigins = records.stream()
                            .map(r -> {

                                originsToNewShard.put(r.getId(), r.getNewShard());

                                r.setShard(r.getNewShard());
                                r.setEtalonId(r.getNewEtalonId().toString());
                                return r;
                            })
                            .collect(Collectors.groupingBy(RecordOriginPO::getShard));

                        // Cut vistory objects.
                        Map<Integer, List<RecordVistoryPO>> newVistory = shardSelect(shard)
                            .jdbcTemplate()
                            .query(templates.getQuery(DataRecordsQuery.CUT_VISTORY_BY_ORIGIN_ID_SQL, shard), rs -> {

                                Map<Integer, List<RecordVistoryPO>> result = new HashMap<>();
                                while (rs.next()) {
                                    RecordVistoryPO po = RecordVistoryRowMapper.RAW_PROTOSTUFF_ROW_MAPPER.mapRow(rs, 0);
                                    po.setShard(originsToNewShard.get(po.getOriginId()));
                                    result.computeIfAbsent(po.getShard(), k -> new ArrayList<RecordVistoryPO>()).add(po);
                                }

                                return result;
                            }, originIdsArray);


                        shardSelect(shard)
                            .jdbcTemplate()
                            .update(templates.getQuery(DataRecordsQuery.DELETE_ORIGIN_BY_ID_SQL, shard), shard, originIdsArray);

                        // Put to new target.
                        newOrigins.forEach(this::bulkInsertOriginRecords);
                        newVistory.forEach(this::bulkInsertRawVersions);
                    }
                } catch (SQLException e) {
                    LOGGER.error("Cannot remap origins, SQL exception caught!", e);
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
    public void bulkUpdateExternalKeys(int shard, List<RecordExternalKeysPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            String[] extKeys = new String[records.size()];
            UUID[] etalonIds = new UUID[records.size()];

            for (int i = 0; i < records.size(); i++) {

                if (records.get(i).getShard() != shard) {
                    throwShardNumbersDoesNotMatchException(records.get(i).getShard(), shard);
                }

                extKeys[i] = records.get(i).getCompactKey();
                etalonIds[i] = records.get(i).getEtalonId();
            }

            try (Connection connection = shardSelect(shard).bareConnection()) {

                Array etalonIdsArray = connection.createArrayOf("uuid", etalonIds);
                Array extKeysArray = connection.createArrayOf("varchar", extKeys);

                shardSelect(shard)
                    .jdbcTemplate()
                    .update(templates.getQuery(DataRecordsQuery.UPDATE_EXTERNAL_KEYS_SQL, shard),
                        extKeysArray,
                        etalonIdsArray,
                        shard);

            } catch (SQLException e) {
                LOGGER.error("Cannot update external keys, SQL exception caught!", e);
            }
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upsertEtalonStateDraft(List<EtalonRecordDraftStatePO> drafts) {
        MeasurementPoint.start();
        try {

            if (CollectionUtils.isEmpty(drafts)) {
                return;
            }

            jdbcTemplate.batchUpdate(
                templates.getQuery(DataRecordsQuery.INSERT_ETALON_STATE_DRAFT_SQL),
                drafts, drafts.size(), (ps, pending) -> {
                    ps.setString(1, pending.getEtalonId());
                    ps.setString(2, pending.getEtalonId());
                    ps.setString(3, pending.getStatus() == null ? RecordStatus.ACTIVE.name() : pending.getStatus().name());
                    ps.setString(4, pending.getCreatedBy());
                });

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean cleanupEtalonStateDrafts(String etalonId) {

        boolean success = false;
        MeasurementPoint.start();
        try {
            success = jdbcTemplate.update(templates.getQuery(DataRecordsQuery.CLEANUP_ETALON_STATE_DRAFTS_SQL), etalonId) > 0;
        } finally {
            MeasurementPoint.stop();
        }

        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean putEtalonStateDraft(String etalonId, RecordStatus status, String createdBy) {

        boolean success = false;
        MeasurementPoint.start();
        try {
            success = jdbcTemplate.update(
                templates.getQuery(DataRecordsQuery.INSERT_ETALON_STATE_DRAFT_SQL),
                etalonId, etalonId, status == null ? RecordStatus.ACTIVE.name() : status.name(),
                createdBy == null ? SecurityUtils.getCurrentUserName() : createdBy) > 0;
        } finally {
            MeasurementPoint.stop();
        }

        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EtalonRecordDraftStatePO loadLastEtalonStateDraft(String etalonId) {

        MeasurementPoint.start();
        try {
            return jdbcTemplate.query(
                templates.getQuery(DataRecordsQuery.LOAD_LAST_ETALON_STATE_DRAFT_BY_ETALON_ID_SQL),
                EtalonRecordDraftStateRowMapper.DEFAULT_FIRST_RESULT_EXTRACTOR,
                etalonId);
        } finally {
            MeasurementPoint.stop();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void wipeRecordData(List<RecordKeysPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {

            // The "bulk' is not a bulk really.
            // Use this method to process input.
            Map<Integer, List<RecordKeysPO>> keys = records.stream()
                .collect(Collectors.groupingBy(RecordKeysPO::getShard));

            for (Entry<Integer, List<RecordKeysPO>> entry : keys.entrySet()) {
                bulkWipeRecordData(entry.getKey(), entry.getValue());
            }

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wipeExternalIds(List<RecordExternalKeysPO> records) {

        if (CollectionUtils.isEmpty(records)) {
            return;
        }

        MeasurementPoint.start();
        try {
            Map<Integer, List<RecordExternalKeysPO>> extIds = records.stream()
                .collect(Collectors.groupingBy(RecordExternalKeysPO::getShard));

            for (Entry<Integer, List<RecordExternalKeysPO>> entry : extIds.entrySet()) {
                // The "bulk' is not a bulk really.
                // Use it to process input.
                bulkWipeExternalKeys(entry.getKey(), entry.getValue());
            }
        } finally {
            MeasurementPoint.stop();
        }
    }

    @Override
    public UUID loadSysIdByExternalId(ExternalId extId) {

        MeasurementPoint.start();
        try {

            String extKey = extId.compact();
            int shard = StorageUtils.shard(extId);

            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_DATA_ID_BY_EXTERNAL_ID_SQL, shard),
                    rs -> rs.next()
                        ? rs.getObject(RecordExternalKeysPO.FIELD_ETALON_ID, UUID.class)
                        : null,
                    shard, extKey);

        } finally {
            MeasurementPoint.stop();
        }
    }

    @Override
    public UUID loadSysIdByLSN(int shard, long lsn) {

        MeasurementPoint.start();
        try {
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_DATA_ID_BY_LSN_SQL, shard),
                    rs -> rs.next()
                        ? rs.getObject(RecordKeysPO.FIELD_ID, UUID.class)
                        : null,
                    shard, lsn);

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordKeysPO loadRecordKeysByEtalonId(UUID val) {
        MeasurementPoint.start();
        try {

            int shard = StorageUtils.shard(val);
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_KEYS_BY_ETALON_ID_SQL, shard),
                    RecordKeysRowMapper.DEFAULT_FIRST_RESULT_EXTRACTOR,
                    val, val, val, val, val, val);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordKeysPO loadRecordKeysByExternalId(String externalId, String sourceSystem, String name) {
        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByExternalId(ExternalId.of(externalId, name, sourceSystem));
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadRecordKeysByEtalonId(sysId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordKeysPO loadRecordKeysByLSN(int shard, long lsn) {
        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByLSN(shard, lsn);
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadRecordKeysByEtalonId(sysId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    private Map<String, Object> execEtalonsMap(List<Object> values) {

        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyMap();
        }

        Map<String, Object> etalons = new HashMap<>(values.size());
        Map<Integer, List<UUID>> grouped = values.stream()
            .filter(Objects::nonNull)
            .map(Object::toString)
            .map(v -> {
                etalons.put(v, UNRESOLVED);
                return v;
            })
            .map(UUID::fromString)
            .collect(Collectors.groupingBy(StorageUtils::shard));

        for (Entry<Integer, List<UUID>> entry : grouped.entrySet()) {

            try (Connection connection = shardSelect(entry.getKey()).dataSource().getConnection()) {

                Array p = connection.createArrayOf("uuid", entry.getValue().toArray());

                List<RecordKeysPO> result = shardSelect(entry.getKey())
                    .jdbcTemplate()
                    .query(templates.getQuery(DataRecordsQuery.LOAD_KEYS_BY_ETALON_IDS_SQL, entry.getKey()),
                        RecordKeysRowMapper.DEFAULT_ARRAY_RESULT_EXTRACTOR,
                        p);

                for (int i = 0; i < result.size(); i++) {

                    RecordKeysPO row = result.get(i);
                    Object mark = etalons.get(row.getId());
                    if (Objects.nonNull(mark) && mark == UNRESOLVED) {

                        // Hack - HashMap will not modify old key value
                        etalons.put(row.getId(), row);
                    }
                }

            } catch (SQLException e) {
                LOGGER.warn("Cannot generate etalon id array.", e);
                return Collections.emptyMap();
            }
        }

        return etalons;
    }

    private Map<LSN, Object> execLSNMap(List<Object> values) {

        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyMap();
        }

        Map<LSN, Object> lsns = new HashMap<>(values.size());
        Map<Integer, List<Long>> grouped = values.stream()
            .filter(Objects::nonNull)
            .map(v -> (LSN) v)
            .map(v -> {
                lsns.put(v, UNRESOLVED);
                return v;
            })
            .collect(Collectors.groupingBy(LSN::getShard, Collectors.mapping(LSN::getLsn, Collectors.toList())));

        for (Entry<Integer, List<Long>> entry : grouped.entrySet()) {

            try (Connection connection = shardSelect(entry.getKey()).dataSource().getConnection()) {

                Array p = connection.createArrayOf("int8", entry.getValue().toArray());

                List<RecordKeysPO> result = shardSelect(entry.getKey())
                    .jdbcTemplate()
                    .query(templates.getQuery(DataRecordsQuery.LOAD_KEYS_BY_LSNS_SQL, entry.getKey()),
                        RecordKeysRowMapper.DEFAULT_ARRAY_RESULT_EXTRACTOR,
                        p, entry.getKey());

                for (int i = 0; i < result.size(); i++) {

                    RecordKeysPO row = result.get(i);
                    LSN lsn = LSN.of(row.getShard(), row.getLsn());
                    Object mark = lsns.get(lsn);
                    if (Objects.nonNull(mark) && mark == UNRESOLVED) {

                        // Hack - HashMap will not modify old key value
                        lsns.put(lsn, row);
                    }
                }

            } catch (SQLException e) {
                LOGGER.warn("Cannot generate LSN id array.", e);
                return Collections.emptyMap();
            }
        }

        return lsns;
    }
    /*
    private Map<ExternalId, Object> prepareExternalIdMap(StringBuilder sqlBuffer, List<Object> values) {

        Map<ExternalId, Object> externalIds = new HashMap<>(values.size());
        Map<Integer, List<ExternalId>> grouped = values.stream()
                .filter(Objects::nonNull)
                .map(v -> (ExternalId) v)
                .map(v -> {
                    externalIds.put(v, UNRESOLVED);
                    return v;
                })
                .collect(Collectors.groupingBy(StorageUtils::shard));

        final StringBuilder localSql = new StringBuilder();
        for (Object val : values) {

            ExternalId eid = (ExternalId) val;
            if (localSql.length() > 0) {
                localSql.append(StringUtils.LF).append("union select '");
            } else {
                localSql.append("select '");
            }

            localSql.append(eid.getSourceSystem()).append("'::text as source_system, '").append(eid.getId())
                    .append("'::text as external_id, '").append(eid.getEntityName()).append("'::text as name ");

            externalIds.put(eid, UNRESOLVED);
        }

        String replacement = StringUtils.replace(
                templates.getQuery(DataRecordsQuery.LOAD_DATA_IDS_BY_EXTERNAL_IDS_SQL), ":external_id",
                localSql.toString());

        if (sqlBuffer.length() > 0) {
            sqlBuffer.append(StringUtils.LF).append("union ").append(StringUtils.LF);
        }

        sqlBuffer.append(replacement);
        return externalIds;
    }
    */

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Object, RecordKeysPO> loadRecordKeys(Map<IdSetType, List<Object>> ids) {

        MeasurementPoint.start();
        try {

            if (MapUtils.isEmpty(ids)) {
                return Collections.emptyMap();
            }

            /*
            final MapSqlParameterSource params = new MapSqlParameterSource();
            StringBuilder sqlBuffer = new StringBuilder();
            */

            final Map<String, Object> etalons = execEtalonsMap(ids.get(IdSetType.ETALON_ID));
            final Map<LSN, Object> lsns = execLSNMap(ids.get(IdSetType.LSN));
            //final Map<ExternalId, Object> eids = prepareExternalIdMap(sqlBuffer, ids.get(IdSetType.EXTERNAL_ID));
            /*
            return namedJdbcTemplate.query(sqlBuffer.toString(), params, rs -> {

                while (rs.next()) {

                    RecordKeysPO row = RecordKeysRowMapper.DEFAULT_ROW_MAPPER.mapRow(rs, 0);

                    // Etalon id found.
                    Object mark = etalons.get(row.getId());
                    if (Objects.nonNull(mark) && mark == UNRESOLVED) {

                        // Hack - HashMap will not modify old key value
                        etalons.put(row.getId(), row);
                        continue;
                    }

                    // LSN found
                    LSN lsn = LSN.of(row.getShard(), row.getLsn());
                    mark = lsns.get(lsn);
                    if (Objects.nonNull(mark) && mark == UNRESOLVED) {

                        // Hack - HashMap will not modify old key value
                        lsns.put(lsn, row);
                        continue;
                    }

                    // Ext id found
                    ExternalId eid = row.getOriginKeys().stream()
                            .map(ok -> ExternalId.of(ok.getExternalId(), row.getName(), ok.getSourceSystem()))
                            .filter(eids::containsKey).findFirst().orElse(null);

                    mark = eids.get(eid);
                    if (Objects.nonNull(mark) && mark == UNRESOLVED) {

                        // Hack - HashMap will not modify old key value
                        eids.put(eid, row);
                        continue;
                    }
                }
            });
            */

            Map<Object, RecordKeysPO> result = new IdentityHashMap<>(etalons.size() + /* eids.size() + */ lsns.size());

            result.putAll(etalons.entrySet().stream().filter(entry -> entry.getValue() != UNRESOLVED)
                .collect(Collectors.toMap(Entry::getKey, entry -> (RecordKeysPO) entry.getValue())));

            result.putAll(lsns.entrySet().stream().filter(entry -> entry.getValue() != UNRESOLVED)
                .collect(Collectors.toMap(Entry::getKey, entry -> (RecordKeysPO) entry.getValue())));
            /*
            result.putAll(eids.entrySet().stream().filter(entry -> entry.getValue() != UNRESOLVED)
                    .collect(Collectors.toMap(Entry::getKey, entry -> (RecordKeysPO) entry.getValue())));
            */
            return result;

        } finally {
            MeasurementPoint.stop();
        }
    }

    @Override
    public Map<String, List<RecordOriginPO>> findAllActiveOriginsForEtlaons(List<String> etalonsIds) {

        if (CollectionUtils.isEmpty(etalonsIds)) {
            return Collections.emptyMap();
        }

        Map<Integer, List<UUID>> input = etalonsIds.stream()
            .map(UUID::fromString)
            .collect(Collectors.groupingBy(StorageUtils::shard));

        final Map<String, List<RecordOriginPO>> result = new HashMap<>();
        input.forEach((shard, ids) -> {
            List<RecordOriginPO> or = shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_ALL_ORIGINS_FOR_ETLAONS_QUERY, shard),
                    OriginRecordRowMapper.DEFAULT_ROW_MAPPER, ids);

            for (int i = 0; i < or.size(); i++) {
                result.computeIfAbsent(or.get(i).getEtalonId(), key -> new ArrayList<>()).add(or.get(i));
            }
        });

        return result;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByEtalonId(UUID idVal, boolean fetchKeys, Date point, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            Timestamp asOf = VendorUtils.coalesce(point);
            int shard = StorageUtils.shard(idVal);

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_VERSIONS_BY_ETALON_ID_SQL, shard), rs ->
                        rs.next()
                            ? RecordTimelineRowMapper.DEFAULT_RECORD_TIMELINE_ROW_MAPPER.mapRow(rs, 0)
                            : null,
                    fetchKeys,
                    idVal, idVal, idVal, idVal, idVal, idVal,
                    null, // operation_id
                    idVal,
                    null, // operation_id
                    idVal,
                    null, // operation_id
                    null, // lud
                    asOf, includeDrafts, userName,
                    null, null); // updates_after, updates_after

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByEtalonIdAndLastUpdateDate(
        UUID idVal, boolean fetchKeys, Date point, Date lud, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            Timestamp asOf = VendorUtils.coalesce(point);
            int shard = StorageUtils.shard(idVal);

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_VERSIONS_BY_ETALON_ID_SQL, shard), rs ->
                        rs.next()
                            ? RecordTimelineRowMapper.DEFAULT_RECORD_TIMELINE_ROW_MAPPER.mapRow(rs, 0)
                            : null,
                    fetchKeys,
                    idVal, idVal, idVal, idVal, idVal, idVal,
                    null, // operation_id
                    idVal,
                    null, // operation_id
                    idVal,
                    null, // operation_id
                    lud != null ? new Timestamp(lud.getTime()) : null, // lud
                    asOf, includeDrafts, userName,
                    null, null); // updates_after, updates_after
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByEtalonIdAndUpdatesAfter(
        UUID idVal, boolean fetchKeys, Date point, Date updatesAfter, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            Timestamp asOf = VendorUtils.coalesce(point);
            int shard = StorageUtils.shard(idVal);

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_VERSIONS_BY_ETALON_ID_SQL, shard), rs ->
                        rs.next()
                            ? RecordTimelineRowMapper.DEFAULT_RECORD_TIMELINE_ROW_MAPPER.mapRow(rs, 0)
                            : null,
                    fetchKeys,
                    idVal, idVal, idVal, idVal, idVal, idVal,
                    null, // operation_id
                    idVal,
                    null, // operation_id
                    idVal,
                    null, // operation_id
                    null, // lud
                    asOf, includeDrafts, userName,
                    updatesAfter != null ? new Timestamp(updatesAfter.getTime()) : null,  // updates_after
                    updatesAfter != null ? new Timestamp(updatesAfter.getTime()) : null); // updates_after
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByEtalonIdAndOperationId(
        UUID idVal, boolean fetchKeys, Date point, String operationId, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            Timestamp asOf = VendorUtils.coalesce(point);
            int shard = StorageUtils.shard(idVal);

            // fetch_keys, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, etalon_id, operation_id, etalon_id, operation_id, etalon_id, operation_id,
            // lud, point, is_approver, user_name, updates_after, updates_after
            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_VERSIONS_BY_ETALON_ID_SQL, shard), rs ->
                        rs.next()
                            ? RecordTimelineRowMapper.DEFAULT_RECORD_TIMELINE_ROW_MAPPER.mapRow(rs, 0)
                            : null,
                    fetchKeys,
                    idVal, idVal, idVal, idVal, idVal, idVal,
                    operationId, // operation_id
                    idVal,
                    operationId, // operation_id
                    idVal,
                    operationId, // operation_id
                    null, // lud
                    asOf, includeDrafts, userName,
                    null, null); // updates_after, updates_after
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByExternalId(ExternalId externalId, Date point, boolean includeDrafts,
                                                     String userName) {

        MeasurementPoint.start();
        try {

            UUID sysKey = loadSysIdByExternalId(externalId);
            if (Objects.isNull(sysKey)) {
                return null;
            }

            return loadVersionsByEtalonId(sysKey, true, point, includeDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByExternalIdAndLastUpdateDate(ExternalId externalId, Date point,
                                                                      Date lud, boolean loadDrafts, String userName) {

        MeasurementPoint.start();
        try {

            UUID sysKey = loadSysIdByExternalId(externalId);
            if (Objects.isNull(sysKey)) {
                return null;
            }

            return loadVersionsByEtalonIdAndLastUpdateDate(sysKey, true, point, lud, loadDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByExternalIdAndUpdatesAfter(ExternalId externalId, Date point,
                                                                    Date updatesAfter, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            UUID sysKey = loadSysIdByExternalId(externalId);
            if (Objects.isNull(sysKey)) {
                return null;
            }

            return loadVersionsByEtalonIdAndUpdatesAfter(sysKey, true, point, updatesAfter, includeDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByExternalIdAndOperationId(ExternalId externalId, Date point,
                                                                   String operationId, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            UUID sysKey = loadSysIdByExternalId(externalId);
            if (Objects.isNull(sysKey)) {
                return null;
            }

            return loadVersionsByEtalonIdAndOperationId(sysKey, true, point, operationId, includeDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByLSN(LSN lsn, boolean fetchKeys, Date point, boolean includeDrafts,
                                              String userName) {

        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByLSN(lsn.getShard(), lsn.getLsn());
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadVersionsByEtalonId(sysId, fetchKeys, point, includeDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByLSNAndLastUpdateDate(LSN lsn, boolean fetchKeys, Date point, Date lud,
                                                               boolean loadDrafts, String userName) {

        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByLSN(lsn.getShard(), lsn.getLsn());
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadVersionsByEtalonIdAndLastUpdateDate(sysId, fetchKeys, point, lud, loadDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByLSNAndUpdatesAfter(LSN lsn, boolean fetchKeys, Date point, Date updatesAfter,
                                                             boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByLSN(lsn.getShard(), lsn.getLsn());
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadVersionsByEtalonIdAndUpdatesAfter(sysId, fetchKeys, point, updatesAfter, includeDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadVersionsByLSNAndOperationId(LSN lsn, boolean fetchKeys, Date point,
                                                            String operationId, boolean includeDrafts, String userName) {

        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByLSN(lsn.getShard(), lsn.getLsn());
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadVersionsByEtalonIdAndOperationId(sysId, fetchKeys, point, operationId, includeDrafts, userName);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RecordVistoryPO> loadVersionsUnfilterdByEtalonId(String id, Date point) {

        final RowMapper<RecordVistoryPO> rm = RecordVistoryRowMapper.DEFAULT_PROTOSTUFF_ROW_MAPPER;

        Timestamp asOf = VendorUtils.coalesce(point);
        UUID idVal = UUID.fromString(id);
        int shard = StorageUtils.shard(idVal);
        return shardSelect(shard)
            .jdbcTemplate()
            .query(templates.getQuery(DataRecordsQuery.LOAD_UNFILTERED_VERSIONS_BY_ETALON_ID_SQL, shard), rm, idVal, asOf);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<RecordOriginKeyPO, List<RecordVistoryPO>> loadHistory(String etalonId) {

        final RowMapper<RecordVistoryPO> rm = ExtendedRecordVistoryRowMapper.DEFAULT_EXTENDED_RECORD_VISTORY_PROTOSTUFF_ROW_MAPPER;

        UUID val = UUID.fromString(etalonId);
        int shard = StorageUtils.shard(val);

        final Map<RecordOriginKeyPO, List<RecordVistoryPO>> result = new HashMap<>();
        shardSelect(shard)
            .jdbcTemplate()
            .query(templates.getQuery(DataRecordsQuery.LOAD_HISTORY_VERSIONS_BY_ETALON_ID_SQL, shard), (rs, rowNum) -> {

                RecordVistoryPO po = rm.mapRow(rs, rowNum);

                RecordOriginKeyPO key = new RecordOriginKeyPO();
                key.setId(UUID.fromString(po.getOriginId()));
                key.setSourceSystem(po.getSourceSystem());
                key.setExternalId(po.getExternalId());
                key.setCreateDate(po.getCreateDate());
                key.setCreatedBy(po.getCreatedBy());
                key.setEnrichment(po.isEnrichment());
                key.setRevision(po.getRevision());
                key.setStatus(po.getStatus());
                key.setUpdateDate(po.getUpdateDate());
                key.setUpdatedBy(po.getUpdatedBy());

                result.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(po);

                return po;
            }, val);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadTimeline(UUID etalonId, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        MeasurementPoint.start();
        try {

            int shard = StorageUtils.shard(etalonId);
            Timestamp lastUpdateTimestamp = lastUpdate == null ? null : new Timestamp(lastUpdate.getTime());
            Timestamp updatesAfterTimestamp = updatesAfter == null ? null : new Timestamp(updatesAfter.getTime());

            return shardSelect(shard)
                .jdbcTemplate()
                .query(templates.getQuery(DataRecordsQuery.LOAD_TIMELINE_BY_ETALON_ID_SQL, shard), rs ->
                        rs.next()
                            ? RecordTimelineRowMapper.DEFAULT_RECORD_TIMELINE_ROW_MAPPER.mapRow(rs, 0)
                            : null,
//============================
//                  Keys
//============================
                    fetchKeys,
                    etalonId,
                    etalonId,
                    etalonId,
                    etalonId,
                    etalonId,
                    etalonId,
//============================
//                  Operation ID LUD
//============================
                    operationId,
                    etalonId,
                    operationId,
//============================
//                  Records
//============================
                    etalonId,
                    operationId,
                    lastUpdateTimestamp,
                    operationId,
                    lastUpdateTimestamp,
                    fetchData,
                    shard,
//============================
//                  Updates after
//============================
                    updatesAfterTimestamp,
                    updatesAfterTimestamp);

        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadTimeline(ExternalId externalId, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByExternalId(externalId);
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadTimeline(sysId, fetchKeys, fetchData, lastUpdate, updatesAfter, operationId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO loadTimeline(LSN lsnId, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        MeasurementPoint.start();
        try {

            UUID sysId = loadSysIdByLSN(lsnId.getShard(), lsnId.getLsn());
            if (Objects.isNull(sysId)) {
                return null;
            }

            return loadTimeline(sysId, fetchKeys, fetchData, lastUpdate, updatesAfter, operationId);
        } finally {
            MeasurementPoint.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, RecordTimelinePO>
    loadTimelines(List<String> etalonIds, boolean fetchKeys, boolean fetchData, Date lastUpdate, Date updatesAfter, String operationId) {

        MeasurementPoint.start();
        try {

            return etalonIds.stream()
                .filter(Objects::nonNull)
                .map(id -> Pair.of(id, loadTimeline(UUID.fromString(id), fetchKeys, fetchData, lastUpdate, updatesAfter, operationId)))
                .filter(p -> Objects.nonNull(p.getValue()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        } finally {
            MeasurementPoint.stop();
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateVistoryStatus(int shard, List<String> ids, RecordStatus status) {
        MeasurementPoint.start();
        try {

            Map<String, Object> params = new HashMap<>(2);
            params.put("ids", ids.stream().map(UUID::fromString).collect(Collectors.toList()));
            params.put(RecordVistoryPO.FIELD_STATUS, status.name());

            shardSelect(shard)
                .namedJdbcTemplate()
                .update(templates.getQuery(DataRecordsQuery.UPDATE_VERSIONS_STATUS_SQL, shard), params);

            return true;
        } finally {
            MeasurementPoint.stop();
        }
    }
}
