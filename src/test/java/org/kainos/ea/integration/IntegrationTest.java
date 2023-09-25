package org.kainos.ea.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.kainos.ea.db.DatabaseConnector;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.kainos.ea.cli.Role.Admin;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {
    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );
    public static void deleteAccount() throws SQLException {
        String email = System.getenv("ADMIN_EMAIL");
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection c = databaseConnector.getConnection();

        try{
            String deleteStatement = "DELETE FROM `User` WHERE (`Email` = '"+email+ "');";
            PreparedStatement st = c.prepareStatement(deleteStatement);
            st.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    @Test
    void postRegister_shouldReturn200nSuccess() {
        String email = System.getenv("ADMIN_EMAIL");
        String password = System.getenv("ADMIN_PASSWORD");
        Register register = new Register(email, password, Admin);
        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(register, MediaType.APPLICATION_JSON_TYPE));

        String token = response.readEntity(String.class);

        assertEquals(200, response.getStatus());
        assertNotNull(token);
    }

    @Test
    void postLogin_shouldReturn400nFailedLogin() {
        String email = System.getenv("ADMIN_EMAIL");
        Login login = new Login(email, "wrongpassword");
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

    //Tear Down
    @AfterAll
    public static void after(){
        try {
            deleteAccount();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
