package org.kainos.ea.cli;

public class Band {
        private int BandID;
        private String BandName;

    public Band(int bandID, String bandName) {
        BandID = bandID;
        BandName = bandName;
    }

    public int getBandID() {
        return BandID;
    }

    public void setBandID(int bandID) {
        BandID = bandID;
    }

    public String getBandName() {
        return BandName;
    }

    public void setBandName(String bandName) {
        BandName = bandName;
    }
}
