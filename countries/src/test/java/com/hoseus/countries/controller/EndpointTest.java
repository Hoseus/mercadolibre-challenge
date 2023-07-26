//package com.hoseus.countries.controller;
//
//import com.mercadolibre.countries.exception.BadRequestException;
//import com.hoseus.error.model.RequestError;
//import com.mercadolibre.countries.controller.CountryController;
//import com.mercadolibre.countries.service.ipapi.IpApiDto;
//import com.mercadolibre.countries.service.ipapi.IpApiService;
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.common.http.TestHTTPEndpoint;
//import io.quarkus.test.junit.QuarkusTest;
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.IOException;
//import java.util.Collections;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.*;
//import static org.mockito.ArgumentMatchers.anyString;
//
//@QuarkusTest
//@TestHTTPEndpoint(CountryController.class)
//public class EndpointTest {
//	private static final String BAD_FORMAT_IP = "bad_format_ip";
//	private static final String OK_IP_ARGENTINA = "190.210.32.163";
//	private static final String OK_IP_BRASIL = "200.147.35.149";
//
//	@InjectMock
//	@RestClient
//	IpApiService ipApiService;
//
//	@BeforeEach
//	public void beforeEach() {
//		Mockito.when(ipApiService.get("bad_format_ip")).thenThrow(new BadRequestException(Collections.singletonList(new RequestError("Invalid IP address")), null));
//		Mockito.when(ipApiService.get("190.210.32.163")).thenThrow(new BadRequestException(Collections.singletonList(new RequestError("Invalid IP address")), null));
//		Mockito.when(ipApiService.get(anyString())).thenReturn(new IpApiDto());
//	}
//
//	// {"code":"bad_request","errors":[{"description":"Invalid ip address"}]}
//	@Test
//	public void testCall() throws IOException {
//		given()
//		.when()
//		.get("/190.210.32.163")
//		.then()
//		.statusCode(200)
//		.body(
//			"code", is("bad_request"),
//			"errors.size()", is(1),
//			"errors.description", hasItem("Invalid IP address")
//		);
//	}
//}
//
