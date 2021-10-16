

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
public class UN12296InitializationJobCoreSchema implements MigrationScript {


    /**
     * Constructor.
     */
    public UN12296InitializationJobCoreSchema() {
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
        return "UN-12296__InitializationJobCoreSchema";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void migrate(Migration migration) {
        migration.raw()
                .sql(ResourceUtils.asStrings(
                        "classpath:/migration/UN-12296-job-meta-structure.sql",
                        "classpath:/migration/UN-12012-batch-schema.sql"))
                .save();
    }
}
