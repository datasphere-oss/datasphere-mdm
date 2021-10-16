
package org.datasphere.mdm.core.migration.bus;

import nl.myndocs.database.migrator.MigrationScript;
import nl.myndocs.database.migrator.definition.Column;
import nl.myndocs.database.migrator.definition.Migration;

/**
 * @author Alexander Malyshev
 */
public class InitBusConfigurationTables implements MigrationScript {

    @Override
    public String migrationId() {
        return "InitBusConfigurationTables";
    }

    @Override
    public String author() {
        return "Alexander Malyshev";
    }

    @Override
    public void migrate(Migration migration) {
        migration.table("bus_routes_definitions")
                .addColumn("routes_definition_id", Column.TYPE.VARCHAR, cb -> cb.primary(true).notNull(true).size(255))
                .addColumn("routes_definition", Column.TYPE.TEXT, cb -> cb.notNull(true))
                .save();
    }
}
