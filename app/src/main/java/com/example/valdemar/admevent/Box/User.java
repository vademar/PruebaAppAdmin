package com.example.valdemar.admevent.Box;

public class User {
    public String USID;
    public String NOMB;
    public String APEL;
    public String PROF;
    public String INST;
    public String CARG;
    public String CEDU;
    public String PASS;

    public User(String USID,String NOMB, String APEL, String PROF, String INST, String CARG, String CEDU, String PASS) {
        this.USID = USID;
        this.NOMB = NOMB;
        this.APEL = APEL;
        this.PROF = PROF;
        this.INST = INST;
        this.CARG = CARG;
        this.CEDU = CEDU;
        this.PASS = PASS;
    }

    public String getUSID() {
        return this.USID;
    }

    public void setUSID(String USID) {
        this.USID = USID;
    }

    public String getNOMB() {
        return this.NOMB;
    }

    public void setNOMB(String NOMB) {
        this.NOMB = NOMB;
    }

    public String getAPEL() {
        return this.APEL;
    }

    public void setAPEL(String APEL) {
        this.APEL = APEL;
    }

    public String getPROF() {
        return this.PROF;
    }

    public void setPROF(String PROF) {
        this.PROF = PROF;
    }

    public String getINST() {
        return this.INST;
    }

    public void setINST(String INST) {
        this.INST = INST;
    }

    public String getCARG() {
        return this.CARG;
    }

    public void setCARG(String CARG) {
        this.CARG = CARG;
    }

    public String getCEDU() {
        return this.CEDU;
    }

    public void setCEDU(String CEDU) {
        this.CEDU = CEDU;
    }

    public String getPASS() {
        return this.PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }
}
