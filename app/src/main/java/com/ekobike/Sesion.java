package com.ekobike;

import android.content.Context;
import android.content.SharedPreferences;

public class Sesion {

    private static final String ARCHIVO = "usuarios_ekobike";

    // Obtiene la "libreta" guardada en el teléfono
    private static SharedPreferences prefs(Context contexto) {
        return contexto.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE);
    }

    // Registrar: guarda la clave y el nombre asociados al correo
    public static void registrar(Context contexto, String correo, String clave, String nombre) {
        SharedPreferences.Editor editor = prefs(contexto).edit();
        editor.putString("clave_" + correo, clave);
        editor.putString("nombre_" + correo, nombre);
        editor.apply(); // guarda de forma permanente
    }

    // Revisar si un correo ya existe
    public static boolean existe(Context contexto, String correo) {
        return prefs(contexto).contains("clave_" + correo);
    }

    // Validar correo + contraseña al iniciar sesión
    public static boolean validar(Context contexto, String correo, String clave) {
        String guardada = prefs(contexto).getString("clave_" + correo, null);
        return guardada != null && guardada.equals(clave);
    }

    // Obtener el nombre de un usuario (para el saludo)
    public static String obtenerNombre(Context contexto, String correo) {
        return prefs(contexto).getString("nombre_" + correo, "Usuario");
    }
}