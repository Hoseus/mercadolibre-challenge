package com.mercadolibre.countries.controller.dto.error;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Value;

@RegisterForReflection
@Value
@EqualsAndHashCode(callSuper = true)
public class DefaultErrorDto extends ErrorDto {
	private final String message;

	public DefaultErrorDto(String code, String message) {
		super(code);
		this.message = message;
	}
}
