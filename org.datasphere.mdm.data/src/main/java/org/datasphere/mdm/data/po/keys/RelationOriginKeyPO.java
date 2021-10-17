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

package org.datasphere.mdm.data.po.keys;

import java.util.UUID;

/**
 * @author Mikhail Mikhailov
 * Concrete relation origin key class.
 */
public class RelationOriginKeyPO extends AbstractOriginKeyPO {
    /**
     * Origin from id.
     */
    public static final String FIELD_FROM_KEY = "from_key";
    /**
     * Origin from id.
     */
    public static final String FIELD_TO_KEY = "to_key";
    /**
     * Origin from ID.
     */
    protected UUID fromKey;
    /**
     * Origin to ID.
     */
    protected UUID toKey;
    /**
     * Constructor.
     */
    public RelationOriginKeyPO() {
        super();
    }
    /**
     * @return the fromKey
     */
    public UUID getFromKey() {
        return fromKey;
    }
    /**
     * @param fromKey the fromKey to set
     */
    public void setFromKey(UUID fromKey) {
        this.fromKey = fromKey;
    }
    /**
     * @return the toKey
     */
    public UUID getToKey() {
        return toKey;
    }
    /**
     * @param toKey the toKey to set
     */
    public void setToKey(UUID toKey) {
        this.toKey = toKey;
    }
}
