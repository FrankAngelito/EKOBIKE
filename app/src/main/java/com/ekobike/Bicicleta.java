package com.ekobike;

public class Bicicleta {

    private int codigo;
    private String estacion;
    private boolean disponible;

    public Bicicleta(int codigo, String estacion, boolean disponible) {
        this.codigo = codigo;
        this.estacion = estacion;
        this.disponible = disponible;
    }

    public int getCodigo()        { return codigo; }
    public String getEstacion()   { return estacion; }
    public boolean isDisponible() { return disponible; }
}