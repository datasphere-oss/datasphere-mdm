

package org.datasphere.mdm.core.type.monitoring.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

// TODO @Modules
@Service
public class HazelcastMetricsExports {

//    private final HazelcastStateExporters hazelcastStateExporters;

    public HazelcastMetricsExports(final HazelcastInstance hazelcastInstance) {
//        new HazelcastInfoExports(hazelcastInstance).register();
//        new HazelcastClusterInfoExports(hazelcastInstance).register();
//        hazelcastStateExporters = new HazelcastStateExporters(hazelcastInstance);
        //new HazelcastInternalsExporters(hazelcastInstance);
    }

//    @PreDestroy
//    public void shutdown() {
//        hazelcastStateExporters.shutdown();
//    }
}
