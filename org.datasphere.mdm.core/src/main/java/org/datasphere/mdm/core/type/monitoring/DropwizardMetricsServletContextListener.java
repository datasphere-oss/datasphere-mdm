

package org.datasphere.mdm.core.type.monitoring;


import io.dropwizard.metrics5.MetricRegistry;
import io.dropwizard.metrics5.jvm.ClassLoadingGaugeSet;
import io.dropwizard.metrics5.jvm.GarbageCollectorMetricSet;
import io.dropwizard.metrics5.jvm.MemoryUsageGaugeSet;
import io.dropwizard.metrics5.jvm.ThreadStatesGaugeSet;
import io.dropwizard.metrics5.servlets.MetricsServlet;

public class DropwizardMetricsServletContextListener extends MetricsServlet.ContextListener {

    private static final MetricRegistry METRICS_REGISTRY = new MetricRegistry();

    public DropwizardMetricsServletContextListener() {
        final MemoryUsageGaugeSet memoryUsageGaugeSet = new MemoryUsageGaugeSet();
        METRICS_REGISTRY.registerAll(memoryUsageGaugeSet);

        final GarbageCollectorMetricSet garbageCollectorMetricSet = new GarbageCollectorMetricSet();
        METRICS_REGISTRY.registerAll(garbageCollectorMetricSet);

//        final CpuTimeClock cpuTimeClock = new CpuTimeClock();
//        METRICS_REGISTRY.timer("cpu");

        final ThreadStatesGaugeSet threadStatesGaugeSet = new ThreadStatesGaugeSet();
        METRICS_REGISTRY.registerAll(threadStatesGaugeSet);

        final ClassLoadingGaugeSet classLoadingGaugeSet = new ClassLoadingGaugeSet();
        METRICS_REGISTRY.registerAll(classLoadingGaugeSet);
    }

    @Override
    protected MetricRegistry getMetricRegistry() {
        return METRICS_REGISTRY;
    }
}
