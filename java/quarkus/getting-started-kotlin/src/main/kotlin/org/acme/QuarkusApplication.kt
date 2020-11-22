package org.acme

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import javax.ws.rs.core.Application

@OpenAPIDefinition(
        info = Info(
                title = "Getting Started API",
                description = "Our test API description",
                version = "1.2"
        )
//        ,
//        servers = [
//                Server(url = "org.acme")
//        ]
)
class QuarkusApplication : Application()
