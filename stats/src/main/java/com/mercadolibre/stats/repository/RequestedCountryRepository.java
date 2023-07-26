package com.mercadolibre.stats.repository;

import com.mercadolibre.stats.repository.entity.RequestedCountryEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RequestedCountryRepository implements PanacheRepository<RequestedCountryEntity> {
	@WithTransaction
	public Uni<RequestedCountryEntity> save(RequestedCountryEntity requestedCountry) {
		return this.persist(requestedCountry);
	}
}
