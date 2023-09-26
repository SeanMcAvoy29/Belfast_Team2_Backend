package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRequest {
    private String jobRole;
    private int bandID;
    private int capabilityID;
    private String jobSpecification;
    private String responsibilities;
    private String sharePointLink;

    public String getSharePointLink() {
        return sharePointLink;
    }

    public void setSharePointLink(String sharePointLink) { this.sharePointLink = sharePointLink; }

    public int getCapabilityID() { return capabilityID; }

    public void setCapabilityID(int capabilityID) { this.capabilityID = capabilityID; }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public String getJobSpecification() {
        return jobSpecification;
    }

    public void setJobSpecification(String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    @JsonCreator
    public JobRequest(
            @JsonProperty("jobRole") String jobRole,
            @JsonProperty("bandID") int bandID,
            @JsonProperty("JobSpecifications") String JobSpecifications,
            @JsonProperty("responsibilities") String responsibilities,
            @JsonProperty("SharePointLink") String sharePointLink,
            @JsonProperty("capabilityID") int capabilityID)
    {
        this.jobRole = jobRole;
        this.bandID = bandID;
        this.jobSpecification = JobSpecifications;
        this.responsibilities = responsibilities;
        this.sharePointLink = sharePointLink;
        this.capabilityID = capabilityID;
    }
}
