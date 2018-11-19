package com.example.valdemar.admevent.Box;

public class costo {
    private String profesion;
    private String precio;

    public costo(){};
    public costo (String profesion,String precio){
        setProfesion(profesion);
        setPrecio(precio);
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return profesion +"  :  "+ precio+" Bs";
    }
}