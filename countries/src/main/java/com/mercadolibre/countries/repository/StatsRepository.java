package com.mercadolibre.countries.repository;

import com.mercadolibre.countries.exception.NotFoundException;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;

@ApplicationScoped
public class StatsRepository implements PanacheRepository<StatsEntity> {
	@WithSession
	public Uni<StatsEntity> get() {
		return this.findAll().singleResult()
			.onFailure(ex -> ex instanceof NoResultException || ex instanceof NonUniqueResultException)
			.transform(ex -> new NotFoundException("Stats not found", ex));
	}
}
