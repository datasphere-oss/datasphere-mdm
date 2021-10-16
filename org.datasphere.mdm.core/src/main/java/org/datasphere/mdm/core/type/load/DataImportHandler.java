
package org.datasphere.mdm.core.type.load;

import java.util.Collection;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.context.DataImportInputContext;
import org.datasphere.mdm.core.context.DataImportTemplateContext;
import org.datasphere.mdm.core.dto.DataImportTemplateResult;

/**
 * @author Mikhail Mikhailov on May 13, 2021
 * A load handler, capable to load data from supplied input stream.
 */
public interface DataImportHandler {
    /**
     * Gets the unique load handler name.
     * @return handler id
     */
    @Nonnull
    String getId();
    /**
     * Gets translated handler description.
     * @return description
     */
    @Nonnull
    String getDescription();
    /**
     * Returns import formats, supported by this load handler.
     * @return import formats, supported by this load handler
     */
    Collection<DataImportFormat> getSupported();
    /**
     * Loads the supplied data.
     * @param ctx the context
     */
    void handle(DataImportInputContext ctx);
    /**
     * Gets
     * @param ctx
     * @return
     */
    default DataImportTemplateResult template(DataImportTemplateContext ctx) {
        throw new UnsupportedOperationException(getId() + " handler does not support templates.");
    }
}
