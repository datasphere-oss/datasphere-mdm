

package org.datasphere.mdm.core.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.datasphere.mdm.core.context.AuditEventWriteContext;
import org.datasphere.mdm.core.dao.AuditDao;
import org.datasphere.mdm.core.exception.CoreExceptionIds;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.datasphere.mdm.system.dao.impl.BaseDAOImpl;
import org.datasphere.mdm.system.exception.PlatformFailureException;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Alexander Malyshev
 */
@Repository
public class AuditDaoImpl extends BaseDAOImpl implements AuditDao {

    public static final String TYPE_FIELD = "type";
    public static final String PARAMETERS_FIELD = "parameters";
    public static final String SUCCESS_FIELD = "success";
    public static final String LOGIN_FIELD = "login";
    public static final String CLIENT_IP_FIELD = "client_ip";
    public static final String SERVER_IP_FIELD = "server_ip";
    public static final String ENDPOINT_FIELD = "endpoint";
    public static final String WHEN_HAPPENED_FIELD = "when_happened";

    private final ObjectMapper objectMapper;

    private final String insertEnhancedAuditEvent;

    public AuditDaoImpl(
            final ObjectMapper objectMapper,
            @Qualifier("coreDataSource") final DataSource coreDataSource,
            @Qualifier("audit-sql") final Properties sql
    ) {
        super(coreDataSource);
        this.objectMapper = objectMapper;
        insertEnhancedAuditEvent = sql.getProperty("INSERT_AUDIT_EVENT");
    }

    @Override
    public boolean insert(Collection<AuditEventWriteContext> auditEventWriteContexts) {
        namedJdbcTemplate.batchUpdate(
                insertEnhancedAuditEvent,
                auditEventWriteContexts.stream()
                        .map(this::queryParameters)
                        .map(MapSqlParameterSource::new)
                        .toArray(MapSqlParameterSource[]::new)
        );
        return true;
    }

    private Map<String, ? extends Serializable> queryParameters(final AuditEventWriteContext auditEventWriteContext) {
        return Map.of(
                TYPE_FIELD, auditEventWriteContext.getType(),
                PARAMETERS_FIELD, convertParameters(auditEventWriteContext),
                SUCCESS_FIELD, auditEventWriteContext.isSuccess(),
                LOGIN_FIELD, auditEventWriteContext.getUserLogin(),
                CLIENT_IP_FIELD, auditEventWriteContext.getClientIp(),
                SERVER_IP_FIELD, auditEventWriteContext.getServerIp(),
                ENDPOINT_FIELD, auditEventWriteContext.getEndpoint(),
                WHEN_HAPPENED_FIELD, auditEventWriteContext.getWhenHappened()
        );
    }

    private String convertParameters(final AuditEventWriteContext auditEventWriteContext) {
        final Map<String, Object> parameters = new HashMap<>(auditEventWriteContext.getParameters());
        parameters.entrySet().stream()
                .filter(e -> e.getValue() instanceof Throwable)
                .forEach(e -> parameters.put(e.getKey(), e.getValue().toString()));
        try {
            return objectMapper.writeValueAsString(parameters);
        } catch (JsonProcessingException e) {
            throw new PlatformFailureException(
                    "Can't serialize audit event parameters " + parameters,
                    e,
                    CoreExceptionIds.EX_AUDIT_EVENT_JSON_SERIALIZATION_EXCEPTION,
                    parameters
            );
        }
    }

    @Override
    public long deleteOldAuditEvents(long maxLifetime) {
        return 0;
    }
}
