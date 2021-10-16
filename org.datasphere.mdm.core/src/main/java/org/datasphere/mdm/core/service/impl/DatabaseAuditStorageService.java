

package org.datasphere.mdm.core.service.impl;

import java.util.Collection;

import org.datasphere.mdm.core.configuration.CoreConfigurationConstants;
import org.datasphere.mdm.core.context.AuditEventWriteContext;
import org.datasphere.mdm.core.dao.AuditDao;
import org.datasphere.mdm.core.service.AuditStorageService;
import org.springframework.stereotype.Service;
import org.datasphere.mdm.system.type.annotation.ConfigurationRef;
import org.datasphere.mdm.system.type.configuration.ConfigurationValue;

/**
 * @author Alexander Malyshev
 */
@Service("databaseAuditStorageService")
public class DatabaseAuditStorageService implements AuditStorageService {

    private final AuditDao auditDao;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_AUDIT_ENABLED)
    private ConfigurationValue<Boolean> auditEnabled;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_AUDIT_ENABLED_STORAGES)
    private ConfigurationValue<AuditStorageType[]> enabledStorages;

    public DatabaseAuditStorageService(final AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(Collection<AuditEventWriteContext> auditEventWriteContext) {

        // Audit is either disabled entirely or just the DB is disabled
        if (!auditEnabled.getValue().booleanValue()
          || enabledStorages.getValue()[AuditStorageType.DATABASE.ordinal()] == null) {
            return;
        }

        auditDao.insert(auditEventWriteContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        // NOP for DB
    }
}
