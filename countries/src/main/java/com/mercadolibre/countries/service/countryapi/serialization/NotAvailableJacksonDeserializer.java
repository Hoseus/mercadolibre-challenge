package com.mercadolibre.countries.service.countryapi.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Custom deserializer needed because some response have two different types for the same attribute.
 * In this case this covers attributes that should be an object but sometimes are a string.
 */
public class NotAvailableJacksonDeserializer extends StdDeserializer<Object> {

	public NotAvailableJacksonDeserializer() {
		super(Object.class);
	}

	@Override
	public Object deserialize(JsonParser p, DeserializationContext ctxt)  throws IOException {
		JsonToken jsonToken = p.getCurrentToken();
		if (jsonToken == JsonToken.VALUE_STRING) {
			return null;
		}
		return new ObjectMapper().readValue(p, Object.class);
	}
}
