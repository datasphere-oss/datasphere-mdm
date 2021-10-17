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
import java.util.Date;
import java.util.Map;

import org.datasphere.mdm.system.dao.PoolSetting;

/**
 * @author Mikhail Mikhailov
 * Node attrs.
 */
public class DataNodePO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "nodes_info";
    /**
     * Generated id.
     */
    public static final String FIELD_ID = "id";
    /**
     * Node's name.
     */
    public static final String FIELD_NAME = "name";
    /**
     * PostgreSQL host name.
     */
    public static final String FIELD_HOST = "host";
    /**
     * PostgreSQL port number.
     */
    public static final String FIELD_PORT = "port";
    /**
     * PostgreSQL database name.
     */
    public static final String FIELD_DATABASE = "dbname";
    /**
     * PostgreSQL database user.
     */
    public static final String FIELD_USER = "username";
    /**
     * PostgreSQL database password.
     */
    public static final String FIELD_PASSWORD = "password";
    /**
     * Bitronix settings.
     */
    public static final String FIELD_SETTINGS = "settings";
    /**
     * Create timestamp.
     */
    public static final String FIELD_CREATE_DATE = "create_date";
    /**
     * Update timestamp.
     */
    public static final String FIELD_UPDATE_DATE = "update_date";
    /**
     * Generated id.
     */
    private int id;
    /**
     * The node name.
     */
    private String name;
    /**
     * PostgreSQL host.
     */
    private String host;
    /**
     * Port.
     */
    private int port;
    /**
     * PostgreSQL database.
     */
    private String database;
    /**
     * PostgreSQL user.
     */
    private String user;
    /**
     * PostgreSQL password.
     */
    private String password;
    /**
     * Create date.
     */
    private Date createDate;
    /**
     * Update date.
     */
    private Date updateDate;
    /**
     * Pool settings.
     */
    private Map<PoolSetting, String> settings = Collections.emptyMap();
    /**
     * Constructor.
     */
    public DataNodePO() {
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
     * @return the host
     */
    public String getHost() {
        return host;
    }
    /**
     * @param host the host to set
     */
    public void setHost(String url) {
        this.host = url;
    }
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }
    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the settings
     */
    public Map<PoolSetting, String> getSettings() {
        return settings;
    }
    /**
     * @param settings the settings to set
     */
    public void setSettings(Map<PoolSetting, String> settings) {
        this.settings = settings;
    }
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
