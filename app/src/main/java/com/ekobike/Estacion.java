package com.ekobike;

public class Estacion {

    private String nombre;
    private int cantidad;

    public Estacion(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre()  { return nombre; }
    public int getCantidad()   { return cantidad; }
}