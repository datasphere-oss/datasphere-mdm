
package org.datasphere.mdm.core.dto.reports.csv;

import javax.annotation.Nonnull;

/**
 * Csv header
 */
public interface CvsHeader {
    /**
     * @return header name
     */
    @Nonnull
    String headerName();
}
