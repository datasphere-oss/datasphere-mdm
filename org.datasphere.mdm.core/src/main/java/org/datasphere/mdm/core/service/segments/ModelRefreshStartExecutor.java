

package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelRefreshContext;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Start;
import org.datasphere.mdm.system.type.pipeline.defaults.VoidPipelineOutput;

/**
 * @author maria.chistyakova
 * @since  06.12.2019
 */
@Component(ModelRefreshStartExecutor.SEGMENT_ID)
public class ModelRefreshStartExecutor extends Start<ModelRefreshContext, VoidPipelineOutput> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_REFRESH_START]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.refresh.start.description";
    /**
     * Constructor.
     */
    public ModelRefreshStartExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, ModelRefreshContext.class, VoidPipelineOutput.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(ModelRefreshContext ctx) {
        // NOOP. Start does nothing here.
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String subject(ModelRefreshContext ctx) {
        // No subject for this type of pipelines
        // This may be storage id in the future
        return null;
    }
}
