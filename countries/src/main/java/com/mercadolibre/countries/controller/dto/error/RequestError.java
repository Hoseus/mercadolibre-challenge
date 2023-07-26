package com.mercadolibre.countries.controller.dto.error;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Value;

@RegisterForReflection
@Value
public class RequestError {
	private final String description;

	public RequestError(String description) {
		this.description = description;
	}
}
