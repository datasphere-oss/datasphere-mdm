
package org.datasphere.mdm.core.context;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

/**
 * @author Mikhail Mikhailov on Jun 18, 2021
 */
public class JobDescriptorsGetContext {
    /**
     * Fetch all descriptors.
     */
    private final boolean all;
    /**
     * The job names to select fescriptors for.
     */
    private final Set<String> names;
    /**
     * Constructor.
     */
    private JobDescriptorsGetContext(JobDescriptorsGetContextBuilder b) {
        super();
        this.all = b.all;
        this.names = Objects.isNull(b.names) ? Collections.emptySet() : b.names;
    }
    /**
     * @return the all
     */
    public boolean isAll() {
        return all;
    }
    /**
     * @return the names
     */
    public Set<String> getNames() {
        return names;
    }
    /**
     * @return builder
     */
    public static JobDescriptorsGetContextBuilder builder() {
        return new JobDescriptorsGetContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jun 18, 2021
     * Builder class.
     */
    public static class JobDescriptorsGetContextBuilder {
        /**
         * Fetch all descriptors.
         */
        private boolean all;
        /**
         * The job names to select fescriptors for.
         */
        private Set<String> names;
        /**
         * Constructor.
         */
        private JobDescriptorsGetContextBuilder() {
            super();
        }
        /**
         * Sets fech all mark.
         * @param value the mark
         * @return self
         */
        public JobDescriptorsGetContextBuilder all(boolean value) {
            this.all = value;
            return this;
        }
        /**
         * Sets fech all mark.
         * @param value the mark
         * @return self
         */
        public JobDescriptorsGetContextBuilder name(String name) {
            if (Objects.nonNull(name)) {
                if (names == null) {
                    names = new HashSet<>();
                }

                names.add(name);
            }
            return this;
        }
        /**
         * Sets fech all mark.
         * @param value the mark
         * @return self
         */
        public JobDescriptorsGetContextBuilder names(Collection<String> names) {
            if (CollectionUtils.isNotEmpty(names)) {
                for (String name : names) {
                    name(name);
                }
            }
            return this;
        }
        /**
         * Builder method.
         * @return context
         */
        public JobDescriptorsGetContext build() {
            return new JobDescriptorsGetContext(this);
        }
    }
}
