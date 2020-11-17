package org.acme

import io.quarkus.arc.config.ConfigProperties

@ConfigProperties(prefix = "greeting")
class GreetingConfig() {
    lateinit var prefix: String
    lateinit var suffix: String

    // Required by Quarkus in order to compile and set the values.
    constructor(
            prefix: String,
            suffix: String
    ) : this() {
        this.prefix = prefix
        this.suffix = suffix
    }
}
