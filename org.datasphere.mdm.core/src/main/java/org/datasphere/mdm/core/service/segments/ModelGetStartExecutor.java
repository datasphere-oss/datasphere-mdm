
package org.datasphere.mdm.core.service.segments;

import org.datasphere.mdm.core.context.ModelGetContext;
import org.datasphere.mdm.core.dto.ModelGetResult;
import org.datasphere.mdm.core.module.CoreModule;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.type.pipeline.Start;
/**
 * @author Mikhail Mikhailov on Nov 28, 2019
 */
@Component(ModelGetStartExecutor.SEGMENT_ID)
public class ModelGetStartExecutor extends Start<ModelGetContext, ModelGetResult> {
    /**
     * This segment ID.
     */
    public static final String SEGMENT_ID = CoreModule.MODULE_ID + "[MODEL_GET_START]";
    /**
     * Localized message code.
     */
    public static final String SEGMENT_DESCRIPTION = CoreModule.MODULE_ID + ".model.get.start.description";
    /**
     * Constructor.
     */
    public ModelGetStartExecutor() {
        super(SEGMENT_ID, SEGMENT_DESCRIPTION, ModelGetContext.class, ModelGetResult.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(ModelGetContext ctx) {
        // NOOP. Start does nothing here.
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String subject(ModelGetContext ctx) {
        // No subject for this type of pipelines
        // This may be storage id in the future
        return null;
    }
}
