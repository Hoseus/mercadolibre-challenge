package com.mercadolibre.stats.schedule;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaterializedViewRefresher {
	private final PgPool pgPool;

	public MaterializedViewRefresher(PgPool pgPool) {
		this.pgPool = pgPool;
	}

	@Scheduled(every="${stats.cron.time}")
	public Uni<Void> refreshCountryStats() {
		return this.pgPool.query("REFRESH MATERIALIZED VIEW country_stats").execute().replaceWithVoid();
	}
}
