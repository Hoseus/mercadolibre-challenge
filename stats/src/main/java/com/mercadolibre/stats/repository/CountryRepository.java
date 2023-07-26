package com.mercadolibre.stats.repository;

import com.mercadolibre.stats.repository.entity.CountryEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<CountryEntity> {
	@WithSession
	public Uni<CountryEntity> findByName(String name) {
		return this.find("name", name).firstResult();
	}
}
