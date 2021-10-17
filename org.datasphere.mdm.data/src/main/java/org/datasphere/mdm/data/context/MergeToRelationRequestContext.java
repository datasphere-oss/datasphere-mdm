package org.datasphere.mdm.data.context;

import org.datasphere.mdm.data.service.segments.relations.merge.RelationMergeStartExecutor;
import org.datasphere.mdm.data.type.keys.RecordKeys;

/**
 * @author Mikhail Mikhailov on May 3, 2020
 */
public class MergeToRelationRequestContext extends AbstractRelationIdentityContext
        implements
            RelationMergeContext,
            RelationToIdentityContext {
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
    private MergeToRelationRequestContext(MergeToRelationRequestContextBuilder b) {
        super(b);
        this.master = b.master;
        this.masterKeys(b.masterKeys);
        this.toKeys(b.toKeys);
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
    public static MergeToRelationRequestContextBuilder builder() {
        return new MergeToRelationRequestContextBuilder();
    }
    /**
     * The builder.
     * @author Mikhail Mikhailov on May 3, 2020
     */
    public static class MergeToRelationRequestContextBuilder extends AbstractRelationIdentityContextBuilder<MergeToRelationRequestContextBuilder> {
        /**
         * Master context for simgle remap call.
         */
        private RecordIdentityContext master;
        /**
         * The master keys.
         */
        private RecordKeys masterKeys;
        /**
         * The to-side keys.
         */
        private RecordKeys toKeys;
        /**
         * Constructor.
         */
        private MergeToRelationRequestContextBuilder() {
            super();
        }
        /**
         * Sets the master context.
         * @param master the master context
         * @return self
         */
        public MergeToRelationRequestContextBuilder master(RecordIdentityContext master) {
            this.master = master;
            return self();
        }
        /**
         * Sets the master keys.
         * Relevant for a single rel merge only.
         * In all other cases happens automatically.
         * @param keys the master context
         * @return self
         */
        public MergeToRelationRequestContextBuilder master(RecordKeys keys) {
            this.masterKeys = keys;
            return self();
        }
        /**
         * Sets the to-side keys.
         * Relevant for a single rel merge only.
         * In all other cases happens automatically.
         * @param keys the to side keys
         * @return self
         */
        public MergeToRelationRequestContextBuilder toKeys(RecordKeys keys) {
            this.toKeys = keys;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public MergeToRelationRequestContext build() {
            return new MergeToRelationRequestContext(this);
        }
    }
}
