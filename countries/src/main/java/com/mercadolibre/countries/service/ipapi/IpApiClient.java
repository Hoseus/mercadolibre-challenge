package com.mercadolibre.countries.service.ipapi;

import io.quarkus.rest.client.reactive.ClientQueryParam;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "ip-api")
@ClientQueryParam(name = "access_key", value = "${countries.ip-api.api-key}")
public interface IpApiClient {
	@GET
	@Path("/{ip}")
	Uni<IpApiDto> get(@PathParam("ip") String ip);
}
