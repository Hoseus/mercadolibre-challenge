package com.mercadolibre.stats.consumer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class StatsDeserializer extends ObjectMapperDeserializer<StatsMessage> {
	public StatsDeserializer() {
		super(StatsMessage.class);
	}
}
