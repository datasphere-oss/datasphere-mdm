

package org.datasphere.mdm.core.dto.reports;

import javax.annotation.Nonnull;

/**
 * @param <E> type of elements in a report
 */
public interface Report<E> {

    /**
     * Start new row in a report
     * Note: A meaning of row depends on type of report.
     */
    void newRow();

    /**
     * Add element of report to row.
     *
     * @param element - element of report in row
     */
    void addElement(@Nonnull E element);

    /**
     * @return array of bytes
     */
    @Nonnull
    byte[] generate();
}
