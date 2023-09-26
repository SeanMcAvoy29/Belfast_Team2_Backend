package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.BandService;
import org.kainos.ea.client.FailedToGetBandsException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Belfast_Team2 API")
@Path("/api")
public class BandController {
    private static BandService bandService;

    @GET
    @Path("/band")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands() {
        try {
            return Response.ok(bandService.getBands()).build();
        } catch (FailedToGetBandsException | SQLException e) {
           return Response.serverError().build();
        }
    }
}
