package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Api("Belfast_Team2 API")
@Path("/api")
public class JobController {
    private static JobService jobService;
    private static JobValidator jobValidator;

    public JobController() {
        DatabaseConnector connector = new DatabaseConnector();
        jobService = new JobService(new jobDao(), connector);
        jobValidator = new JobValidator();
    }

    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJob(JobRequest job) throws SpecificationTooLongException, JobRoleTooLongException, ResponsibilitiesTooLongException, BandNameTooLongException {
        if (jobValidator.isValidJob(job)) {
            try {
                int id = jobService.createJob(job);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            } catch (Exception e) {
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        } else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {
        try {
            return Response.ok(jobService.getAllJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (FailedToGetJobRolesException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleByID(@PathParam("id") int id) {
        try {
            return Response.ok(jobService.getJobRoleByID(id)).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        } catch (JobRoleDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("id") int id) {
        try {
            jobService.deleteJobRole(id);

            return Response.ok().build();
        } catch (JobRoleDoesNotExistException | DatabaseConnectionException | SQLException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteJobRoleException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobRole(@PathParam("id") int id, JobRequest job) {
        try {
            jobService.updateJobRole(id, job);

            return Response.ok().build();
        } catch (InvalidJobException | JobRoleDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateJobRoleException | DatabaseConnectionException | SQLException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
