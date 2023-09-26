package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.JobSpecResponse;

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
        Assertions.assertEquals("Apprentice Software Engineer",response.readEntity(JobSpecResponse.class).getJobRole());
    }

    @Test
    void getJobSpec_shouldReturnJobSpecLinkInSpec(){
        String expectedLink = "https://kainossoftwareltd.sharepoint.com/:b:/r/people/Job%20Specifications/Engineering/Job%20profile%20-%20Apprentice%20Software%20Engineer%20(Apprentice).pdf?csf=1&web=1&e=0ZopF7";
        Response response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request().get();


        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals(expectedLink,response.readEntity(JobSpecResponse.class).getSharePointLink());
    }

    @Test
    void getJobSpec_shouldReturn404WhenIdDoesNotExist(){
        int id = 0;
        Response response = APP.client().target("http://localhost:8080/api/job-specification/"+id)
                .request().get();

        Assertions.assertEquals(404,response.getStatus());
    }

}
