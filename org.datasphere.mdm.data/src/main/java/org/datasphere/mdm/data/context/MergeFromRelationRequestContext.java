package org.datasphere.mdm.data.context;

import org.datasphere.mdm.data.service.segments.relations.merge.RelationMergeStartExecutor;
import org.datasphere.mdm.data.type.keys.RecordKeys;

/**
 * @author Mikhail Mikhailov on May 3, 2020
 */
public class MergeFromRelationRequestContext extends AbstractRelationIdentityContext
        implements
            RelationMergeContext,
            RelationFromIdentityContext {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -5614833553851688251L;
    /**
     * Master context for simgle remap call.
     */
    private final RecordIdentityContext master;
    /**
     * Constructor.
     * @param b the builder
     */
    private MergeFromRelationRequestContext(MergeFromRelationRequestContextBuilder b) {
        super(b);
        this.master = b.master;
        this.masterKeys(b.masterKeys);
        this.fromKeys(b.fromKeys);
    }
    /**
     * @return the master
     */
    public RecordIdentityContext getMaster() {
        return master;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStartTypeId() {
        return RelationMergeStartExecutor.SEGMENT_ID;
    }
    /**
     * The builder.
     * @return builder
     */
    public static MergeFromRelationRequestContextBuilder builder() {
        return new MergeFromRelationRequestContextBuilder();
    }
    /**
     * The builder.
     * @author Mikhail Mikhailov on May 3, 2020
     */
    public static class MergeFromRelationRequestContextBuilder extends AbstractRelationIdentityContextBuilder<MergeFromRelationRequestContextBuilder> {
        /**
         * Master context for simgle remap call.
         */
        private RecordIdentityContext master;
        /**
         * The new master keys.
         */
        private RecordKeys masterKeys;
        /**
         * The from side keys.
         */
        private RecordKeys fromKeys;
        /**
         * Constructor.
         */
        private MergeFromRelationRequestContextBuilder() {
            super();
        }
        /**
         * Sets the master context.
         * @param master the master context
         * @return self
         */
        public MergeFromRelationRequestContextBuilder master(RecordIdentityContext master) {
            this.master = master;
            return self();
        }
        /**
         * Sets the master keys.
         * Relevant for a single rel merge only.
         * In all other cases happens automatically.
         * @param keys the new master keys
         * @return self
         */
        public MergeFromRelationRequestContextBuilder master(RecordKeys keys) {
            this.masterKeys = keys;
            return self();
        }
        /**
         * Sets the from keys.
         * Relevant for a single rel merge only.
         * In all other cases happens automatically.
         * @param keys of the from side.
         * @return self
         */
        public MergeFromRelationRequestContextBuilder fromKeys(RecordKeys keys) {
            this.fromKeys = keys;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public MergeFromRelationRequestContext build() {
            return new MergeFromRelationRequestContext(this);
        }
    }
}
