package com.github.benoj.resources

import com.github.benoj.api.Message
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("hello-world")
@Produces(MediaType.APPLICATION_JSON)
class HelloWorldResource {

    @GET
    fun get(): Message {
        return Message("Hello world!")
    }
}
