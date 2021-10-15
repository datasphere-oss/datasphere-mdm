
package org.datasphere.mdm.core.context;

import java.io.InputStream;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Mikhail Mikhailov on May 13, 2021
 */
public class DataImportInputContext extends AbstractImportContext {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -5912038259015020136L;
    /**
     * Input stream supplier.
     */
    private final transient Supplier<InputStream> input;
    /**
     * The source system.
     */
    private final String sourceSystem;
    /**
     * The original file name.
     */
    private final String fileName;
    /**
     * Merge with previous version, if it does exist.
     */
    private final boolean mergeWithPrevious;
    /**
     * Constructor.
     * @param b
     */
    private DataImportInputContext(DataImportInputContextBuilder b) {
        super(b);
        this.sourceSystem = b.sourceSystem;
        this.fileName = b.fileName;
        this.mergeWithPrevious = b.mergeWithPrevious;
        this.input = Objects.nonNull(b.inputSupplier) ? b.inputSupplier : () -> b.inputStream;
    }
    /**
     * @return the inputStream
     */
    public Supplier<InputStream> getInput() {
        return input;
    }
    /**
     * @return the sourceSystem
     */
    public String getSourceSystem() {
        return sourceSystem;
    }
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @return the mergeWithPrevious
     */
    public boolean isMergeWithPrevious() {
        return mergeWithPrevious;
    }
    /**
     * Builder.
     * @return builder
     */
    public static DataImportInputContextBuilder builder() {
        return new DataImportInputContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on May 13, 2021
     */
    public static class DataImportInputContextBuilder extends AbstractImportContextBuilder<DataImportInputContextBuilder> {
        /**
         * Input stream supplier.
         */
        private Supplier<InputStream> inputSupplier;
        /**
         * Input stream.
         */
        private InputStream inputStream;
        /**
         * The source system.
         */
        private String sourceSystem;
        /**
         * The original file name.
         */
        private String fileName;
        /**
         * Merge with previous version, if it does exist.
         */
        private boolean mergeWithPrevious;
        /**
         * Constructor.
         */
        private DataImportInputContextBuilder() {
            super();
        }
        /**
         * Sets the input stream.
         * @param inputStream the input stream
         * @return self
         */
        public DataImportInputContextBuilder input(InputStream inputStream) {
            this.inputStream = inputStream;
            return self();
        }
        /**
         * Sets an input stream supplier,
         * which may do some recovery in case of failure or run other activities,
         * related to underlying input.
         * @param inputStream the input stream
         * @return self
         */
        public DataImportInputContextBuilder input(Supplier<InputStream> inputStream) {
            this.inputSupplier = inputStream;
            return self();
        }
        /**
         * @param sourceSystem the sourceSystem to set
         */
        public DataImportInputContextBuilder sourceSystem(String sourceSystem) {
            this.sourceSystem = sourceSystem;
            return self();
        }
        /**
         * @param fileName the fileName to set
         */
        public DataImportInputContextBuilder fileName(String fileName) {
            this.fileName = fileName;
            return self();
        }
        /**
         * Sets the merge with previous flag state.
         * @param mergeWithPrevious the mergeWithPrevious flag
         * @return self
         */
        public DataImportInputContextBuilder mergeWithPrevious(boolean mergeWithPrevious) {
            this.mergeWithPrevious = mergeWithPrevious;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public DataImportInputContext build() {
            return new DataImportInputContext(this);
        }
    }
}
