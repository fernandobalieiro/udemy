package org.acme.greeting

import java.util.Base64

class Base64Value(var base64: String) {
    private var value: String = String(Base64.getDecoder().decode(base64))

    override fun toString(): String {
        return value
    }
}
