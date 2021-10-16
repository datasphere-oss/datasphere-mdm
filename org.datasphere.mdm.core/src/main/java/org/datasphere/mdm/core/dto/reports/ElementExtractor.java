

package org.datasphere.mdm.core.dto.reports;

import javax.annotation.Nonnull;

/**
 * Extract element from info holder
 *
 * @param <R> return type of value (can be Object)
 * @param <I> generalized info holder
 */
public interface ElementExtractor<R, I extends ReportInfoHolder> {

    /**
     * @param infoHolder - info holder
     * @return element
     */
    @Nonnull
    R getElement(I infoHolder);
}
