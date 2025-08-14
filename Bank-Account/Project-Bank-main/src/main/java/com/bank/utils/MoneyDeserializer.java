package com.bank.utils;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MoneyDeserializer extends JsonDeserializer<BigDecimal> {

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		String raw = p.getText();

		if (raw == null || raw.trim().isEmpty()) {
			return null;
		}

		String normalized = raw.replace("R$", "").replace("\u00A0","").replace("\\s", "").replace(".", "").replace(",", ".");
		return new BigDecimal(normalized);
	}

}
