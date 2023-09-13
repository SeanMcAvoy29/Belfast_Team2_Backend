package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.FailedToCreateJobException;
import org.kainos.ea.client.InvalidJobException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
        }
