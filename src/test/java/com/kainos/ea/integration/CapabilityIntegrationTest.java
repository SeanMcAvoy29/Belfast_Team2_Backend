package com.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.CapabilityLeadRequest;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityIntegrationTest {

    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getCapabilityLead_shouldReturnCapabilityLead() {
        Response response = APP.client().target("http://localhost:8080/api/capability-lead-info/1")
                .request()
                .get();

        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals("Patrick Jones", response.readEntity(CapabilityLeadRequest.class).getCapabilityLeadName());
    }

    @Test
    void getCapabilityLead_shouldReturnError400WhenRequestBad() {
        Response response = APP.client().target("http://localhost:8080/api/capability-lead-info/99999")
                .request()
                .get();

        Assertions.assertEquals(400,response.getStatus());
    }





}
