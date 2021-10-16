

package org.datasphere.mdm.core.service.impl.job;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.service.job.JobComplexParameterHolder;
import org.springframework.stereotype.Service;

/**
 * Object responsible fot containing complex parameters for job execution.
 */
@Service
public class JobComplexParameterHolderImpl implements JobComplexParameterHolder {

    /**
     * Map of complex parameters
     */
    private Map<String, Object> complexParameters = new ConcurrentHashMap<>();

    @Override
    public void putComplexParameter(@Nonnull String key, @Nonnull Object parameter) {
        complexParameters.put(key, parameter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getComplexParameter(@Nonnull String key) {
        return (T) complexParameters.get(key);
    }

    @Override
    public void removeComplexParameter(@Nonnull String key) {
        complexParameters.remove(key);
    }

    @Override
    public <T> T getComplexParameterAndRemove(@Nonnull String complexParameterKey) {
        T result = getComplexParameter(complexParameterKey);
        removeComplexParameter(complexParameterKey);
        return result;
    }
}
