
package org.datasphere.mdm.core.service.segments;

import java.util.Objects;

import org.datasphere.mdm.core.context.ModelRefreshContext;
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
@Component(ModelRefreshPointExecutor.SEGMENT_ID)
public class ModelRefreshPointExecutor extends Point<ModelRefreshContext> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_REFRESH_POINT]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.refresh.point.description";
    /**
     * MMS. Cheap and dirty.
     */
    @Autowired
    private MetaModelService metaModelService;
    /**
     * Constructor.
     */
    public ModelRefreshPointExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void point(ModelRefreshContext ctx) {

        ModelImplementation<?> mi = metaModelService.implementation(ctx.getTypeId());
        if (Objects.nonNull(mi)) {
            mi.refresh(ctx);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelRefreshContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
