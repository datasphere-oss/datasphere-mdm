

package org.datasphere.mdm.core.service.segments;

import java.util.Objects;

import org.datasphere.mdm.core.context.ModelGetContext;
import org.datasphere.mdm.core.dto.ModelGetResult;
import org.datasphere.mdm.core.module.CoreModule;
import org.datasphere.mdm.core.service.MetaModelService;
import org.datasphere.mdm.core.type.model.ModelImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Finish;
import org.datasphere.mdm.system.type.pipeline.Start;

/**
 * @author Mikhail Mikhailov on Nov 28, 2019
 * Model type agnostic get executor.
 */
@Component(ModelGetFinishExecutor.SEGMENT_ID)
public class ModelGetFinishExecutor extends Finish<ModelGetContext, ModelGetResult> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_GET_FINISH]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.get.finish.description";
    /**
     * MMS. Cheap and dirty.
     */
    @Autowired
    private MetaModelService metaModelService;
    /**
     * Constructor.
     */
    public ModelGetFinishExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, ModelGetResult.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ModelGetResult finish(ModelGetContext ctx) {

        ModelImplementation<?> mi = metaModelService.implementation(ctx.getTypeId());
        if (Objects.nonNull(mi)) {
            return mi.get(ctx);
        }

        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelGetContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
