package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.kainos.ea.resources.TestController;

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
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardApplicationConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new TestController());
    }

}
