package de.somePackage;

public class BahnReise {

    private String bahncode;

    private String time;

    private String reisender;

    private String preis;

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getReisender() {
        return reisender;
    }

    public void setReisender(String reisender) {
        this.reisender = reisender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBahncode() {
        return bahncode;
    }

    public void setBahncode(String bahncode) {
        this.bahncode = bahncode;
    }
}
}
