package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class JobSpecRequest {
    private String jobRole;
    private String specifications;
    private List<String> responsibilities;

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    @JsonCreator
    public JobSpecRequest(
            @JsonProperty("jobRole") String jobRole,
            @JsonProperty("specifications") String specifications,
            @JsonProperty("responsibilities") List<String> responsibilities){
        this.jobRole = jobRole;
        this.specifications = specifications;
        this.responsibilities = responsibilities;
    }
}
