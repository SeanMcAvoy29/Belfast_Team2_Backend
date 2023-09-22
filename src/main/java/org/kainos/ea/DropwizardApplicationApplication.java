package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.CapabilityLeadController;
import org.kainos.ea.resources.JobSpecController;

import org.kainos.ea.api.JobService;
import org.kainos.ea.resources.JobController;

public class DropwizardApplicationApplication extends Application<DropwizardApplicationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardApplicationApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardApplication";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardApplicationConfiguration> bootstrap) {

        bootstrap.addBundle(new SwaggerBundle<DropwizardApplicationConfiguration>(){

            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardApplicationConfiguration configuration){

        bootstrap.addBundle(new SwaggerBundle<DropwizardApplicationConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardApplicationConfiguration configuration) {

                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final DropwizardApplicationConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(new JobSpecController());
        environment.jersey().register(new CapabilityLeadController());

        // TODO: implement application
        environment.jersey().register(new JobController());

    }

}
    }: