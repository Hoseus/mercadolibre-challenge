package com.mercadolibre.countries.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class DistanceUtils {
	private static final double EARTH_RADIUS_KM = 6371.01;
	private static final double BUENOS_AIRES_LATITUDE = -34.61315;
	private static final double BUENOS_AIRES_LONGITUDE = -58.37723;

	private DistanceUtils() {
	}

	public static double approximatedDistance(double lat1, double lon1, double lat2, double lon2) {
		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);

		double result = EARTH_RADIUS_KM * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public static double approximatedDistanceToBuenosAires(double latitude, double longitude) {
		return approximatedDistance(latitude, longitude, BUENOS_AIRES_LATITUDE, BUENOS_AIRES_LONGITUDE);
	}
}
