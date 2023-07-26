package com.mercadolibre.countries.controller.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record DistanceStat(
	String from,
	Long distance
) {
}
