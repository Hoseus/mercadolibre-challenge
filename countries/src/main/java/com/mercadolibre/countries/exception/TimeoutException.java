package com.mercadolibre.countries.exception;

import lombok.Getter;

@Getter
public final class TimeoutException extends MercadolibreException {
	public TimeoutException() {
	}

	public TimeoutException(String message) {
		super(message);
	}

	public TimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeoutException(Throwable cause) {
		super(cause);
	}
}
