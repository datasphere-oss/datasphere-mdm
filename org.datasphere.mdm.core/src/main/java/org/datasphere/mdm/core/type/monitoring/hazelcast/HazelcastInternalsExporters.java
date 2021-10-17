

package org.datasphere.mdm.core.type.monitoring.hazelcast;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.impl.HazelcastInstanceImpl;
import com.hazelcast.instance.impl.HazelcastInstanceProxy;
import com.hazelcast.internal.metrics.LongGauge;
import com.hazelcast.internal.metrics.MetricsRegistry;

public class HazelcastInternalsExporters {

    private static final Logger logger = LoggerFactory.getLogger(HazelcastInternalsExporters.class);

    public HazelcastInternalsExporters(final HazelcastInstance hazelcastInstance) {
        final HazelcastInstanceProxy hazelcastInstanceProxy = (HazelcastInstanceProxy) hazelcastInstance;
        try {
            final Field original = HazelcastInstanceProxy.class.getDeclaredField("original");
            original.setAccessible(true);
            final HazelcastInstanceImpl hazelcastInstanceImpl = (HazelcastInstanceImpl) original.get(hazelcastInstance);
            final MetricsRegistry metricsRegistry = hazelcastInstanceImpl.node.nodeEngine.getMetricsRegistry();
            initGauges(metricsRegistry);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Error getting hazelcast internals", e);
        }
    }

    private void initGauges(final MetricsRegistry metricsRegistry) {
        metricsRegistry.getNames().forEach(m -> {
            final LongGauge longGauge = metricsRegistry.newLongGauge(m);
            System.out.println(m + " = " + longGauge.read());
        });
//        final LongGauge longGauge = metricsRegistry.newLongGauge("os.totalSwapSpaceSize");
//        System.out.println();longGauge.read()
    }
}
