package com.mercadolibre.countries.exception;

import lombok.Getter;


@Getter
public abstract class MercadolibreException extends RuntimeException {
	public MercadolibreException() {
	}

	public MercadolibreException(String message) {
		super(message);
	}

	public MercadolibreException(String message, Throwable cause) {
		super(message, cause);
	}

	public MercadolibreException(Throwable cause) {
		super(cause);
	}
}
