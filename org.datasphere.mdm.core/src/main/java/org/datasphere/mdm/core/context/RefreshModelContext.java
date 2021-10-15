

package org.datasphere.mdm.core.context;

import org.datasphere.mdm.core.service.segments.ModelRefreshStartExecutor;

/**
 * @author maria.chistyakova
 * @since  18.12.2019
 * Refresh model context - reload a model by type/instance IDs.
 */
public class RefreshModelContext extends AbstractModelRefreshContext {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = 2456877118482094107L;
    /**
     * Constructor.
     *
     * @param b
     */
    public RefreshModelContext(RefreshModelContextBuilder b) {
        super(b);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return ModelRefreshStartExecutor.SEGMENT_ID;
    }
    /**
     * @return builder
     */
    public static RefreshModelContextBuilder builder() {
        return new RefreshModelContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Oct 26, 2020
     * Publish model context -  - reload a model by type/instance IDs builder.
     */
    public static class RefreshModelContextBuilder extends AbstractModelRefreshContextBuilder<RefreshModelContextBuilder> {
        /**
         * {@inheritDoc}
         */
        @Override
        public RefreshModelContext build() {
            return new RefreshModelContext(this);
        }
    }
}
