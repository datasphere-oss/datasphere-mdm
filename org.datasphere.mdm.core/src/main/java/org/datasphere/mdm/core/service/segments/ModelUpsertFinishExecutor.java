

package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelChangeContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Finish;
import org.datasphere.mdm.system.type.pipeline.Start;
import org.datasphere.mdm.system.type.pipeline.defaults.VoidPipelineOutput;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelUpsertFinishExecutor.SEGMENT_ID)
public class ModelUpsertFinishExecutor extends Finish<ModelChangeContext, VoidPipelineOutput> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_UPSERT_FINISH]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.upsert.finish.description";
    /**
     * Constructor.
     */
    public ModelUpsertFinishExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, VoidPipelineOutput.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public VoidPipelineOutput finish(ModelChangeContext ctx) {
        return VoidPipelineOutput.INSTANCE;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Start<?, ?> start) {
        return ModelChangeContext.class.isAssignableFrom(start.getInputTypeClass());
    }
}
