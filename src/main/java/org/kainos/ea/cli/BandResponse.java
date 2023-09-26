package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandResponse {
    private int BandID;
    private String BandName;

    @JsonCreator
    public BandResponse(@JsonProperty("BandID") int BandID,
                        @JsonProperty("BandName") String BandName)
    {
        this.BandID = BandID;
        this.BandName = BandName;
    }

    public int getBandID() {
        return BandID;
    }

    public void setBandID(int BandID) {
        this.BandID = BandID;
    }

    public String getBandName() {
        return BandName;
    }

    public void setBandName(String BandName) {
        this.BandName = BandName;
    }
}