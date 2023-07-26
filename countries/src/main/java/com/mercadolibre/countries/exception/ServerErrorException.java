package com.mercadolibre.countries.exception;

import lombok.Getter;

@Getter
public final class ServerErrorException extends MercadolibreException {
	public ServerErrorException() {
	}

	public ServerErrorException(String message) {
		super(message);
	}

	public ServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerErrorException(Throwable cause) {
		super(cause);
	}
}
