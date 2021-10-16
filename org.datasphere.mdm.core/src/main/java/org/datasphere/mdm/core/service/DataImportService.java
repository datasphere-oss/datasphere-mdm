
package org.datasphere.mdm.core.service;

import java.util.Collection;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.context.DataImportInputContext;
import org.datasphere.mdm.core.context.DataImportTemplateContext;
import org.datasphere.mdm.core.dto.DataImportTemplateResult;
import org.datasphere.mdm.core.type.load.DataImportHandler;
import org.datasphere.mdm.core.type.load.DataImportHandlerInfo;

/**
 * @author Mikhail Mikhailov on May 13, 2021
 * The data load service, responsible for loading various kinds of data in different formats.
 * The actual work of loading data is delegated to handlers, which are registered at runtime.
 */
public interface DataImportService {
    /**
     * Registers a data handler.
     * @param handler the handler to register
     */
    void register(@Nonnull DataImportHandler handler);
    /**
     * Loads data.
     * @param ctx the context
     */
    void handle(DataImportInputContext ctx);
    /**
     * Gets a template for import, if this is supported by the handler.
     * @param ctx the context
     * @return result
     */
    DataImportTemplateResult template(DataImportTemplateContext ctx);
    /**
     * returns info about curently registered handlers.
     * @return collection of info elements
     */
    Collection<DataImportHandlerInfo> handlers();
}
