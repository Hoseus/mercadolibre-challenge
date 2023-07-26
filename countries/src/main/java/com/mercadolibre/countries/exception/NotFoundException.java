package com.mercadolibre.countries.exception;

import lombok.Getter;

@Getter
public final class NotFoundException extends MercadolibreException {
	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
