
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.system.context.StorageSpecificContext;

/**
 * @author Alexander Malyshev
 */
public class UserLibraryRemoveContext extends AbstractUserLibraryContext implements StorageSpecificContext {
    /**
     * Library's version
     */
    private final String version;
    /**
     * Constructor.
     * @param b the builder
     */
    private UserLibraryRemoveContext(final UserLibraryRemoveContextBuilder b) {
        super(b);
        this.version = b.version;
    }
    /**
     * Version field to filter for.
     * @return the version
     */
    public String getVersion() {
        return version;
    }
    /**
     * Builder method.
     * @return builder instance
     */
    public static UserLibraryRemoveContextBuilder builder() {
        return new UserLibraryRemoveContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Sep 16, 2020
     * Builder class.
     */
    public static class UserLibraryRemoveContextBuilder extends AbstractUserLibraryContextBuilder<UserLibraryRemoveContextBuilder> {
        /**
         * Library's version
         */
        private String version;
        /**
         * Constructor.
         */
        private UserLibraryRemoveContextBuilder() {
            super();
        }
        /**
         * The version.
         * @param version the version to filter for
         * @return self
         */
        public UserLibraryRemoveContextBuilder version(String version) {
            this.version = version;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public UserLibraryRemoveContext build() {
            return new UserLibraryRemoveContext(this);
        }
    }
}
