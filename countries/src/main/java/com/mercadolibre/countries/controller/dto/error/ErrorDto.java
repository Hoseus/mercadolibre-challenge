package com.mercadolibre.countries.controller.dto.error;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@RegisterForReflection
@Getter
@ToString
@EqualsAndHashCode
public abstract class ErrorDto {
	private final String code;

	public ErrorDto(String code) {
		this.code = code;
	}
}
