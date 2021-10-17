package org.datasphere.mdm.data.context;

import java.util.Objects;

import org.datasphere.mdm.data.type.storage.DataCluster;
import org.datasphere.mdm.data.type.storage.DataNode;
import org.datasphere.mdm.system.context.DatabaseMigrationContext;

/**
 * @author Mikhail Mikhailov
 * Distributed migrations support.
 */
public class DistributedMigrationContext extends DatabaseMigrationContext {
    /**
     * The cluster we're migrating.
     */
    private final DataCluster cluster;
    /**
     * Particular node, we are running on.
     */
    private final DataNode node;
    /**
     * Constructor.
     */
    public DistributedMigrationContext(DistributedMigrationContextBuilder b) {
        super(b);
        this.cluster = b.cluster;
        this.node = b.node;
    }
    /**
     * The cluster we're migrating.
     * @return the cluster
     */
    public DataCluster getCluster() {
        return cluster;
    }
    /**
     * Particular node, we are running on.
     * @return the node
     */
    public DataNode getNode() {
        return node;
    }
    /**
     * Builder method.
     * @return distributed builder
     */
    public static DistributedMigrationContextBuilder distributed() {
        return new DistributedMigrationContextBuilder();
    }
    /**
     * Builder extension.
     * @author Mikhail Mikhailov on Apr 10, 2020
     */
    public static class DistributedMigrationContextBuilder extends AbstractDatabaseMigrationContextBuilder<DistributedMigrationContextBuilder> {
        /**
         * The cluster we're migrating.
         */
        private DataCluster cluster;
        /**
         * Particular node, we are running on.
         */
        private DataNode node;
        /**
         * Constructor.
         */
        private DistributedMigrationContextBuilder() {
            super();
        }
        /**
         * Sets cluster info.
         * @param cluster the cluster
         * @return self
         */
        public DistributedMigrationContextBuilder cluster(DataCluster cluster) {
            this.cluster = cluster;
            return self();
        }
        /**
         * Sets node info.
         * @param node the node
         * @return self
         */
        public DistributedMigrationContextBuilder node(DataNode node) {
            this.node = node;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public DistributedMigrationContext build() {
            Objects.requireNonNull(this.cluster, "Cluster info must not be null!");
            Objects.requireNonNull(this.node, "Node info must not be null!");
            return new DistributedMigrationContext(this);
        }

    }
}