

package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelRefreshContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Finish;
import org.datasphere.mdm.system.type.pipeline.Start;
import org.datasphere.mdm.system.type.pipeline.defaults.VoidPipelineOutput;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelRefreshFinishExecutor.SEGMENT_ID)
public class ModelRefreshFinishExecutor extends Finish<ModelRefreshContext, VoidPipelineOutput> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_REFRESH_FINISH]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.refresh.finish.description";
    /**
     * Constructor.
     */
    public ModelRefreshFinishExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, VoidPipelineOutput.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public VoidPipelineOutput finish(ModelRefreshContext ctx) {
        return VoidPipelineOutput.INSTANCE;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelRefreshContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
