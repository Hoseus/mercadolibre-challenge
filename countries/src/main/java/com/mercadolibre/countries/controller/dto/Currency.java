package com.mercadolibre.countries.controller.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;

@RegisterForReflection
public record Currency(
	String name,
	BigDecimal usdConversion
) {
}
