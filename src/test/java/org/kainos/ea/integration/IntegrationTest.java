package org.kainos.ea.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;
import static org.kainos.ea.cli.Role.Admin;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {
    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );
    @Test
    void postRegister_shouldReturn200nSuccess() {
        Register register = new Register("test12345@example.com", "password123", Admin);
        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(register, MediaType.APPLICATION_JSON_TYPE));

        String token = response.readEntity(String.class);

        assertEquals(200, response.getStatus());
        assertNotNull(token);
    }

    @Test
    void postLogin_shouldReturn400nFailedLogin() {
        Login login = new Login("test@example.com", "wrongpassword");
        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(login, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(400, response.getStatus());
    }

    @Test
    void postRegister_shouldReturn500errWhenDupEmailUsed() {
        String email = System.getenv("ADMIN_EMAIL");
        Register register = new Register(email, "Fake password", Admin);
        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(register, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(500, response.getStatus());
    }
}
