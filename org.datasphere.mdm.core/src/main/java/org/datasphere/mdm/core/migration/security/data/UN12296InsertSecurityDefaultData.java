

package org.datasphere.mdm.core.migration.security.data;

import org.datasphere.mdm.system.util.ResourceUtils;

import nl.myndocs.database.migrator.MigrationScript;
import nl.myndocs.database.migrator.definition.Migration;

/**
 * migration for insert secutiry resource, admin data
 *
 * @author maria.chistyakova
 * @since 11.10.2019
 */
public class UN12296InsertSecurityDefaultData implements MigrationScript {


    /**
     * Constructor.
     */
    public UN12296InsertSecurityDefaultData() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String author() {
        return "maria.chistyakova";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String migrationId() {
        return "UN-12296__InsertSecurityDefaultData";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void migrate(Migration migration) {
        migration.raw()
                .sql(ResourceUtils.asString(
                        "classpath:/migration/UN-12296-security-initialization-data.sql"))
                .save();
    }
}
