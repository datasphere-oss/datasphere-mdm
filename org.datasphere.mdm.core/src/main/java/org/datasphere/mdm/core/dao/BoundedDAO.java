
package org.datasphere.mdm.core.dao;

import org.datasphere.mdm.system.dao.BaseDAO;

/**
 * @author Mikhail Mikhailov on Sep 9, 2021
 * A DAO, bounded mostly to a single table.
 */
public interface BoundedDAO extends BaseDAO {
    /**
     * Locks its main table.
     */
    void lock();
    /**
     * Returns the object's name this DAO is bound to.
     * @return the object's name this DAO is bound to
     */
    String getName();
}
