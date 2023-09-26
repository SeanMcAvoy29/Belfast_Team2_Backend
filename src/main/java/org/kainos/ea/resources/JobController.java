package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Belfast_Team2 API")
@Path("/api")
public class JobController {
    private static JobService jobService;

    public JobController() {
        DatabaseConnector connector = new DatabaseConnector();
        jobService = new JobService(new JobDao(), connector, new JobValidator());
    }

    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJob(JobRequest jobRequest) {
        try {
            int idJob = jobService.createJob(jobRequest);
            return Response.status(HttpStatus.CREATED_201).entity(idJob).build();
        } catch (SQLException | DatabaseConnectionException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (FailedToCreateJobException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }
}
