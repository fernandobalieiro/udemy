package org.acme.data

import org.jboss.resteasy.annotations.providers.multipart.PartType
import java.io.InputStream
import javax.ws.rs.FormParam
import javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM
import javax.ws.rs.core.MediaType.TEXT_PLAIN

data class MultipartBody(
        @field:FormParam("file")
        @field:PartType(APPLICATION_OCTET_STREAM)
        val file: InputStream,

        @field:FormParam("name")
        @field:PartType(TEXT_PLAIN)
        val name: String
)
