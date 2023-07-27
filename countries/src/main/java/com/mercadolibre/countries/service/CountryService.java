package com.mercadolibre.countries.service;

import com.mercadolibre.countries.controller.dto.CountryDto;
import com.mercadolibre.countries.controller.dto.Currency;
import com.mercadolibre.countries.producer.StatsMessage;
import com.mercadolibre.countries.producer.StatsProducer;
import com.mercadolibre.countries.service.countryapi.CountryApiService;
import com.mercadolibre.countries.service.countryapi.CountryData;
import com.mercadolibre.countries.service.exchangerateapi.ExchangeRateApiClient;
import com.mercadolibre.countries.service.exchangerateapi.ExchangeRateApiDto;
import com.mercadolibre.countries.service.ipapi.IpApiDto;
import com.mercadolibre.countries.service.ipapi.IpApiService;
import com.mercadolibre.countries.utils.DistanceUtils;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountryService {
	private static final BigDecimal ONE = BigDecimal.ONE.setScale(4, RoundingMode.HALF_UP);
	private final IpApiService ipApiService;
	private final CountryApiService countryApiService;
	private final ExchangeRateApiClient exchangeRateApiClient;
	private final StatsProducer statsProducer;

	public CountryService(
		IpApiService ipApiService,
		CountryApiService countryApiService,
		@RestClient ExchangeRateApiClient exchangeRateApiClient,
		StatsProducer statsProducer
	) {
		this.ipApiService = ipApiService;
		this.countryApiService = countryApiService;
		this.exchangeRateApiClient = exchangeRateApiClient;
		this.statsProducer = statsProducer;
	}

	public Uni<CountryDto> getByIp(String ip) {
		Uni<CountryData> countryDataUni =
			Uni.createFrom().item(ip)
				.flatMap(this::getIpCountryName)
				.flatMap(this::getCountryData);
		Uni<ExchangeRateApiDto> usdExchangeRatesUni = this.getExchangeRateData();

		return
			Uni.combine().all().unis(countryDataUni, usdExchangeRatesUni)
				.combinedWith((countryData, usdExchangeRates) -> {
					List<Double> countryLatLong = countryData.latLng().country();
					long distanceToBuenosAires = this.getApproximatedDistanceToBuenosAires(countryLatLong.get(0), countryLatLong.get(1));
					return this.buildResponse(countryData, usdExchangeRates, distanceToBuenosAires);
				})
				.call(countryDto -> {
					StatsMessage message = new StatsMessage(countryDto.name(), countryDto.distanceToBuenosAires());
					return this.statsProducer.send(message);
				})
				.onFailure()
				.invoke(ex -> Log.error("Error", ex));
	}

	private Uni<String> getIpCountryName(String ip) {
		return
			Uni.createFrom().item(ip)
				.invoke(it -> Log.infof("Start. Call IP Api Service. Ip: %s", it))
				.flatMap(this.ipApiService::get)
				.map(IpApiDto::countryName)
				.invoke(countryName -> Log.infof("End. Call IP Api Service. Country Name: %s", countryName));
	}

	private Uni<CountryData> getCountryData(String countryName) {
		return
			Uni.createFrom().item(countryName)
				.invoke(it -> Log.infof("Start. Call Country Api Service. Country Name: %s", it))
				.flatMap(this.countryApiService::getByName)
				.invoke(countryData -> Log.infof("End. Call Country Api Service. Response body: %s", countryData));
	}

	private Uni<ExchangeRateApiDto> getExchangeRateData() {
		return
			Uni.createFrom().voidItem()
				.invoke(() -> Log.info("Start. Call Exchange Rate Api Service"))
				.chain(() -> this.exchangeRateApiClient.getLatestExchangeRate("USD"))
				.invoke(exchangeRateApiDto -> Log.infof("End. Call Exchange Rate Api Service. Response body: %s", exchangeRateApiDto));
	}

	private Long getApproximatedDistanceToBuenosAires(double latitude, double longitude) {
		double distance = DistanceUtils.approximatedDistanceToBuenosAires(latitude, longitude);
		return Math.round(distance);
	}

	private CountryDto buildResponse(CountryData countryData, ExchangeRateApiDto usdExchangeRates, long distanceToBuenosAires) {
		Map<String, String> times = this.mapToResponseTimes(countryData.timezones());
		List<Currency> currencies = this.mapToResponseCurrencies(countryData.currencies().keySet().stream().toList(), usdExchangeRates);

		return new CountryDto(
			countryData.name(),
			countryData.alpha2Code(),
			countryData.alpha3Code(),
			countryData.languages() != null ? countryData.languages().values().stream().toList() : null,
			times,
			distanceToBuenosAires,
			currencies
		);
	}

	private List<Currency> mapToResponseCurrencies(List<String> currencies, ExchangeRateApiDto usdPrices) {
		return
			currencies
				.stream()
				.map(it -> {
					BigDecimal usdPriceForCurrency = usdPrices.getRate(it);
					if(usdPriceForCurrency == null) {
						return new Currency(it, null);
					}
					return new Currency(it, ONE.divide(usdPriceForCurrency, RoundingMode.HALF_UP));
				})
				.collect(Collectors.toList());
	}

	private Map<String, String> mapToResponseTimes(List<String> timezones) {
		Instant now = Instant.now();
		return
			timezones
			.stream()
			.collect(
				Collectors.toMap(
					it -> it,
					it -> LocalDateTime.ofInstant(now, ZoneId.of(it)).toString()
				)
			);
	}
}
