

package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelRemoveContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Finish;
import org.datasphere.mdm.system.type.pipeline.Start;
import org.datasphere.mdm.system.type.pipeline.defaults.VoidPipelineOutput;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelRemoveFinishExecutor.SEGMENT_ID)
public class ModelRemoveFinishExecutor extends Finish<ModelRemoveContext, VoidPipelineOutput> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_REMOVE_FINISH]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.remove.finish.description";
    /**
     * Constructor.
     */
    public ModelRemoveFinishExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, VoidPipelineOutput.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public VoidPipelineOutput finish(ModelRemoveContext ctx) {
        return VoidPipelineOutput.INSTANCE;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelRemoveContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
