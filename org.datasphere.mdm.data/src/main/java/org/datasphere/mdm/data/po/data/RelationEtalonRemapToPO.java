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

/**
 * @author Mikhail Mikhailov
 * Relation etalon record.
 */
public class RelationEtalonRemapToPO extends RelationEtalonPO {
    /**
     * Etalon ID to.
     */
    public static final String FIELD_NEW_ETALON_ID_TO = "etalon_id_to_new";
    /**
     * Origin ID to.
     */
    private String newEtalonIdTo;
    /**
     * Constructor.
     */
    public RelationEtalonRemapToPO() {
        super();
    }
    /**
     * @return the newEtalonIdTo
     */
    public String getNewEtalonIdTo() {
        return newEtalonIdTo;
    }
    /**
     * @param newEtalonIdTo the newEtalonIdTo to set
     */
    public void setNewEtalonIdTo(String newEtalonIdTo) {
        this.newEtalonIdTo = newEtalonIdTo;
    }
}
