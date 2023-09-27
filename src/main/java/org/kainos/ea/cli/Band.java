package org.kainos.ea.cli;

public class Band {
    private int BandID;
    private String BandName;

    public Band(int BandID, String BandName) {
        BandID = BandID;
        BandName = BandName;
    }

    public int getBandID() {
        return BandID;
    }

    public void setBandID(int BandID) {
        BandID = BandID;
    }

    public String getBandName() {
        return BandName;
    }

    public void setBandName(String BandName) {
        BandName = BandName;
    }
}