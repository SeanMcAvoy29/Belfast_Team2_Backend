package org.kainos.ea.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/API")
public class TestController {
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTest() {
        return "THIS IS A TEST";
    }
}
