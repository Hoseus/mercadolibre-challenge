package com.mercadolibre.countries.exception;

import lombok.Getter;

@Getter
public final class UnexpectedException extends MercadolibreException {
	private static final String DEFAULT_MESSAGE = "Something went wrong";
	public UnexpectedException() {
		super(DEFAULT_MESSAGE);
	}

	public UnexpectedException(String message) {
		super(message);
	}

	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}
}
