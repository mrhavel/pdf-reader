package de.somePackage.beans;

import java.io.File;

public class BahnReise {

    private String bahncode;

    private String time;

    private String reisender;

    private String preis;

    private File originalPDF;

    private File finalPDF;

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

    public File getOriginalPDF() {
        return originalPDF;
    }

    public void setOriginalPDF(File originalPDF) {
        this.originalPDF = originalPDF;
    }

    public File getFinalPDF() {
        return finalPDF;
    }

    public void setFinalPDF(File finalPDF) {
        this.finalPDF = finalPDF;
    }
}
