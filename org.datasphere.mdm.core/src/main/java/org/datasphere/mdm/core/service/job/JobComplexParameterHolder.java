

package org.datasphere.mdm.core.service.job;

import javax.annotation.Nonnull;

public interface JobComplexParameterHolder {
    /**
     * Put complex parameter to container.
     * @param key - key
     * @param parameter -  parameter
     */
    void putComplexParameter(@Nonnull String key, @Nonnull Object parameter);
    /**
     * Gets a parameter by key.
     * @param key - key
     * @param <T> -
     * @return complex parameter
     */
    <T> T getComplexParameter(@Nonnull String key);
    /**
     * Clean complex parameter
     * @param key -  key
     */
    void removeComplexParameter(@Nonnull String key);
    /**
     * Combination of methods getComplexParameter() and removeComplexParameter()
     * @param complexParameterKey
     * @param <T>
     * @return
     */
    <T> T getComplexParameterAndRemove(@Nonnull String complexParameterKey);
}
