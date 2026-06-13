package com.ekobike;

import java.util.ArrayList;

public class DatosBicicletas {

    // Devuelve todas las bicicletas de una estación
    public static ArrayList<Bicicleta> porEstacion(String estacion) {
        ArrayList<Bicicleta> lista = new ArrayList<>();

        if (estacion.equals("Cumbayá")) {
            lista.add(new Bicicleta(1, "Cumbayá", true));
            lista.add(new Bicicleta(2, "Cumbayá", true));
            lista.add(new Bicicleta(3, "Cumbayá", true));
            lista.add(new Bicicleta(4, "Cumbayá", true));
            lista.add(new Bicicleta(5, "Cumbayá", false));
            lista.add(new Bicicleta(6, "Cumbayá", true));
            lista.add(new Bicicleta(7, "Cumbayá", true));

        } else if (estacion.equals("La Carolina")) {
            lista.add(new Bicicleta(8, "La Carolina", true));
            lista.add(new Bicicleta(9, "La Carolina", true));
            lista.add(new Bicicleta(10, "La Carolina", false));
            lista.add(new Bicicleta(11, "La Carolina", true));
            lista.add(new Bicicleta(12, "La Carolina", true));

        } else if (estacion.equals("Valle de los Chillos")) {
            lista.add(new Bicicleta(13, "Valle de los Chillos", true));
            lista.add(new Bicicleta(14, "Valle de los Chillos", true));
            lista.add(new Bicicleta(15, "Valle de los Chillos", true));

        } else if (estacion.equals("Moran Valverde")) {
            lista.add(new Bicicleta(16, "Moran Valverde", true));
            lista.add(new Bicicleta(17, "Moran Valverde", true));
            lista.add(new Bicicleta(18, "Moran Valverde", true));
            lista.add(new Bicicleta(19, "Moran Valverde", true));
            lista.add(new Bicicleta(20, "Moran Valverde", true));
            lista.add(new Bicicleta(21, "Moran Valverde", true));
            lista.add(new Bicicleta(22, "Moran Valverde", true));
            lista.add(new Bicicleta(23, "Moran Valverde", true));
        }else if (estacion.equals("Calderon")) {
            lista.add(new Bicicleta(25, "Calderon", true));
            lista.add(new Bicicleta(26, "Calderon", true));
            lista.add(new Bicicleta(27, "Calderon", true));
            lista.add(new Bicicleta(28, "Calderon", true));
            lista.add(new Bicicleta(29, "Calderon", true));
            lista.add(new Bicicleta(30, "Calderon", true));
            lista.add(new Bicicleta(31, "Calderon", true));
            lista.add(new Bicicleta(32, "Calderon", true));
            lista.add(new Bicicleta(33, "Calderon", true));
            lista.add(new Bicicleta(34, "Calderon", true));
        }


        return lista;
    }
}