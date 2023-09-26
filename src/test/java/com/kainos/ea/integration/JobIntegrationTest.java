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

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    public void createJobRole_shouldReturnIdOfJobRole() {
        JobRequest jobRequest = new JobRequest(
                "Test",
                1,
                "test",
                "Test",
                "Test",
                2
        );
        int id = APP.client().target("http://localhost:8080/api/job")
                .request()
                .post(Entity.entity(jobRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        Assertions.assertNotNull(id);
    }

    @Test
    public void createJobRole_shouldReturn400_whenInvalidData() {
        JobRequest jobRequest = new JobRequest(
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest",
                1,
                "test",
                "Test",
                "Test",
                2
        );

        Response response = APP.client().target("http://localhost:8080/api/job")
                .request()
                .post(Entity.entity(jobRequest, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, response.getStatus());
    }
}
