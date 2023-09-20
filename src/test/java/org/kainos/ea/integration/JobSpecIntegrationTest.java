package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.JobSpecRequest;

import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobSpecIntegrationTest {

    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobSpec_shouldReturnJobSpec(){
        Response response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request().get();

        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals("Job Role Name",response.readEntity(JobSpecRequest.class).getJobRole());
    }

    @Test
    void getJobSpec_shouldReturn404WhenIdDoesNotExist(){
        int id = 0;
        Response response = APP.client().target("http://localhost:8080/api/job-specification/"+id)
                .request().get();

        Assertions.assertEquals(404,response.getStatus());
    }

}
