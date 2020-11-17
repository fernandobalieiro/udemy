package org.acme

import org.eclipse.microprofile.config.spi.Converter

class Base64ValueConverter: Converter<Base64Value> {
    override fun convert(base64: String): Base64Value {
        return Base64Value(base64)
    }
}
