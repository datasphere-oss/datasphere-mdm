

package org.datasphere.mdm.core.context;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author Alexander Malyshev
 */
public class AuditEventWriteContext {

    private final String currentUserStorageId;

    private final String domain;

    private final String type;

    private final boolean success;

    private final String throwableDump;

    private final String userLogin;

    private final String clientIp;

    private final String serverIp;

    private final String endpoint;

    private final LocalDateTime whenHappened;

    private final Map<String, Object> parameters;

    public AuditEventWriteContext(final AuditEventWriteContextBuilder builder) {
        currentUserStorageId = builder.currentUserStorageId;
        type = builder.type;
        domain = builder.domain;
        success = builder.success;
        throwableDump = builder.throwableDump;
        userLogin = builder.userLogin;
        clientIp = builder.clientIp;
        serverIp = builder.serverIp;
        endpoint = builder.endpoint;
        whenHappened = builder.whenHappened;
        parameters = Collections.unmodifiableMap(builder.parameters);
    }

    public String getCurrentUserStorageId() {
        return currentUserStorageId;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    public String getType() {
        return type;
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the throwableDump
     */
    public String getThrowableDump() {
        return throwableDump;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public LocalDateTime getWhenHappened() {
        return whenHappened;
    }

    public Map<String, Object> getParameters() {
        return MapUtils.isEmpty(parameters) ? Map.of() : parameters;
    }

    @Override
    public String toString() {
        return "AuditEventWriteContext { currentUserStorageId='" + currentUserStorageId
                + "', parameters='" + parameters + "' }";
    }

    public static AuditEventWriteContextBuilder builder() {
        return new AuditEventWriteContextBuilder();
    }

    public static class AuditEventWriteContextBuilder {
        private String currentUserStorageId;

        private String type;

        private String domain;

        private boolean success;

        private String throwableDump;

        private String userLogin;

        private String clientIp;

        private String serverIp;

        private String endpoint;

        private LocalDateTime whenHappened;

        private final Map<String, Object> parameters = new HashMap<>();

        public AuditEventWriteContextBuilder type(final String type) {
            this.type = type;
            return this;
        }

        public AuditEventWriteContextBuilder domain(final String domain) {
            this.domain = domain;
            return this;
        }

        public AuditEventWriteContextBuilder userLogin(final String userLogin) {
            this.userLogin = userLogin;
            return this;
        }

        public AuditEventWriteContextBuilder clientIp(final String clientIp) {
            this.clientIp = clientIp;
            return this;
        }

        public AuditEventWriteContextBuilder serverIp(final String serverIp) {
            this.serverIp = serverIp;
            return this;
        }

        public AuditEventWriteContextBuilder endpoint(final String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public AuditEventWriteContextBuilder whenHappened(final LocalDateTime whenHappened) {
            this.whenHappened = whenHappened;
            return this;
        }

        public AuditEventWriteContextBuilder throwableDump(Throwable cause) {
            if (Objects.nonNull(cause)) {

                StringBuilder dump = new StringBuilder()
                        .append(ExceptionUtils.getMessage(cause))
                        .append(StringUtils.CR)
                        .append(ExceptionUtils.getStackTrace(cause));
                this.throwableDump = dump.toString();
            }
            return this;
        }

        public AuditEventWriteContextBuilder success(final boolean success) {
            this.success = success;
            return this;
        }

        public AuditEventWriteContextBuilder parameters(final Map<String, Object> parameters) {
            if (parameters != null) {
                this.parameters.putAll(parameters);
            }
            return this;
        }

        public AuditEventWriteContext build() {
            return new AuditEventWriteContext(this);
        }
    }
}
