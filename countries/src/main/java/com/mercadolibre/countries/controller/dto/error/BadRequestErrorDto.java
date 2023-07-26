package com.mercadolibre.countries.controller.dto.error;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@RegisterForReflection
@Value
@EqualsAndHashCode(callSuper = true)
public class BadRequestErrorDto extends ErrorDto {
	private final List<RequestError> errors;

	public BadRequestErrorDto(String code, List<RequestError> errors) {
		super(code);
		this.errors = errors;
	}
}
