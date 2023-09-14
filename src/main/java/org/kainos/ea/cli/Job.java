package org.kainos.ea.cli;

public class Job {
    private int jobID;
    private String jobRole;
    private String band;
    private String specifications;
    private String responsibilities;

    public Job(int jobID, String jobRole, String band, String specifications, String responsibilities) {
        this.jobID = jobID;
        this.jobRole = jobRole;
        this.band = band;
        this.specifications = specifications;
        this.responsibilities = responsibilities;
    }

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
}
