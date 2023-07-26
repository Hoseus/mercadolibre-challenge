package com.mercadolibre.countries.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(name = "country_stats")
public class StatsEntity {
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "min_distance_country")
	private String minDistanceCountry;

	@Column(name = "min_distance_to_buenos_aires")
	private Long minDistanceToBuenosAires;

	@Column(name = "max_distance_country")
	private String maxDistanceCountry;

	@Column(name = "max_distance_to_buenos_aires")
	private Long maxDistanceToBuenosAires;

	@Column(name = "avg_distance_to_buenos_aires")
	private Long avgDistanceToBuenosAires;
}
