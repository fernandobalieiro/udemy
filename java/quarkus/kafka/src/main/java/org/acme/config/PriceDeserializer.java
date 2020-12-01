package org.acme.config;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import org.acme.data.Price;

public class PriceDeserializer extends JsonbDeserializer<Price> {

    public PriceDeserializer() {
        super(Price.class);
    }

    public PriceDeserializer(Class<Price> type) {
        super(type);
    }
}
