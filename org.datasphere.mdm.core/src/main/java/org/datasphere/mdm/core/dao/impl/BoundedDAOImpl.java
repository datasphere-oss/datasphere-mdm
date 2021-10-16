
package org.datasphere.mdm.core.dao.impl;

import javax.sql.DataSource;

import org.datasphere.mdm.core.dao.BoundedDAO;
import org.datasphere.mdm.system.dao.impl.BaseDAOImpl;

/**
 * @author Mikhail Mikhailov on Sep 9, 2021
 */
public abstract class BoundedDAOImpl extends BaseDAOImpl implements BoundedDAO {
    /**
     * The object's name this DAO is bound to.
     */
    private final String name;

    /**
     * Constructor.
     * @param dataSource
     */
    protected BoundedDAOImpl(DataSource dataSource, String name) {
        super(dataSource);
        this.name = name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void lock() {
        super.lock(getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
}
