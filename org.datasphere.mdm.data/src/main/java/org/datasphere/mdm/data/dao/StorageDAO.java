

package org.datasphere.mdm.data.dao;

import java.util.List;

import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.po.storage.DataNodePO;

/**
 * @author Mikhail Mikhailov
 *
 */
public interface StorageDAO extends BaseStorageDAO {
    /**
     * Loads cluster metadata.
     * @return cluster info
     */
    DataClusterPO load();
    /**
     * Saves cluster info.
     * Current implementation resets/closes underlaying data sources.
     * Thus, this method <b>MUST NOT</b> be called often (in fact, it should be called once at cluster initialization).
     * This is subject to change after a full blown configurator is ready.
     * @param info cluster metadata
     */
    void save(DataClusterPO info);
    /**
     * Saves a collection of nodes.
     * @param nodes the nodes to save
     */
    void save(List<DataNodePO> nodes);
}
