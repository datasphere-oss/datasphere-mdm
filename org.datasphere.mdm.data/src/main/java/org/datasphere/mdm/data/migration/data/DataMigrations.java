
package org.datasphere.mdm.data.migration.data;

import nl.myndocs.database.migrator.MigrationScript;

/**
 * @author Mikhail Mikhailov
 * Data migrations.
 */
public final class DataMigrations {
    /**
     * Migrations.
     */
    private static final MigrationScript[] MIGRATIONS = {
        new DataModuleInitialMigration()
    };
    /**
     * Constructor.
     */
    private DataMigrations() {
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
