package com.mercadolibre.countries.producer;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;

@ApplicationScoped
public class StatsProducer {
	private final MutinyEmitter<StatsMessage> statsEmitter;

	public StatsProducer(@Channel("country-stats") MutinyEmitter<StatsMessage> statsEmitter) {
		this.statsEmitter = statsEmitter;
	}

	public Uni<Void> send(StatsMessage statsMessage) {
		return this.statsEmitter.send(statsMessage);
	}
}
