

package org.datasphere.mdm.core.service;

/**
 * Monitoring service.
 */
public interface MonitoringService {
	
	/**
	 * Register counter.
	 *
	 * @param name counter name
	 * @param description counter description
	 */
	void registerCounter(String name, String description);

	/**
	 * Increment counter by 1.
	 *
	 * @param name counter name.
	 */
	void incrementСounter(String name);

	/**
	 * Increment counter by value.
	 *
	 * @param name counter name.
	 * @param value value by which counter will be increased.
	 */
	void incrementCounter(String name, long value);

}
