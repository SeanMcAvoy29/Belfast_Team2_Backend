package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRequest {
    private int jobID;
    private String jobRole;
    private String band;
    private String specifications;
    private String responsibilities;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    @JsonCreator
    public JobRequest(
            @JsonProperty("jobID") int jobID,
            @JsonProperty("jobRole") String jobRole,
            @JsonProperty("band") String band,
            @JsonProperty("specifications") String specifications,
            @JsonProperty("responsibilities") String responsibilities) {
        this.jobID = jobID;
        this.jobRole = jobRole;
        this.band = band;
        this.specifications = specifications;
        this.responsibilities = responsibilities;
    }
}
