

package org.datasphere.mdm.data.convert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.exception.DataExceptionIds;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.po.storage.DataNodePO;
import org.datasphere.mdm.data.type.storage.DataCluster;
import org.datasphere.mdm.data.type.storage.DataNode;
import org.datasphere.mdm.data.type.storage.DataShard;
import org.datasphere.mdm.system.dao.PoolSetting;
import org.datasphere.mdm.system.exception.PlatformFailureException;

/**
 * Converts serialized cluster metadata to middle tier form and vice versa.
 *
 * @author maria.chistyakova
 * @since 28.10.2019
 */
public class DataClusterConverter {

    private DataClusterConverter() {
        super();
    }

    public static DataClusterPO of(DataCluster cluster) {

        if (Objects.isNull(cluster)) {
            return null;
        }

        DataClusterPO result = new DataClusterPO();
        result.setDistributionFactor(cluster.getDistributionFactor());
        result.setHasData(cluster.hasData());
        result.setId(cluster.getId());
        result.setInitialized(cluster.isInitialized());
        result.setName(cluster.getName());
        result.setNumberOfShards(ArrayUtils.isEmpty(cluster.getShards()) ? 0 : cluster.getShards().length);
        result.setVersion(cluster.getVersion());

        List<DataNodePO> nodes = Collections.emptyList();
        if (ArrayUtils.isNotEmpty(cluster.getNodes())) {

            nodes = new ArrayList<>();
            for (DataNode node : cluster.getNodes()) {

                DataNodePO n = of(node);
                if (Objects.nonNull(n)) {
                    nodes.add(n);
                }
            }
        }

        result.setNodes(nodes);
        return result;
    }

    public static DataNodePO of(DataNode node) {

        if (Objects.isNull(node)) {
            return null;
        }

        DataNodePO result = new DataNodePO();
        result.setId(node.getNumber());
        result.setName(node.getName());
        result.setHost(node.getHost());
        result.setPort(node.getPort());
        result.setDatabase(node.getDatabase());
        result.setUser(node.getUser());
        result.setPassword(node.getPassword());
        result.setSettings(node.getSettings());
        result.setCreateDate(node.getCreateDate());
        result.setUpdateDate(node.getUpdateDate());

        return result;
    }

    public static DataCluster of(DataClusterPO po) {

        if (Objects.isNull(po)) {
            return null;
        }

        DataNode[] nodes = new DataNode[po.getNodes().size()];
        DataShard[] shards = new DataShard[po.getNumberOfShards()];

        // Nodes are zero based, ordered by id
        for (DataNodePO node : po.getNodes()) {

            DataNode converted = of(node);
            if (Objects.isNull(converted)) {
                continue;
            }

            nodes[node.getId()] = converted;
        }

        // Shards are zero based, simply assigned by modulo for now
        for (int i = 0; i < po.getNumberOfShards(); i++) {
            shards[i] = DataShard.builder()
                    .number(i)
                    .primary(nodes[(i + 1) % nodes.length])
                    .build();
        }

        return DataCluster.builder()
                .id(po.getId())
                .nodes(nodes)
                .shards(shards)
                .distributionFactor(po.getDistributionFactor())
                .name(po.getName())
                .hasData(po.hasData())
                .initialized(po.isInitialized())
                .version(po.getVersion())
                .build();
    }

    public static DataNode of(DataNodePO po) {

        if (Objects.isNull(po)) {
            return null;
        }

        return DataNode.builder()
                .number(po.getId())
                .name(po.getName())
                .host(po.getHost())
                .port(po.getPort())
                .database(po.getDatabase())
                .user(po.getUser())
                .password(po.getPassword())
                .settings(po.getSettings())
                .createDate(po.getCreateDate())
                .updateDate(po.getUpdateDate())
                .build();
    }

    public static DataClusterPO of(Integer shardNumber, String[] nodeIds) {

        if (ArrayUtils.isEmpty(nodeIds) || shardNumber <= 0) {
            throw new PlatformFailureException("Invalid storage initialization spec.",
                    DataExceptionIds.EX_DATA_STORAGE_INIT_FAILED);
        }

        DataClusterPO po = new DataClusterPO();
        List<DataNodePO> nodes = new ArrayList<>();
        for (String spec : nodeIds) {
            nodes.add(of(spec));
        }

        po.setName("Default Unidata storage cluster.");
        po.setNodes(nodes);
        po.setNumberOfShards(shardNumber);
        po.setDistributionFactor(shardNumber / nodes.size());
        po.setHasData(false);
        po.setInitialized(false);

        return po;
    }

    // This all is temporary stuff
    // Will be done via metadata
    public static DataNodePO of(String spec) {

        String[] parts = StringUtils.split(spec, ':');
        if (parts == null || parts.length < 4) {
            throw new PlatformFailureException("Node line supplied in wrong format '{}'.",
                    DataExceptionIds.EX_DATA_STORAGE_INIT_NODE_FORMAT, spec);
        }

        // 1. Node number
        int number = Integer.parseInt(parts[0]);

        // 2. Node name
        String name = parts[1];
        if (!StringUtils.contains(parts[2], "@")) {
            throw new PlatformFailureException("Supplied Node line contains no credentials '{}'.",
                    DataExceptionIds.EX_DATA_STORAGE_INIT_NODE_NO_CREDENTIALS, spec);
        }

        // 3. DB user.
        String user = StringUtils.substringBefore(parts[2], "@");
        // 4. DB password
        String password = StringUtils.substringAfter(parts[2], "@");
        // 5. DB password
        String database = "unidata";
        // 6. DB host
        String host = null;
        if (StringUtils.contains(parts[3], "@")) {
            database = StringUtils.substringBefore(parts[3], "@");
            host = StringUtils.substringAfter(parts[3], "@");
        } else {
            host = parts[3];
        }

        // 7. DB Port
        int port = 5432;
        if (parts.length > 4) {
            port = Integer.parseInt(parts[4]);
        }

        Map<PoolSetting, String> settings = new EnumMap<>(PoolSetting.class);
        for (PoolSetting s : PoolSetting.values()) {
            settings.put(s, s.getDefaultValue());
        }

        DataNodePO po = new DataNodePO();
        po.setId(number);
        po.setName(name);
        po.setHost(host);
        po.setPort(port);
        po.setDatabase(database);
        po.setUser(user);
        po.setPassword(password);
        po.setSettings(settings);

        return po;
    }
}
