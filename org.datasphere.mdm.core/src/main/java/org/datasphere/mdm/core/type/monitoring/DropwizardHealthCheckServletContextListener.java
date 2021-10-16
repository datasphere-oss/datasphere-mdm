

package org.datasphere.mdm.core.type.monitoring;

import io.dropwizard.metrics5.health.HealthCheckRegistry;
import io.dropwizard.metrics5.servlets.HealthCheckServlet;

public class DropwizardHealthCheckServletContextListener extends HealthCheckServlet.ContextListener {

    private static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry();

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return HEALTH_CHECK_REGISTRY;
    }
}
