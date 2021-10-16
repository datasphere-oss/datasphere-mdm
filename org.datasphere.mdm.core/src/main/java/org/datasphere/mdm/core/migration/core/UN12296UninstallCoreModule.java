
package org.datasphere.mdm.core.migration.core;

import nl.myndocs.database.migrator.MigrationScript;
import nl.myndocs.database.migrator.definition.Migration;

/**
 * cleanup core security schema migration
 *
 * @author maria.chistyakova
 * @since 11.10.2019
 */
public class UN12296UninstallCoreModule implements MigrationScript {

    /**
     * Constructor.
     */
    public UN12296UninstallCoreModule() {
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
        return "UN-12296__UninstallCoreModule";
    }

    /**
    /**
     * drop tables, not schema
     *
     * {@inheritDoc}
     */
    @Override
    public void migrate(Migration migration) {
        migration.raw().sql("select 'drop table if exists \"' || tablename || '\" cascade;' \n" +
                "  from pg_tables\n" +
                " where schemaname = 'org_unidata_mdm_core'; ").save();
    }
}
