package com.mercadolibre.countries.exception;

import lombok.Getter;

@Getter
public final class CommunicationException extends MercadolibreException {
	public CommunicationException() {
	}

	public CommunicationException(String message) {
		super(message);
	}

	public CommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommunicationException(Throwable cause) {
		super(cause);
	}
}
