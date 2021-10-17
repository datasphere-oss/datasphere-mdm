

package org.datasphere.mdm.core.type.monitoring.hazelcast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hazelcast.cluster.Cluster;
import com.hazelcast.core.HazelcastInstance;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

public class HazelcastClusterInfoExports extends Collector {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastClusterInfoExports(final HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public List<MetricFamilySamples> collect() {
        final List<MetricFamilySamples> metricFamilySamples = new ArrayList<>();

        final Cluster cluster = hazelcastInstance.getCluster();

        final GaugeMetricFamily hazelcastClusterInfo = new GaugeMetricFamily(
                "hazelcast_cluster_state",
                "Hazelcast cluster state",
                Arrays.asList("state", "version", "time")
        );

        hazelcastClusterInfo.addMetric(
                Arrays.asList(
                        cluster.getClusterState().name(),
                        cluster.getClusterVersion().toString(),
                        String.valueOf(cluster.getClusterTime())
                ),
                1L
        );

        metricFamilySamples.add(hazelcastClusterInfo);


        final GaugeMetricFamily hazelcastMembersInfo = new GaugeMetricFamily(
                "hazelcast_members_info",
                "Hazelcast members info",
                Arrays.asList("uuid", "address", "version")
        );

        cluster.getMembers().forEach(m ->
                hazelcastMembersInfo.addMetric(
                        Arrays.asList(m.getUuid().toString(), m.getAddress().toString(), m.getVersion().toString()),
                        1L
                )
        );

        metricFamilySamples.add(hazelcastMembersInfo);

        return metricFamilySamples;
    }
}
