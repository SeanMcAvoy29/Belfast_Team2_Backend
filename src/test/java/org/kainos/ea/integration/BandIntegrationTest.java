package org.kainos.ea.integration;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.DropwizardApplicationApplication;
import org.kainos.ea.DropwizardApplicationConfiguration;
import org.kainos.ea.cli.BandResponse;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BandIntegrationTest {
    static final DropwizardAppExtension<DropwizardApplicationConfiguration> APP = new DropwizardAppExtension<>(
            DropwizardApplicationApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getBands_shouldReturnListOfBands() {
        List<BandResponse> response = APP.client().target("http://localhost:8080/api/band")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
}