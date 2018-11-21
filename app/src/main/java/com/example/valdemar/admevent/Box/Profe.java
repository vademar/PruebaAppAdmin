package com.example.valdemar.admevent.Box;

public class Profe{
    public String IDPRO;
    public String PROFES;
    public String PRECIO;

    public Profe(String IDPRO, String PROFES, String PRECIO) {
        this.IDPRO = IDPRO;
        this.PROFES = PROFES;
        this.PRECIO = PRECIO;
    }

    public String getIDPRO() {
        return this.IDPRO;
    }

    public void setIDPRO(String IDPRO) {
        this.IDPRO = IDPRO;
    }

    public String getPROFES() {
        return this.PROFES;
    }

    public void setPROFES(String PROFES) {
        this.PROFES = PROFES;
    }

    public String getPRECIO() {
        return this.PRECIO;
    }

    public void setPRECIO(String PRECIO) {
        this.PRECIO = PRECIO;
    }
}
