package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.api.JobspecService;
import org.kainos.ea.client.FailedToGetJobSpecException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Belfast_Team2 Job Specification API")
@Path("/api")
public class JobspecController {

    private JobspecService jobspecService = new JobspecService();
    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsById(@PathParam("id")int id){
        try{
            return Response.ok(jobspecService.getJobspecById(id)).build();
        }catch(FailedToGetJobSpecException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (JobDoesNotExistException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
