package com.mercadolibre.countries.exception;

import com.mercadolibre.countries.controller.dto.error.RequestError;
import lombok.Getter;

import java.util.List;

@Getter
public final class BadRequestException extends MercadolibreException {
	private final List<RequestError> errors;

	public BadRequestException(List<RequestError> errors) {
		super(null, null);
		this.errors = errors;
	}

	public BadRequestException(List<RequestError> errors, Throwable cause) {
		super(null, cause);
		this.errors = errors;
	}
}
