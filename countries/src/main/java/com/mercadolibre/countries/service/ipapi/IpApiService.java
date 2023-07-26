package com.mercadolibre.countries.service.ipapi;

import com.mercadolibre.countries.controller.dto.error.RequestError;
import com.mercadolibre.countries.exception.BadRequestException;
import com.mercadolibre.countries.exception.NotFoundException;
import com.mercadolibre.countries.exception.UnexpectedException;
import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Collections;

@ApplicationScoped
public class IpApiService {
	private final IpApiClient ipApiClient;

	public IpApiService(
		@RestClient IpApiClient ipApiClient
	) {
		this.ipApiClient = ipApiClient;
	}

	@CacheResult(cacheName = "ip-api-cache")
	public Uni<IpApiDto> get(String ip) {
		return this.ipApiClient.get(ip)
			.invoke(this::validate);
	}

	private void validate(IpApiDto ipApiDto) {
		if(ipApiDto.success() != null && !ipApiDto.success()) {
			Log.errorf("Error. Failed response from IP Api. Response body: %s", ipApiDto);

			IpApiError error = ipApiDto.error();
			if(error.code() == 106) {
				Log.error("Error. Invalid IP address");
				throw new BadRequestException(
					Collections.singletonList(new RequestError("Invalid IP address")),
					null
				);
			}

			if (error.code() == 404) {
				throw new NotFoundException("Country not found", null);
			}

			throw new UnexpectedException();
		}

		if(ipApiDto.countryName() == null) {
			throw new NotFoundException("Country not found", null);
		}
	}
}
