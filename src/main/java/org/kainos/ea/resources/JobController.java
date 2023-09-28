package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.BandService;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;

import javax.swing.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Belfast_Team2 API")
@Path("/api")
public class JobController {
    private static JobRoleService jobService;
    private static BandService bandService;
    public JobController() {
        DatabaseConnector connector = new DatabaseConnector();
        jobService = new JobRoleService(new JobRoleDao(), connector);
        bandService = new BandService(new BandDao(), connector);
    }

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {
        try {
            return Response.ok(jobService.getAllJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException | JobRoleDoesNotExistException e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/band-names")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBands() {
        try {
            return Response.ok(bandService.getBands()).build();
        } catch (FailedToGetBandsException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
