
package org.datasphere.mdm.core.service.segments;

import java.util.Objects;

import org.datasphere.mdm.core.context.ModelChangeContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.datasphere.mdm.core.service.MetaModelService;
import org.datasphere.mdm.core.type.model.ModelImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Point;
import org.datasphere.mdm.system.type.pipeline.Start;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelUpsertPointExecutor.SEGMENT_ID)
public class ModelUpsertPointExecutor extends Point<ModelChangeContext> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_UPSERT_POINT]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.upsert.point.description";
    /**
     * MMS. Cheap and dirty.
     */
    @Autowired
    private MetaModelService metaModelService;
    /**
     * Constructor.
     */
    public ModelUpsertPointExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void point(ModelChangeContext ctx) {

        ModelImplementation<?> mi = metaModelService.implementation(ctx.getTypeId());
        if (Objects.nonNull(mi)) {
            mi.upsert(ctx);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelChangeContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
