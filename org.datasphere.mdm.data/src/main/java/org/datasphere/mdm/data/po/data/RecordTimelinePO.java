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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.datasphere.mdm.core.po.ObjectPO;

/**
 * @author Mikhail Mikhailov
 * Timeline vistory version.
 */
public class RecordTimelinePO implements ObjectPO {
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
    private RecordKeysPO keys;
    /**
     * Vistory records (timeline).
     */
    private List<RecordVistoryPO> vistory;
    /**
     * Constructor.
     */
    public RecordTimelinePO() {
        super();
    }
    /**
     * Utility constructor.
     * @param versions the versions to hold.
     */
    public RecordTimelinePO(List<RecordVistoryPO> versions) {
        this.vistory = versions;
    }
    /**
     * @return the keys
     */
    public RecordKeysPO getKeys() {
        return keys;
    }
    /**
     * @param keys the keys to set
     */
    public void setKeys(RecordKeysPO keys) {
        this.keys = keys;
    }
    /**
     * @return the vistory
     */
    public List<RecordVistoryPO> getVistory() {
        return Objects.isNull(vistory) ? Collections.emptyList() : vistory;
    }
    /**
     * @param recordEtalonId the recordEtalonId to set
     */
    public void setVistory(List<RecordVistoryPO> vistory) {
        this.vistory = vistory;
    }
}
