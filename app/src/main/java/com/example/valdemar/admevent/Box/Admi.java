package com.example.valdemar.admevent.Box;

public class Admi {
    public String IDPRO;
    public String NOMBRE;
    public String CONTRASEÑA;

    public Admi(String IDPRO, String NOMBRE, String CONTRASEÑA) {
        this.IDPRO = IDPRO;
        this.NOMBRE = NOMBRE;
        this.CONTRASEÑA = CONTRASEÑA;
    }

    public String getIDPRO() {
        return this.IDPRO;
    }

    public void setIDPRO(String IDPRO) {
        this.IDPRO = IDPRO;
    }

    public String getNOMBRE() {
        return this.NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getCONTRASEÑA() {
        return this.CONTRASEÑA;
    }

    public void setCONTRASEÑA(String CONTRASEÑA) {
        this.CONTRASEÑA = CONTRASEÑA;
    }
}
