
package org.datasphere.mdm.core.service.impl;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.datasphere.mdm.core.context.AbstractImportContext;
import org.datasphere.mdm.core.context.DataImportInputContext;
import org.datasphere.mdm.core.context.DataImportTemplateContext;
import org.datasphere.mdm.core.dto.DataImportTemplateResult;
import org.datasphere.mdm.core.exception.CoreExceptionIds;
import org.datasphere.mdm.core.service.DataImportService;
import org.datasphere.mdm.core.type.load.DataImportHandler;
import org.datasphere.mdm.core.type.load.DataImportHandlerInfo;
import org.springframework.stereotype.Service;
import org.datasphere.mdm.system.exception.PlatformBusinessException;

/**
 * @author Mikhail Mikhailov on May 13, 2021
 */
@Service
public class DataImportServiceImpl implements DataImportService {
    /**
     * Load handlers.
     */
    private final ConcurrentMap<String, DataImportHandler> registry = new ConcurrentHashMap<>();
    /**
     * Constructor.
     */
    public DataImportServiceImpl() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void register(DataImportHandler handler) {
        Objects.requireNonNull(handler, "Import handler instance must not be null.");
        Objects.requireNonNull(handler.getId(), "Import handler ID must not be null.");
        registry.put(handler.getId(), handler);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(DataImportInputContext ctx) {

        DataImportHandler dlh = ensureHandler(ctx);
        ensureFormat(dlh, ctx);

        dlh.handle(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataImportTemplateResult template(DataImportTemplateContext ctx) {

        DataImportHandler dlh = ensureHandler(ctx);
        ensureFormat(dlh, ctx);

        return dlh.template(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<DataImportHandlerInfo> handlers() {
        return registry.values().stream()
                .map(DataImportHandlerInfo::new)
                .collect(Collectors.toList());
    }

    private DataImportHandler ensureHandler(AbstractImportContext ctx) {

        DataImportHandler dlh = registry.get(ctx.getTarget());
        if (Objects.isNull(dlh)) {
            throw new PlatformBusinessException("Data import handler [{}] not registered.",
                    CoreExceptionIds.EX_DATA_LOAD_HANDLER_NOT_FOUND, ctx.getTarget());
        }

        return dlh;
    }

    private void ensureFormat(DataImportHandler dlh, AbstractImportContext ctx) {
        if (!dlh.getSupported().contains(ctx.getFormat())) {
            throw new PlatformBusinessException("Data import format [{}] is not supported by the handler.",
                    CoreExceptionIds.EX_DATA_LOAD_FORMAT_NOT_SUPPORTED, ctx.getFormat());
        }
    }
}
