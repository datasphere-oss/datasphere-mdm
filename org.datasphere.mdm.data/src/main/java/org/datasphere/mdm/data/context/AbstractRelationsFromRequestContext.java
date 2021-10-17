

package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * Abstract from side request context.
 */
public abstract class AbstractRelationsFromRequestContext<T extends RelationFromIdentityContext>
    extends AbstractRecordIdentityContext {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 8277274116336739520L;
    /**
     * The relations to upsert.
     */
    protected final transient Map<String, List<T>> relationsFrom;
    /**
     * Constructor.
     * @param b the builder
     */
    protected AbstractRelationsFromRequestContext(AbstractRelationsFromRequestContextBuilder<T, ?> b) {
        super(b);
        this.relationsFrom = b.relationsFrom;
        flags.set(DataContextFlags.FLAG_APPLY_TO_ALL, b.applyToAll);
    }
    /**
     * Gets the To side relations.
     * @return map of relations
     */
    public Map<String, List<T>> getRelationsFrom() {
        return relationsFrom == null ? Collections.emptyMap() : this.relationsFrom;
    }
    /**
     * Apply operation (delete/get/upsert) to all classifier records, found by record attributes (etalon id, external id, LSN etc.).
     */
    public boolean isApplyToAll() {
        return flags.get(DataContextFlags.FLAG_APPLY_TO_ALL);
    }
    /**
     * @author Mikhail Mikhailov
     *
     * @param <X> the concrete builder class
     */
    public abstract static class
        AbstractRelationsFromRequestContextBuilder<T extends RelationFromIdentityContext, X extends AbstractRelationsFromRequestContextBuilder<T, X>>
        extends AbstractRecordIdentityContextBuilder<X> {
        /**
         * The relations to upsert.
         */
        private Map<String, List<T>> relationsFrom;
        /**
         * Apply operation (delete/get/upsert) to all classifier records, found by record attributes (etalon id, external id, LSN etc.).
         */
        private boolean applyToAll;
        /**
         * Constructor.
         */
        protected AbstractRelationsFromRequestContextBuilder() {
            super();
        }
        /**
         * Constructor.
         * @param other
         */
        protected AbstractRelationsFromRequestContextBuilder(AbstractRelationsFromRequestContext<?> other) {
            super(other);
        }
        /**
         * Sets the apply to all flag.
         * @param applyToAll the flag
         * @return self
         */
        public X applyToAll(boolean applyToAll) {
            this.applyToAll = applyToAll;
            return self();
        }
        /**
         * @param relations the relations to set
         * @return self
         */
        public X relationsFrom(Map<String, List<T>> relations) {
            relations.forEach(this::relationsFrom);
            return self();
        }
        /**
         * @param relationName the name
         * @param relations the relations to set
         * @return self
         */
        public X relationsFrom(String relationName, List<T> relations) {
            relations.forEach(v -> relationFrom(relationName, v));
            return self();
        }
        /**
         * @param relationName the name
         * @param relationsFrom the relations to set
         * @return self
         */
        public X relationFrom(String relationName, T relation) {

            Objects.requireNonNull(relationName);
            if (Objects.isNull(this.relationsFrom)) {
                this.relationsFrom = new HashMap<>();
            }

            this.relationsFrom
                .computeIfAbsent(relationName, k -> new ArrayList<T>())
                .add(relation);

            return self();
        }
    }
}
