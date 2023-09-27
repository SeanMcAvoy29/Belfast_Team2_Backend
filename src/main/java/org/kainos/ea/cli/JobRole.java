package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;

public class JobRole {
    private int jobID;
    private String jobRoleName;
    private String jobSpecification;
    private String band;
    private String capability;
    private String responsibilities;
    private String sharePointLink;

    @JsonCreator
    public JobRole(int jobID, String jobRoleName, String jobSpecification, String band, String capability, String responsibilities, String sharePointLink) {
        this.jobID = jobID;
        this.jobRoleName = jobRoleName;
        this.jobSpecification = jobSpecification;
        this.band = band;
        this.capability = capability;
        this.responsibilities = responsibilities;
        this.sharePointLink = sharePointLink;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getJobRoleName() {
        return jobRoleName;
    }

    public void setJobRoleName(String jobRoleName) {
        this.jobRoleName = jobRoleName;
    }

    public String getJobSpecification() {
        return jobSpecification;
    }

    public void setJobSpecification(String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getSharePointLink() {
        return sharePointLink;
    }

    public void setSharePointLink(String sharePointLink) {
        this.sharePointLink = sharePointLink;
    }

}



