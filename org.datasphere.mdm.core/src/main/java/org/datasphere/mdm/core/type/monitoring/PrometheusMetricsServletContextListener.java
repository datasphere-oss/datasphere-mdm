

package org.datasphere.mdm.core.type.monitoring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import io.prometheus.client.hotspot.DefaultExports;

public class PrometheusMetricsServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DefaultExports.initialize();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
