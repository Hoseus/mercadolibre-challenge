package com.mercadolibre.countries.service.countryapi;

import com.mercadolibre.countries.exception.NotFoundException;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CountryApiService {
	private final CountryApiClient countryApiClient;

	public CountryApiService(
		@RestClient CountryApiClient countryApiClient
	) {
		this.countryApiClient = countryApiClient;
	}

	public Uni<CountryData> getByName(String countryName) {
		return
			this.getAll()
				.map(countryDataMap -> countryDataMap.getOrDefault(countryName, null))
				.flatMap(countryData -> {
					if(countryData == null) {
						return Uni.createFrom().failure(new NotFoundException("Country not found", null));
					}
					return Uni.createFrom().item(countryData);
				});
	}

	//@CacheResult(cacheName = "country-api-cache")
	public Uni<Map<String, CountryData>> getAll() {
		return this.countryApiClient.getAll()
				.invoke(it -> Log.infof("Response: %s", it))
				.map(countryApiDto -> {
					Map<String, CountryData> map = new HashMap<>();
					countryApiDto.countriesData().values().forEach(it -> map.put(it.name(), it));
					return map;
				});
	}
}
