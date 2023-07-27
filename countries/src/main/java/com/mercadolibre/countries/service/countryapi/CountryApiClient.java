package com.mercadolibre.countries.service.countryapi;

import com.mercadolibre.countries.exception.NotFoundException;
import com.mercadolibre.countries.exception.UnexpectedException;
import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import io.quarkus.rest.client.reactive.ClientQueryParam;
import io.quarkus.rest.client.reactive.ClientQueryParams;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "country-api")
@ClientQueryParams(value = {
	@ClientQueryParam(name = "apikey", value = "${countries.country-api.api-key}"),
})
public interface CountryApiClient {
	@GET
	@Path("/all")
	@CacheResult(cacheName = "country-api-cache")
	Uni<CountryApiDto> getAll();

	@ClientExceptionMapper
	static RuntimeException toException(Response response) {
		String body = response.readEntity(String.class);
		Log.errorf("Error. Failed response from Country Api. Response body: %s", body);

		if (response.getStatus() == 404) {
			return new NotFoundException("Country not found", null);
		}

		return new UnexpectedException();
	}
}
