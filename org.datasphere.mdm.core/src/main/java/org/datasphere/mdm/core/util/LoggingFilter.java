

package org.datasphere.mdm.core.util;

import org.apache.cxf.interceptor.Fault;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Custom logback filter.
 * It's needed to distinguish client logs from backend logs.
 * @author ilya.bykov
 *
 */
public class LoggingFilter extends AbstractMatcherFilter<ILoggingEvent> {
    /**
     * Logger name of rest client.
     */
    private static final String UNIDATA_REST_CLIENT = "UNIDATA_REST_CLIENT";

    /** {@inheritDoc} */
    @Override
    public FilterReply decide(ILoggingEvent event) {

        if (event.getThrowableProxy() instanceof ThrowableProxy
        && Fault.class.isInstance(((ThrowableProxy) event.getThrowableProxy()).getThrowable())) {
            return FilterReply.DENY;
        }

        if (event.getLoggerName().equals(UNIDATA_REST_CLIENT)) {
            return onMatch;
        } else {
            return onMismatch;
        }
    }

}
