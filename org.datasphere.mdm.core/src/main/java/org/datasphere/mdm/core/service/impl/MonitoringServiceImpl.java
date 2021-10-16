

package org.datasphere.mdm.core.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.prometheus.client.Counter;

import org.datasphere.mdm.core.service.MonitoringService;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {
	private static final Map<String, Counter> COUNTERS = new ConcurrentHashMap<>();

	@Override
	public void registerCounter(String name, String description) {
		if(COUNTERS.containsKey(name)) {
			return;
		}
		Counter counter = Counter.build().name(name).help(description).create().register();
		COUNTERS.put(name, counter);

	}

	@Override
	public void incrementСounter(String name) {
		COUNTERS.get(name).inc();

	}

	@Override
	public void incrementCounter(String name, long value) {
		COUNTERS.get(name).inc(value);

	}

}
