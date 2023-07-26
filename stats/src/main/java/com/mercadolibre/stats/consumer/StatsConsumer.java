package com.mercadolibre.stats.consumer;

import com.mercadolibre.stats.repository.CountryRepository;
import com.mercadolibre.stats.repository.RequestedCountryRepository;
import com.mercadolibre.stats.repository.entity.RequestedCountryEntity;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class StatsConsumer {
	private final RequestedCountryRepository requestedCountryRepository;
	private final CountryRepository countryRepository;

	public StatsConsumer(
		RequestedCountryRepository requestedCountryRepository,
		CountryRepository countryRepository
	) {
		this.requestedCountryRepository = requestedCountryRepository;
		this.countryRepository = countryRepository;
	}

	@Incoming("country-stats")
	public CompletionStage<Void> consume(Message<StatsMessage> msg) {
		return
			Uni.createFrom().item(msg.getPayload())
				.invoke(stats -> Log.infof("Received stats message: %s", stats))
				.flatMap(stats ->
					Uni.combine().all().unis(
						Uni.createFrom().item(stats),
						this.countryRepository.findByName(stats.country())
					).asTuple()
				)
				.flatMap(tuple ->
					this.requestedCountryRepository.save(new RequestedCountryEntity(null, tuple.getItem2(), tuple.getItem1().distanceToBuenosAires()))
				)
				.invoke(result -> Log.infof("Persisted: %s", result))
				.subscribeAsCompletionStage()
				.thenAccept(it -> msg.ack());
	}
}
