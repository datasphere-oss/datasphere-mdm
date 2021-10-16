

package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelRemoveContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Start;
import org.datasphere.mdm.system.type.pipeline.defaults.VoidPipelineOutput;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelRemoveStartExecutor.SEGMENT_ID)
public class ModelRemoveStartExecutor extends Start<ModelRemoveContext, VoidPipelineOutput> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_REMOVE_START]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.remove.start.description";
    /**
     * Constructor.
     */
    public ModelRemoveStartExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, ModelRemoveContext.class, VoidPipelineOutput.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(ModelRemoveContext ctx) {
        // NOOP. Start does nothing here.
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String subject(ModelRemoveContext ctx) {
        // No subject for this type of pipelines
        // This may be storage id in the future
        return null;
    }
}
