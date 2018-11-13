package com.example.valdemar.admevent.Box;

public class Event {
    public String NOMB;
    public String FECI;
    public String HORI;
    public String FECF;
    public String HORF;
    public String DESC;
    public String COST;

    public Event(String NOMB, String FECI, String HORI, String FECF, String HORF, String DESC, String COST) {
        this.NOMB = NOMB;
        this.FECI = FECI;
        this.HORI = HORI;
        this.FECF = FECF;
        this.HORF = HORF;
        this.DESC = DESC;
        this.COST = COST;
    }

    public String getNOMB() {
        return this.NOMB;
    }

    public void setNOMB(String NOMB) {
        this.NOMB = NOMB;
    }

    public String getFECI() {
        return this.FECI;
    }

    public void setFECI(String FECI) {
        this.FECI = FECI;
    }

    public String getHORI() {
        return this.HORI;
    }

    public void setHORI(String HORI) {
        this.HORI = HORI;
    }

    public String getFECF() {
        return this.FECF;
    }

    public void setFECF(String FECF) {
        this.FECF = FECF;
    }

    public String getHORF() {
        return this.HORF;
    }

    public void setHORF(String HORF) {
        this.HORF = HORF;
    }

    public String getDESC() {
        return this.DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    public String getCOST() {
        return this.COST;
    }

    public void setCOST(String COST) {
        this.COST = COST;
    }
}
