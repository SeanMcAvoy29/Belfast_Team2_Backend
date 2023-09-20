package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSpecRequest {
    private String jobRole;
    private String specifications;
    private String sharePointLink;

    public String getSharePointLink() {
        return sharePointLink;
    }

    public void setSharePointLink(String sharePointLink) {
        this.sharePointLink = sharePointLink;
    }

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

    @JsonCreator
    public JobSpecRequest(
            @JsonProperty("jobRole") String jobRole,
            @JsonProperty("specifications") String specifications,
            @JsonProperty("sharePointLink") String sharePointLink){
        this.jobRole = jobRole;
        this.specifications = specifications;
        this.sharePointLink = sharePointLink;
    }
}
