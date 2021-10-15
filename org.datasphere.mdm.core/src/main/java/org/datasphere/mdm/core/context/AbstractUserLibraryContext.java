
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.system.context.StorageSpecificContext;

/**
 * @author Alexander Malyshev
 */
public class AbstractUserLibraryContext implements StorageSpecificContext {
    /**
     * The storage id.
     */
    private final String storageId;
    /**
     * File name.
     */
    private final String filename;
    /**
     * Constructor.
     * @param b the builder
     */
    protected AbstractUserLibraryContext(final AbstractUserLibraryContextBuilder<?> b) {
        super();
        this.storageId = b.storageId;
        this.filename = b.filename;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStorageId() {
        return storageId;
    }
    /**
     * Filename field to filter for.
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }
    /**
     * @author Mikhail Mikhailov on Sep 16, 2020
     * Builder class.
     */
    public abstract static class AbstractUserLibraryContextBuilder<X extends AbstractUserLibraryContextBuilder<X>> {
        /**
         * The storage id.
         */
        private String storageId;
        /**
         * File name.
         */
        private String filename;
        /**
         * Constructor.
         */
        protected AbstractUserLibraryContextBuilder() {
            super();
        }
        /**
         * The storageId to use.
         * @param storageId the storage id to use
         * @return self
         */
        public X storageId(String storageId) {
            this.storageId = storageId;
            return self();
        }
        /**
         * The filename.
         * @param filename the filename
         * @return self
         */
        public X filename(String filename) {
            this.filename = filename;
            return self();
        }
        /**
         * Self to X cast.
         * @return self
         */
        @SuppressWarnings("unchecked")
        protected X self() {
            return (X) this;
        }
        /**
         * {@inheritDoc}
         */
        public abstract AbstractUserLibraryContext build();
    }
}
