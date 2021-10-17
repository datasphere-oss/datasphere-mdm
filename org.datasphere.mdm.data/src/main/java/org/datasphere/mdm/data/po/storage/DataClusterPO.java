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

package org.datasphere.mdm.data.po.storage;

import java.util.Collections;
import java.util.List;

/**
 * @author Mikhail Mikhailov
 * Primitive cluster description.
 */
public class DataClusterPO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "cluster_info";
    /**
     * Generated id.
     */
    public static final String FIELD_ID = "id";
    /**
     * Discriptive name.
     */
    public static final String FIELD_NAME = "name";
    /**
     * Number of primary shards.
     */
    public static final String FIELD_NUMBER_OF_SHARDS = "shards_number";
    /**
     * Distribution factor.
     */
    public static final String FIELD_DISTRIBUTION_FACTOR = "distribution_factor";
    /**
     * Has data or not.
     */
    public static final String FIELD_HAS_DATA = "has_data";
    /**
     * Initialized or not.
     * The cluster is initialized, if primary shards have been assigned to nodes.
     */
    public static final String FIELD_INITIALIZED = "initialized";
    /**
     * Version number.
     */
    public static final String FIELD_VERSION = "version";
    /**
     * Generated id.
     */
    private int id;
    /**
     * Discriptive name.
     */
    private String name;
    /**
     * Number of primary shards.
     */
    private int numberOfShards;
    /**
     * Distribution factor.
     */
    private int distributionFactor;
    /**
     * Has data or not.
     */
    private boolean hasData;
    /**
     * Initialized or not.
     * The cluster is initialized, if primary shards have been assigned to nodes.
     */
    private boolean initialized;
    /**
     * The metadata version.
     */
    private int version;
    /**
     * Nodes list.
     */
    private List<DataNodePO> nodes = Collections.emptyList();
    /**
     * Constructor.
     */
    public DataClusterPO() {
        super();
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the numberOfShards
     */
    public int getNumberOfShards() {
        return numberOfShards;
    }
    /**
     * @param numberOfShards the numberOfShards to set
     */
    public void setNumberOfShards(int numberOfShards) {
        this.numberOfShards = numberOfShards;
    }
    /**
     * @return the distributionFactor
     */
    public int getDistributionFactor() {
        return distributionFactor;
    }
    /**
     * @param distributionFactor the distributionFactor to set
     */
    public void setDistributionFactor(int distributionFactor) {
        this.distributionFactor = distributionFactor;
    }
    /**
     * @return the nodes
     */
    public List<DataNodePO> getNodes() {
        return nodes;
    }
    /**
     * @param nodes the nodes to set
     */
    public void setNodes(List<DataNodePO> nodes) {
        this.nodes = nodes;
    }
    /**
     * @return the hasData
     */
    public boolean hasData() {
        return hasData;
    }
    /**
     * @param hasData the hasData to set
     */
    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }
    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
    /**
     * @param initialized the initialized to set
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

}
