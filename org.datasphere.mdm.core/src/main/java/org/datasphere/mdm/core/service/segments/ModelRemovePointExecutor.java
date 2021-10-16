

package org.datasphere.mdm.core.service.segments;

import java.util.Objects;

import org.datasphere.mdm.core.context.ModelRemoveContext;
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
@Component(ModelRemovePointExecutor.SEGMENT_ID)
public class ModelRemovePointExecutor extends Point<ModelRemoveContext> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_REMOVE_POINT]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.remove.point.description";
    /**
     * MMS. Cheap and dirty.
     */
    @Autowired
    private MetaModelService metaModelService;
    /**
     * Constructor.
     */
    public ModelRemovePointExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void point(ModelRemoveContext ctx) {

        ModelImplementation<?> mi = metaModelService.implementation(ctx.getTypeId());
        if (Objects.nonNull(mi)) {
            mi.remove(ctx);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelRemoveContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
