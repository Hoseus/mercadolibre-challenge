package com.mercadolibre.stats.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Entity
@Table(name = "requested_country")
public class RequestedCountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@ManyToOne
	private CountryEntity country;

	@Column(name = "distance_to_buenos_aires")
	private Long distanceToBuenosAires;
}
