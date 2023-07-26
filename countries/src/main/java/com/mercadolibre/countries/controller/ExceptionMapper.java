package com.mercadolibre.countries.controller;

import com.mercadolibre.countries.controller.dto.error.BadRequestErrorDto;
import com.mercadolibre.countries.controller.dto.error.DefaultErrorDto;
import com.mercadolibre.countries.controller.dto.error.ErrorDto;
import com.mercadolibre.countries.exception.*;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.function.BiFunction;

public class ExceptionMapper {
	private final BiFunction<Integer, ErrorDto, Uni<RestResponse<ErrorDto>>> responseBuilder =
		(status, body) -> Uni.createFrom().item(RestResponse.status(Response.Status.fromStatusCode(status), body));

	@ServerExceptionMapper({BadRequestException.class})
	public Uni<RestResponse<ErrorDto>> mapException(BadRequestException ex) {
		return this.responseBuilder.apply(
			400,
			new BadRequestErrorDto("bad_request", ex.getErrors())
		);
	}

	@ServerExceptionMapper
	public Uni<RestResponse<ErrorDto>> mapException(NotFoundException ex) {
		String message = ex.getMessage() != null ? ex.getMessage() : "Resource not found";
		return this.responseBuilder.apply(
			404,
			new DefaultErrorDto("not_found", message)
		);
	}

	@ServerExceptionMapper
	public Uni<RestResponse<ErrorDto>> mapException(BusinessException ex) {
		String message = ex.getMessage() != null ? ex.getMessage() : "Something went wrong";
		return this.responseBuilder.apply(
			422,
			new DefaultErrorDto("business_error", message)
		);
	}

	@ServerExceptionMapper
	public Uni<RestResponse<ErrorDto>> mapException(TimeoutException ex) {
		String message = ex.getMessage() != null ? ex.getMessage() : "The server timed out when communicating with another service";
		return this.responseBuilder.apply(
			500,
			new DefaultErrorDto("server_timeout", message)
		);
	}

	@ServerExceptionMapper
	public Uni<RestResponse<ErrorDto>> mapException(CommunicationException ex) {
		String message = ex.getMessage() != null ? ex.getMessage() : "The server failed to establish connection with another service";
		return this.responseBuilder.apply(
			500,
			new DefaultErrorDto("server_error", message)
		);
	}

	@ServerExceptionMapper
	public Uni<RestResponse<ErrorDto>> mapException(Exception ex) {
		String message = ex.getMessage() != null ? ex.getMessage() : "Something went wrong";
		return this.responseBuilder.apply(
			500,
			new DefaultErrorDto("server_error", message)
		);
	}
}
