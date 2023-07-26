package com.mercadolibre.countries.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends MercadolibreException {
	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}
