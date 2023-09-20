package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CapabilityLeadService;
import org.kainos.ea.client.CapabilityLeadDoesNotExistException;
import org.kainos.ea.client.FailedToGetCapabilityLeadException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Belfast_Team2 API")
@Path("/api")
public class CapabilityLeadController {
    private CapabilityLeadService capabilityLeadService = new CapabilityLeadService();
    @GET
    @Path("/capability-lead-info/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCapabilityLeadInfo(@PathParam("id")int id){
        try {
            return Response.ok(capabilityLeadService.getCapabilityLeadByCapabilityId(id)).build();
        } catch(FailedToGetCapabilityLeadException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (CapabilityLeadDoesNotExistException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
