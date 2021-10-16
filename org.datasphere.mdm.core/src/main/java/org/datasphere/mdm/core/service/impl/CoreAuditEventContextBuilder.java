

package org.datasphere.mdm.core.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.datasphere.mdm.core.context.AuditEventWriteContext;
import org.datasphere.mdm.system.type.messaging.SystemHeaders;
/**
 * Core event builder.
 */
public final class CoreAuditEventContextBuilder {
    /**
     * Constructor.
     */
    private CoreAuditEventContextBuilder() {
        super();
    }
    /**
     * Build the target audit context.
     * @param exchange the exchange
     * @return audit context
     */
    public static AuditEventWriteContext build(final Exchange exchange) {
        final Map<String, Object> headers = new HashMap<>(exchange.getIn().getHeaders());
        return AuditEventWriteContext.builder()
                .domain(headers.remove(SystemHeaders.DOMAIN.getName()).toString())
                .type(headers.remove(SystemHeaders.TYPE.getName()).toString())
                .userLogin(headers.remove(SystemHeaders.LOGIN.getName()).toString())
                .clientIp(headers.remove(SystemHeaders.CLIENT_IP.getName()).toString())
                .serverIp(headers.remove(SystemHeaders.SERVER_IP.getName()).toString())
                .endpoint(headers.remove(SystemHeaders.ENDPOINT.getName()).toString())
                .whenHappened(LocalDateTime.ofInstant((Instant) headers.remove(SystemHeaders.WHEN_HAPPENED.getName()), ZoneId.systemDefault()))
                .success(!headers.containsKey(SystemHeaders.THROWABLE.getName()))
                .throwableDump((Throwable) headers.remove(SystemHeaders.THROWABLE.getName()))
                .parameters(headers)
                .build();
    }
}
