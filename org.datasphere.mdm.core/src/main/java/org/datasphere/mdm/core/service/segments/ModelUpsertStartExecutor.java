

package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelChangeContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Start;
import org.datasphere.mdm.system.type.pipeline.defaults.VoidPipelineOutput;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelUpsertStartExecutor.SEGMENT_ID)
public class ModelUpsertStartExecutor extends Start<ModelChangeContext, VoidPipelineOutput> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_UPSERT_START]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.upsert.start.description";
    /**
     * Constructor.
     */
    public ModelUpsertStartExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, ModelChangeContext.class, VoidPipelineOutput.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(ModelChangeContext ctx) {
        // NOOP. Start does nothing here.
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String subject(ModelChangeContext ctx) {
        // No subject for this type of pipelines
        // This may be storage id in the future
        return null;
    }
}
