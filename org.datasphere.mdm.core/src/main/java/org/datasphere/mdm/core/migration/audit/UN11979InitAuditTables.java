

package org.datasphere.mdm.core.migration.audit;

import nl.myndocs.database.migrator.MigrationScript;
import nl.myndocs.database.migrator.definition.Column;
import nl.myndocs.database.migrator.definition.Migration;

/**
 * @author Alexander Malyshev
 */
public class UN11979InitAuditTables implements MigrationScript {

    @Override
    public String migrationId() {
        return "UN-11979__InitAuditTables";
    }

    @Override
    public String author() {
        return "Alexander Malyshev";
    }

    @Override
    public void migrate(Migration migration) {
        migration.table("audit_event")
                .addColumn("type", Column.TYPE.VARCHAR, cb -> cb.notNull(true).size(255))
                .addColumn("parameters", Column.TYPE.TEXT)
                .addColumn("success", Column.TYPE.BOOLEAN, cb -> cb.notNull(true))
                .addColumn("login", Column.TYPE.VARCHAR, cb -> cb.notNull(true).size(255))
                .addColumn("client_ip", Column.TYPE.VARCHAR, cb -> cb.notNull(true).size(255))
                .addColumn("server_ip", Column.TYPE.VARCHAR, cb -> cb.notNull(true).size(255))
                .addColumn("endpoint", Column.TYPE.VARCHAR, cb -> cb.notNull(true).size(32))
                .addColumn("when_happened", Column.TYPE.TIMESTAMP, cb -> cb.notNull(true))
                .save();
    }
}
