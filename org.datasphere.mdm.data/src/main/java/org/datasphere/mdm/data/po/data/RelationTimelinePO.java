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

package org.datasphere.mdm.data.po.data;

import java.util.List;

import org.datasphere.mdm.data.po.keys.RelationKeysPO;
import org.datasphere.mdm.core.po.ObjectPO;

/**
 * @author Mikhail Mikhailov
 * Timeline vistory version.
 */
public class RelationTimelinePO implements ObjectPO {
    /**
     * Keys element, may be null.
     */
    public static final String FIELD_KEYS = "keys";
    /**
     * Timeline data - array of vistory objects. May be null.
     */
    public static final String FIELD_VISTORY_DATA = "vistory_data";
    /**
     * Keys object.
     */
    private RelationKeysPO keys;
    /**
     * Vistory records (timeline).
     */
    private List<RelationVistoryPO> vistory;
    /**
     * Constructor.
     */
    public RelationTimelinePO() {
        super();
    }
    /**
     * Utility constructor.
     * @param versions the versions to hold.
     */
    public RelationTimelinePO(List<RelationVistoryPO> versions) {
        this.vistory = versions;
    }
    /**
     * @return the keys
     */
    public RelationKeysPO getKeys() {
        return keys;
    }
    /**
     * @param keys the keys to set
     */
    public void setKeys(RelationKeysPO keys) {
        this.keys = keys;
    }
    /**
     * @return the vistory
     */
    public List<RelationVistoryPO> getVistory() {
        return vistory;
    }
    /**
     * @param recordEtalonId the recordEtalonId to set
     */
    public void setVistory(List<RelationVistoryPO> vistory) {
        this.vistory = vistory;
    }
}
