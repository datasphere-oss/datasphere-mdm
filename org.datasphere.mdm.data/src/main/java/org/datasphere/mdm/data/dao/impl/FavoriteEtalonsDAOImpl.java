/*
 * Unidata Platform
 * Copyright (c) 2013-2020, UNIDATA LLC, All rights reserved.
 *
 * Commercial License
 * This version of Unidata Platform is licensed commercially and is the appropriate option for the vast majority of use cases.
 *
 * Please see the Unidata Licensing page at: https://unidata-platform.com/license/
 * For clarification or additional options, please contact: info@unidata-platform.com
 * -------
 * Disclaimer:
 * -------
 * THIS SOFTWARE IS DISTRIBUTED "AS-IS" WITHOUT ANY WARRANTIES, CONDITIONS AND
 * REPRESENTATIONS WHETHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, MERCHANTABLE QUALITY,
 * FITNESS FOR A PARTICULAR PURPOSE, DURABILITY, NON-INFRINGEMENT, PERFORMANCE AND
 * THOSE ARISING BY STATUTE OR FROM CUSTOM OR USAGE OF TRADE OR COURSE OF DEALING.
 */
package org.datasphere.mdm.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.core.util.SecurityUtils;
import org.datasphere.mdm.data.dao.FavoriteEtalonsDAO;
import org.datasphere.mdm.data.dao.FavoriteEtalonsRowHandler;
import org.datasphere.mdm.system.dao.impl.BaseDAOImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 * @author Alexey Tsarapkin
 */
@Repository
public class FavoriteEtalonsDAOImpl extends BaseDAOImpl implements FavoriteEtalonsDAO {

    private class CursorStatementCreator implements PreparedStatementCreator {

        private final String sql;
        private final int fetchSize;

        public CursorStatementCreator(String sql, int fetchSize) {
            this.sql = sql;
            this.fetchSize = fetchSize;
        }

        @Override
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
            final PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            statement.setFetchSize(fetchSize);
            return statement;
        }
    }

    private String addfavoriteEtalon;

    private String removeFavoriteEtalon;

    private String excludeFavoriteEtalon;

    private String excludeFavoriteEtalonsByEntity;

    private String removeAllFavoriteEtalonsByEntity;

    private String findFavoriteEtalon;

    private String getFavoriteEtalonsByEntity;

    private String getAllFavoriteEtalonsByEntity;

    private String getFavoriteEtalons;

    private String getFavoriteEtalonsMapByEntity;

    private String getAllFavoriteEtalonsMap;

    private String getFavoriteEtalonsMap;

    private String getFavoriteEntityNames;

    public FavoriteEtalonsDAOImpl(
            @Qualifier("storageDataSource") DataSource storageDataSource,
            @Qualifier("user-favorite-etalons-sql") Properties sql) {

        super(storageDataSource);
        addfavoriteEtalon = sql.getProperty("ADD_FAVORITE_ETALON");
        removeFavoriteEtalon = sql.getProperty("REMOVE_FAVORITE_ETALON");
        excludeFavoriteEtalon = sql.getProperty("EXCLUDE_FAVORITE_ETALON");
        excludeFavoriteEtalonsByEntity = sql.getProperty("EXCLUDE_FAVORITE_ETALONS_BY_ENTITY");
        removeAllFavoriteEtalonsByEntity = sql.getProperty("REMOVE_ALL_FAVORITE_ETALONS_BY_ENTITY");

        findFavoriteEtalon = sql.getProperty("FIND_FAVORITE_ETALON");
        getFavoriteEtalonsByEntity = sql.getProperty("GET_FAVORITE_ETALONS_BY_ENTITY");
        getAllFavoriteEtalonsByEntity = sql.getProperty("GET_ALL_FAVORITE_ETALONS_BY_ENTITY");
        getFavoriteEtalons = sql.getProperty("GET_FAVORITE_ETALONS");
        getFavoriteEtalonsMapByEntity = sql.getProperty("GET_FAVORITE_ETALONS_MAP_BY_ENTITY");
        getFavoriteEtalonsMap = sql.getProperty("GET_FAVORITE_ETALONS_MAP");

        getAllFavoriteEtalonsMap = sql.getProperty("GET_ALL_FAVORITE_ETALONS_MAP");

        getFavoriteEntityNames = sql.getProperty("GET_ALL_FAVORITE_ENTITY_NAMES");
    }

    @Override
    public void add(Map<String, List<UUID>> favorites) {
        favorites.forEach((entityName, etalons) ->
            jdbcTemplate.batchUpdate(
                    addfavoriteEtalon,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, entityName);
                            ps.setObject(2, etalons.get(i));
                            ps.setString(3, SecurityUtils.getCurrentUserName());
                        }

                        @Override
                        public int getBatchSize() {
                            return etalons.size();
                        }
                    }
            )
        );
    }

    @Override
    public void add(String userLogin, String entityName, UUID favorite) {
        jdbcTemplate.update(addfavoriteEtalon, entityName, favorite, userLogin);
    }

    @Override
    public void removeUUID(UUID favorite) {
        jdbcTemplate.update(removeFavoriteEtalon, favorite);
    }

    @Override
    public void exclude(UUID favorite) {
        jdbcTemplate.update(excludeFavoriteEtalon, SecurityUtils.getCurrentUserName(), favorite);
    }

    @Override
    public void exclude(List<UUID> favorites) {
        jdbcTemplate.batchUpdate(
                excludeFavoriteEtalon,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setObject(1, favorites.get(i));
                        ps.setString(2, SecurityUtils.getCurrentUserName());
                    }

                    @Override
                    public int getBatchSize() {
                        return favorites.size();
                    }
                }
        );
    }

    @Override
    public void excludeByEntity(String entityName) {
        jdbcTemplate.update(excludeFavoriteEtalonsByEntity, SecurityUtils.getCurrentUserName(), entityName);
    }


    @Override
    public void removeAllByEntity(String entityName) {
        jdbcTemplate.update(removeAllFavoriteEtalonsByEntity, entityName);
    }

    @Override
    public boolean find(UUID uuid) {
        return jdbcTemplate.queryForObject(
                findFavoriteEtalon, Integer.class,
                SecurityUtils.getCurrentUserName(), uuid
        ) > 0;
    }

    @Override
    public List<UUID> getEtalons() {
        return jdbcTemplate.queryForList(
                getFavoriteEtalons, UUID.class,
                SecurityUtils.getCurrentUserName()
        );
    }

    @Override
    public List<UUID> getEtalons(String entityName) {
        return jdbcTemplate.queryForList(
                getFavoriteEtalonsByEntity, UUID.class,
                SecurityUtils.getCurrentUserName(), entityName
        );
    }

    @Override
    public List<UUID> getAllEtalons(String entityName) {
        return jdbcTemplate.queryForList(
                getAllFavoriteEtalonsByEntity, UUID.class,
                entityName
        );
    }

    @Override
    public Map<String, List<UUID>> getEtalonsMap() {
        return jdbcTemplate.query(getFavoriteEtalonsMap,
                (rs, n) -> Pair.of(rs.getString("entity_name"), UUID.fromString(rs.getString("etalon_id"))),
                    SecurityUtils.getCurrentUserName())
                .stream()
                .collect(Collectors.groupingBy(Pair::getKey,
                         Collectors.mapping(Pair::getValue, Collectors.toList())));
    }

    @Override
    public Map<String, List<UUID>> getEtalonsMap(String entityName) {
        return jdbcTemplate.query(getFavoriteEtalonsMapByEntity,
                (rs, n) -> Pair.of(rs.getString("entity_name"), UUID.fromString(rs.getString("etalon_id"))),
                    SecurityUtils.getCurrentUserName(), entityName)
                .stream()
                .collect(Collectors.groupingBy(Pair::getKey,
                         Collectors.mapping(Pair::getValue, Collectors.toList())));
    }

    @Override
    public void getAllFavorites(FavoriteEtalonsRowHandler handler, int fetchSize) {
        jdbcTemplate.query(new CursorStatementCreator(getAllFavoriteEtalonsMap, fetchSize), rs -> {
            handler.processRow(rs.getString(CREATED_BY), UUID.fromString(rs.getString(ETALON_ID)));
        });
    }

    @Override
    public Collection<String> getFavoriteEntityNames() {
        return jdbcTemplate.queryForList(getFavoriteEntityNames, String.class);
    }
}
