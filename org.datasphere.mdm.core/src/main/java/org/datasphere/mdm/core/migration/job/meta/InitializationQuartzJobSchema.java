
package org.datasphere.mdm.core.migration.job.meta;

import org.datasphere.mdm.system.util.ResourceUtils;

import nl.myndocs.database.migrator.MigrationScript;
import nl.myndocs.database.migrator.definition.Migration;

/**
 * migration for create tables
 *
 * @author maria.chistyakova
 * @since 11.10.2019
 */
public class InitializationQuartzJobSchema implements MigrationScript {
    /**
     * Constructor.
     */
    public InitializationQuartzJobSchema() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String author() {
        return "Alexander Malyshev";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String migrationId() {
        return "InitializationQuartzJobSchema";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void migrate(Migration migration) {
        migration.raw()
                .sql(ResourceUtils.asString("classpath:/migration/quartz-db.sql"))
                .save();
    }
}
