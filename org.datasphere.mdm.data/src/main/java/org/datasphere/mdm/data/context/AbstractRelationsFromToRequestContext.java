package org.datasphere.mdm.data.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mikhail Mikhailov on May 3, 2020
 */
public abstract class AbstractRelationsFromToRequestContext<F extends RelationFromIdentityContext, T extends RelationToIdentityContext>
    extends AbstractRelationsFromRequestContext<F> {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = 2495266553531572439L;
    /**
     * The relations to upsert.
     */
    protected final transient Map<String, List<T>> relationsTo;
    /**
     * Constructor.
     * @param b
     */
    protected AbstractRelationsFromToRequestContext(AbstractRelationsFromToRequestContextBuilder<F, T, ?> b) {
        super(b);
        this.relationsTo = b.relationsTo;
    }
    /**
     * Gets the To side relations.
     * @return map of relations
     */
    public Map<String, List<T>> getRelationsTo() {
        return relationsTo == null ? Collections.emptyMap() : this.relationsTo;
    }
    /**
     * Bi-directional container builder.
     * @author Mikhail Mikhailov on May 3, 2020
     */
    public abstract static class AbstractRelationsFromToRequestContextBuilder<F extends RelationFromIdentityContext, T extends RelationToIdentityContext, X extends AbstractRelationsFromToRequestContextBuilder<F, T, X>>
        extends AbstractRelationsFromRequestContextBuilder<F, X> {
        /**
         * The relations to upsert.
         */
        private Map<String, List<T>> relationsTo;
        /**
         * Constructor.
         */
        protected AbstractRelationsFromToRequestContextBuilder() {
            super();
        }
        /**
         * Constructor.
         * @param other
         */
        public AbstractRelationsFromToRequestContextBuilder(AbstractRelationsFromToRequestContext<?, ?> other) {
            super(other);
        }
        /**
         * @param relations the relations to set
         * @return self
         */
        public X relationsTo(Map<String, List<T>> relations) {
            relations.forEach(this::relationsTo);
            return self();
        }
        /**
         * @param relationName the name
         * @param relations the relations to set
         * @return self
         */
        public X relationsTo(String relationName, List<T> relations) {
            relations.forEach(v -> relationTo(relationName, v));
            return self();
        }
        /**
         * @param relationName the name
         * @param relationsFrom the relations to set
         * @return self
         */
        public X relationTo(String relationName, T relation) {

            Objects.requireNonNull(relationName);
            if (Objects.isNull(this.relationsTo)) {
                this.relationsTo = new HashMap<>();
            }

            this.relationsTo
                .computeIfAbsent(relationName, k -> new ArrayList<T>())
                .add(relation);

            return self();
        }
    }
}
