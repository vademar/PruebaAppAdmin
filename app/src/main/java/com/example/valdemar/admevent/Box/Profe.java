package com.example.valdemar.admevent.Box;

public class Profe{
    public String PROFES;
    public String PRECIO;

    public Profe(String PROFES, String PRECIO) {
        this.PROFES = PROFES;
        this.PRECIO = PRECIO;
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
