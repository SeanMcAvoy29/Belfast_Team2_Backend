package org.kainos.ea;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
<<<<<<< HEAD

=======
>>>>>>> US-Unit-Testing
import javax.validation.Valid;
import javax.validation.constraints.*;

public class DropwizardApplicationConfiguration extends Configuration {
    @Valid
    @NotNull
    private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger(){
        swagger.setResourcePackage("org.kainos.ea.resources");
<<<<<<< HEAD
        String[] schemes = {"http","https"};
        swagger.setSchemes(schemes);
        return swagger;
    }
=======
        String[] schemes ={"http", "https"};
        swagger.setSchemes(schemes);
        return swagger;
    }

>>>>>>> US-Unit-Testing

}
