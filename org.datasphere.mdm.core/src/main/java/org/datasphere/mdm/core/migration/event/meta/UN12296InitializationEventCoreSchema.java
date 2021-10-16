

package org.datasphere.mdm.core.migration.event.meta;

import org.datasphere.mdm.system.util.ResourceUtils;

import nl.myndocs.database.migrator.MigrationScript;
import nl.myndocs.database.migrator.definition.Migration;

/**
 * @author maria.chistyakova
 * @since 11.10.2019
 */
public class UN12296InitializationEventCoreSchema implements MigrationScript {


    /**
     * Constructor.
     */
    public UN12296InitializationEventCoreSchema() {
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
        return "UN-12296__UN12296InitializationEventCoreSchema";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void migrate(Migration migration) {
        migration.raw()
                .sql(ResourceUtils.asString("classpath:/migration/UN-11830-binary-data-structure.sql"))
                .save();
    }
}
