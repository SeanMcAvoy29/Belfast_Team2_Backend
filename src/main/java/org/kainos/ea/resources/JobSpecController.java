package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Belfast_Team2 API Job Specification")
@Path("/api")
public class JobSpecController {

    private final JobSpecService jobspecService;

    public JobSpecController(){
        DatabaseConnector connector = new DatabaseConnector();
        jobspecService = new JobSpecService(new JobSpecDAO(),connector);
    }
    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecById(@PathParam("id")int id){
        try{
            return Response.ok(jobspecService.getJobSpecById(id)).build();
        }catch(JobDoesNotExistException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (SQLException | DatabaseConnectionException e){
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
