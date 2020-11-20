package org.acme.service

import org.acme.data.MultipartBody
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA
import javax.ws.rs.core.MediaType.TEXT_PLAIN

@RegisterRestClient(configKey = "api.local")
@Path("/echo")
interface FileService {

    @POST
    @Consumes(MULTIPART_FORM_DATA)
    @Produces(TEXT_PLAIN)
    fun sendFile(@MultipartForm multipartBody: MultipartBody): String
}
