

package org.datasphere.mdm.core.migration;

import org.datasphere.mdm.core.migration.core.UN12296UninstallCoreModule;

import nl.myndocs.database.migrator.MigrationScript;

/**
 * storage migrations for uninstall core security
 *
 * @author maria.chistyakova
 */
public final class UninstallCoreSchemaMigrations {

    private static final MigrationScript[] MIGRATIONS = {
        new UN12296UninstallCoreModule()
    };
    /**
     * Constructor.
     */
    private UninstallCoreSchemaMigrations() {
        super();
    }

    /**
     * Makes SONAR happy.
     * @return migrations
     */
    public static MigrationScript[] migrations() {
        return MIGRATIONS;
    }


}
