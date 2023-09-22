package com.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.JobRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getEmployees_shouldReturnListOfEmployees() {
        List response = APP.client().target("http://localhost:8080/api/job-roles")
                .request().get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getJobRoleByID_shouldReturnJobRole() {
        Response response = APP.client().target("http://localhost:8080/api/job-roles/1")
                .request().get();

        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals(1, response.readEntity(JobRequest.class).getJobID());
    }

    @Test
    void createJobRole_shouldReturnIdOfJobRole() {
        JobRequest jobRequest = new JobRequest(
                8,
                "Test",
                "test",
                "test",
                "Test"
        );

        int id = APP.client().target("http://localhost:8080/api/job")
                .request()
                .post(Entity.entity(jobRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        Assertions.assertNotNull(id);
    }

}
