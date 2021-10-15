
package org.datasphere.mdm.core.context;

/**
 * @author Mikhail Mikhailov on May 24, 2021
 */
public class DataImportTemplateContext extends AbstractImportContext {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = 5690466419767540260L;
    /**
     * Constructor.
     * @param b
     */
    private DataImportTemplateContext(DataImportTemplateContextBuilder b) {
        super(b);
    }
    /**
     * Builder.
     * @return builder
     */
    public static DataImportTemplateContextBuilder builder() {
        return new DataImportTemplateContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on May 24, 2021
     */
    public static class DataImportTemplateContextBuilder extends AbstractImportContextBuilder<DataImportTemplateContextBuilder> {
        /**
         * Constructor.
         */
        private DataImportTemplateContextBuilder() {
            super();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public DataImportTemplateContext build() {
            return new DataImportTemplateContext(this);
        }
    }
}
