package com.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.CapabilityLead;
import org.kainos.ea.cli.CapabilityLeadRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CapabilityIntegrationTest {

    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getCapabilityLead_shouldReturnCapabilityLead() {
        Response response = APP.client().target("http://localhost:8080/hr/employee/1")
                .request()
                .get();

        Assertions.assertEquals(200,response.getStatus());
        Assertions.assertEquals(1, response.readEntity(CapabilityLead.class).getCapabilityLeadName());
    }

    @Test
    void getEmployee_shouldReturnEmployeesDetails() {
        CapabilityLeadRequest capabilityLeadRequest = new CapabilityLeadRequest(
                "Engineering",
                "Patrick Jones",
                "Hello, this is a test message",
                "www.google.com"
        );

        int id = APP.client().target("http://localhost:8080/api/get-capability-lead")
                .request()
                .post(Entity.entity(capabilityLeadRequest, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Integer.class);

        CapabilityLead capabilityLead = APP.client().target("http://localhost:8080/hr/employee" + id)
                .request()
                .get()
                .readEntity(CapabilityLead.class);

        Assertions.assertNotNull(id);
    }

}
