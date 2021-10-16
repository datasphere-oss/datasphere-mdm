
package org.datasphere.mdm.core.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.datasphere.mdm.core.context.AuditEventWriteContext;

/**
 * @author Alexander Malyshev
 */
public interface AuditStorageService {
    /**
     * Known audit storage types.
     * @author Mikhail Mikhailov on Apr 25, 2020
     */
    enum AuditStorageType {
        /**
         * Database.
         */
        DATABASE("db"),
        /**
         * Indexing.
         */
        INDEX("es");
        /**
         * Constructor.
         * @param value the value
         */
        private AuditStorageType(String value) {
            this.value = value;
        }
        /**
         * The value.
         */
        private final String value;
        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        public static AuditStorageType[] fromValues(String... vals) {

            final AuditStorageType[] result = new AuditStorageType[AuditStorageType.values().length];
            for (int i = 0; ArrayUtils.isNotEmpty(vals) && i < vals.length; i++) {

                AuditStorageType hit = null;
                for (int j = 0; Objects.nonNull(vals[i]) && j < AuditStorageType.values().length; j++) {

                    if (AuditStorageType.values()[j].getValue().equals(vals[i])) {
                        hit = AuditStorageType.values()[j];
                        break;
                    }
                }

                if (Objects.nonNull(hit)) {
                    result[hit.ordinal()] = hit;
                }
            }

            return result;
        }

        public static String[] toValues(AuditStorageType[] types) {

            if (ArrayUtils.isEmpty(types)) {
                return ArrayUtils.EMPTY_STRING_ARRAY;
            }

            return Arrays.stream(types)
                    .filter(Objects::nonNull)
                    .map(AuditStorageType::getValue)
                    .toArray(String[]::new);
        }
    }
    /**
     * Write event to the index.
     * @param auditEventWriteContext the event
     */
    void write(Collection<AuditEventWriteContext> auditEventWriteContext);
    /**
     * Prepares storage for usage.
     */
    void prepare();
}
