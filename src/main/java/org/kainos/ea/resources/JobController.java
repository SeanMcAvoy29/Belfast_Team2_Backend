package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.Api;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Belfast_Team2 API")
@Path("/api")
public class JobController {
        private final JobService jobService = new JobService();
            @POST
            @Path("/job")
            @Produces(MediaType.APPLICATION_JSON)
            public Response createJob(JobRequest job) {
                try {
                    return Response.ok(jobService.createJob(job)).build();
                } catch (FailedToCreateJobException e) {
                    System.err.println(e.getMessage());

                    return Response.serverError().build();
                } catch (InvalidJobException e) {
                    System.err.println(e.getMessage());

                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
                }
            }

            @GET
            @Path("/job-roles")
            @Produces(MediaType.APPLICATION_JSON)
            public Response getJobRoles() {
                try {
                    return Response.ok(jobService.getAllJobRoles()).build();
                } catch (FailedToGetJobRolesException e) {
                    System.err.println(e.getMessage());

                    return Response.serverError().build();
                }
            }

            @GET
            @Path("/job-roles/{id}")
            @Produces(MediaType.APPLICATION_JSON)
            public Response getJobRoleByID(@PathParam("id") int id) {
                try {
                    return Response.ok(jobService.getJobRoleByID(id)).build();
                } catch (FailedToGetJobRolesException e) {
                    System.err.println(e.getMessage());

                    return Response.serverError().build();
                } catch (JobRoleDoesNotExistException e) {
                    System.err.println(e.getMessage());

                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            }

            @DELETE
            @Path("/job-roles/{id}")
            @Produces(MediaType.APPLICATION_JSON)
            public Response deleteJobRole(@PathParam("id") int id) {
                try {
                    jobService.deleteJobRole(id);

                    return Response.ok().build();
                } catch (JobRoleDoesNotExistException e) {
                    System.err.println(e.getMessage());

                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
                } catch (FailedToDeleteJobRoleException e) {
                    System.err.println(e.getMessage());

                    return Response.serverError().build();
                }
            }
                }
