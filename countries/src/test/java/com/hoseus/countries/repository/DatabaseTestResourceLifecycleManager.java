package com.hoseus.countries.repository;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

import java.util.Map;

public class DatabaseTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

	@Override
	public Map<String, String> start() {
		return InMemoryConnector.switchOutgoingChannelsToInMemory("country-stats");
	}

	@Override
	public void stop() {
		InMemoryConnector.clear();
	}
}
