package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CapabilityLead {
    private String capabilityName;
    private String capabilityLeadName;
    private String capabilityLeadMessage;
    private String capabilityLeadPhotoURL;

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getCapabilityLeadName() {
        return capabilityLeadName;
    }

    public void setCapabilityLeadName(String capabilityLeadName) {
        this.capabilityLeadName = capabilityLeadName;
    }

    public String getCapabilityLeadMessage() {
        return capabilityLeadMessage;
    }

    public void setCapabilityLeadMessage(String capabilityLeadMessage) {
        this.capabilityLeadMessage = capabilityLeadMessage;
    }

    public String getCapabilityLeadPhotoURL() {
        return capabilityLeadPhotoURL;
    }

    public void setCapabilityLeadPhotoURL(String capabilityLeadPhotoURL) {
        this.capabilityLeadPhotoURL = capabilityLeadPhotoURL;
    }

    @JsonCreator
    public CapabilityLead(
            @JsonProperty("capabilityName") String capabilityName,
            @JsonProperty("capabilityLeadName") String capabilityLeadName,
            @JsonProperty("capabilityLeadMessage") String capabilityLeadMessage,
            @JsonProperty("capabilityLeadPhotoURL") String capabilityLeadPhotoURL)
    {
        this.capabilityName = capabilityName;
        this.capabilityLeadName = capabilityLeadName;
        this.capabilityLeadMessage = capabilityLeadMessage;
        this.capabilityLeadPhotoURL = capabilityLeadPhotoURL;
    }

}
