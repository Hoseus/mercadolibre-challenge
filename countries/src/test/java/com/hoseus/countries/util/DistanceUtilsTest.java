package com.hoseus.countries.util;

import com.mercadolibre.countries.utils.DistanceUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DistanceUtilsTest {
	@Test
	public void approximated_distance_zero_test() {
		double result = DistanceUtils.approximatedDistance(0, 0, 0, 0);
		Assertions.assertEquals(0, result);
	}

	@ParameterizedTest
	@CsvSource(value = {
		"1,1,1,1",
		"-1,-1,-1,-1",
		"1,-1,1,-1",
		"4.32892,4.32892,4.32892,4.32892"
	})
	public void approximated_distance_same_lat_lon_test(double lat1, double lon1, double lat2, double lon2) {
		double result = DistanceUtils.approximatedDistance(lat1, lon1, lat2, lon2);
		Assertions.assertEquals(0, result);
	}

	@ParameterizedTest
	@CsvSource(value = {
		"1,1,2,2,157.23",
		"1,-1,4,-3,400.79",
		"4.32892,15.07136,-8.49866,-22.34099,4386.78"
	})
	public void approximated_distance_test(double lat1, double lon1, double lat2, double lon2, double expectedResult) {
		double result = DistanceUtils.approximatedDistance(lat1, lon1, lat2, lon2);
		Assertions.assertEquals(expectedResult, result);
	}

	@Test
	public void approximated_distance_to_buenos_aires_zero_test() {
		double result = DistanceUtils.approximatedDistanceToBuenosAires(0, 0);
		Assertions.assertEquals(7164.95, result);
	}

	@Test
	public void approximated_distance_to_buenos_aires_same_lat_lon_test() {
		double result = DistanceUtils.approximatedDistanceToBuenosAires(-34.61315, -58.37723);
		Assertions.assertEquals(0, result);
	}

	@ParameterizedTest
	@CsvSource(value = {
		"1,1,7321.35",
		"4,-3,7197.94",
		"4.32892,15.07136,8783.76"
	})
	public void approximated_distance_to_buenos_aires_test(double lat, double lon, double expectedResult) {
		double result = DistanceUtils.approximatedDistanceToBuenosAires(lat, lon);
		Assertions.assertEquals(expectedResult, result);
	}
}
