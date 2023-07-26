package com.hoseus.countries.producer;

import com.mercadolibre.countries.producer.StatsMessage;
import com.mercadolibre.countries.producer.StatsProducer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
public class StatsProducerTest {
	@Inject
	StatsProducer statsProducer;

	@Inject
	@Any
	InMemoryConnector connector;

	@Test
	void producer_test() {
		InMemorySink<StatsMessage> statsReceiver = this.connector.sink("country-stats");

		StatsMessage stats1 = new StatsMessage("Spain", 9994L);
		StatsMessage stats2 = new StatsMessage("Australia", 13239L);
		StatsMessage stats3 = new StatsMessage("France", 10812L);

		this.statsProducer.send(stats1).await().indefinitely();
		this.statsProducer.send(stats2).await().indefinitely();
		this.statsProducer.send(stats3).await().indefinitely();

		List<StatsMessage> receivedStats = statsReceiver.received().stream().map(Message::getPayload).toList();

		Assertions.assertEquals(stats1, receivedStats.get(0));
		Assertions.assertEquals(stats2, receivedStats.get(1));
		Assertions.assertEquals(stats3, receivedStats.get(2));
	}
}
