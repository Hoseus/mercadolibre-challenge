package com.mercadolibre.countries.service.exchangerateapi;

import com.mercadolibre.countries.exception.UnexpectedException;
import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "exchangerate-api")
public interface ExchangeRateApiClient {
	// Cache every 1 hour
	@GET
	@Path("/latest/{base}")
	@CacheResult(cacheName = "exchangerate-api-cache")
	Uni<ExchangeRateApiDto> getLatestExchangeRate(@PathParam("base") String baseCurrency);

	@ClientExceptionMapper
	static RuntimeException toException(Response response) {
		String body = response.readEntity(String.class);
		Log.errorf("Error. Failed response from Exchange Rate Api. Response body: %s", body);

		return new UnexpectedException();
	}
}
