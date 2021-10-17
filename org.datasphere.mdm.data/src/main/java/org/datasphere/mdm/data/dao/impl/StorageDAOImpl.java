/*
 * Unidata Platform Community Edition
 * Copyright (c) 2013-2020, UNIDATA LLC, All rights reserved.
 * This file is part of the Unidata Platform Community Edition software.
 *
 * Unidata Platform Community Edition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Unidata Platform Community Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.datasphere.mdm.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.sql.DataSource;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.data.dao.StorageDAO;
import org.datasphere.mdm.data.dao.rm.DataClusterRowMapper;
import org.datasphere.mdm.data.dao.rm.DataNodeRowMapper;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.po.storage.DataNodePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.datasphere.mdm.system.util.JsonUtils;

/**
 * @author Mikhail Mikhailov
 * Storage management DAO.
 */
@Repository
public class StorageDAOImpl extends BaseStorageDAOImpl implements StorageDAO {
    /**
     * Loads cluster metadata.
     */
    private final String loadClusterInfoSQL;
    /**
     * Updates cluster metadata.
     */
    private final String updateClusterInfoSQL;
    /**
     * Inserts cluster metadata.
     */
    private final String insertClusterInfoSQL;
    /**
     * Deletes cluster metadata.
     */
    private final String deleteClusterInfoSQL;
    /**
     * Loads nodes metadata.
     */
    private final String loadNodesInfoSQL;
    /**
     * Updates nodes metadata.
     */
    private final String updateNodesInfoSQL;
    /**
     * Inserts nodes metadata.
     */
    private final String insertNodesInfoSQL;
    /**
     * Deletes nodes metadata.
     */
    private final String deleteNodesInfoSQL;
    /**
     * Deletes all nodes metadata.
     */
    private final String deleteAllNodesInfoSQL;
    /**
     * Constructor.
     */
    @Autowired
    public StorageDAOImpl(
            @Qualifier("storageDataSource") DataSource dataSource,
            @Qualifier("data-sql") Properties sql) {
        super(dataSource);
        this.loadClusterInfoSQL = sql.getProperty("loadClusterInfoSQL");
        this.updateClusterInfoSQL = sql.getProperty("updateClusterInfoSQL");
        this.insertClusterInfoSQL = sql.getProperty("insertClusterInfoSQL");
        this.deleteClusterInfoSQL = sql.getProperty("deleteClusterInfoSQL");
        this.loadNodesInfoSQL = sql.getProperty("loadNodesInfoSQL");
        this.updateNodesInfoSQL = sql.getProperty("updateNodesInfoSQL");
        this.insertNodesInfoSQL = sql.getProperty("insertNodesInfoSQL");
        this.deleteNodesInfoSQL = sql.getProperty("deleteNodesInfoSQL");
        this.deleteAllNodesInfoSQL = sql.getProperty("deleteAllNodesInfoSQL");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataClusterPO load() {

        DataClusterPO cluster = jdbcTemplate.query(loadClusterInfoSQL, DataClusterRowMapper.DEFAULT_SINGLETON_EXTRACTOR);
        if (Objects.nonNull(cluster)) {
            List<DataNodePO> pos = jdbcTemplate.query(loadNodesInfoSQL, DataNodeRowMapper.DEFAULT_ROW_MAPPER);
            cluster.setNodes(pos);
        }

        return cluster;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(DataClusterPO info) {

        if (info.getId() == 0) {
            jdbcTemplate.update(insertClusterInfoSQL,
                    info.getName(),
                    info.getNumberOfShards(),
                    info.getDistributionFactor(),
                    info.hasData(),
                    info.isInitialized());
        } else {
            jdbcTemplate.update(updateClusterInfoSQL,
                    info.getName(),
                    info.getNumberOfShards(),
                    info.getDistributionFactor(),
                    info.hasData(),
                    info.isInitialized(),
                    info.getId());
        }

        if (CollectionUtils.isNotEmpty(info.getNodes())) {
            save(info.getNodes());
        }
    }

    /**
     * {@inheritDoc}
     */
    // TODO: make transactional
    @Override
    public void save(List<DataNodePO> nodes) {

        Map<Boolean, List<DataNodePO>> grouped = nodes.stream()
                .collect(Collectors.groupingBy(node -> node.getCreateDate() == null));
        for (Entry<Boolean, List<DataNodePO>> entry : grouped.entrySet()) {

            if (entry.getKey().booleanValue()) {
                jdbcTemplate.batchUpdate(insertNodesInfoSQL, entry.getValue(), entry.getValue().size(), (ps, v) -> {
                    ps.setInt(1, v.getId());
                    ps.setString(2, v.getName());
                    ps.setString(3, v.getHost());
                    ps.setInt(4, v.getPort());
                    ps.setString(5, v.getDatabase());
                    ps.setString(6, v.getUser());
                    ps.setString(7, v.getPassword());
                    ps.setString(8, JsonUtils.write(v.getSettings()));
                });
            } else {
                jdbcTemplate.batchUpdate(updateNodesInfoSQL, entry.getValue(), entry.getValue().size(), (ps, v) -> {
                    ps.setString(1, v.getName());
                    ps.setString(2, v.getHost());
                    ps.setInt(3, v.getPort());
                    ps.setString(4, v.getDatabase());
                    ps.setString(5, v.getUser());
                    ps.setString(6, v.getPassword());
                    ps.setString(7, JsonUtils.write(v.getSettings()));
                    ps.setTimestamp(8, v.getUpdateDate() == null ? new Timestamp(System.currentTimeMillis()) : new Timestamp(v.getUpdateDate().getTime()));
                    ps.setInt(9, v.getId());
                });
            }
        }
    }
}
