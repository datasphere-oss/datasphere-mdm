

package org.datasphere.mdm.core.type.monitoring.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HazelcastInfoExports extends Collector {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastInfoExports(final HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public List<MetricFamilySamples> collect() {
        final List<MetricFamilySamples> metricFamilySamples = new ArrayList<>();

        final GaugeMetricFamily hazelcastInfo = new GaugeMetricFamily(
                "hazelcast_info",
                "Labeled Hazelcast info",
                Collections.singletonList("name")
        );

        hazelcastInfo.addMetric(
                Collections.singletonList(
                        hazelcastInstance.getName()
                ),
                1L
        );

        metricFamilySamples.add(hazelcastInfo);

        return metricFamilySamples;
    }
}
