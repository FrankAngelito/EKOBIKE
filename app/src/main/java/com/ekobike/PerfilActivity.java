package com.ekobike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Recibe los datos que mando Estaciones
        String nombre = getIntent().getStringExtra("NOMBRE");
        String correo = getIntent().getStringExtra("CORREO");
        if (nombre == null) nombre = "Usuario";
        if (correo == null) correo = "sin correo";

        // Muestra el nombre y correo reales
        TextView txtNombre = findViewById(R.id.txtNombreUsuario);
        TextView txtCorreo = findViewById(R.id.txtCorreoUsuario);
        txtNombre.setText(nombre);
        txtCorreo.setText(correo);

        // Pone la primera letra del nombre en el círculo
        TextView txtInicial = findViewById(R.id.txtInicial);
        if (!nombre.isEmpty()) {
            String inicial = nombre.substring(0, 1).toUpperCase();
            txtInicial.setText(inicial);
        }

        // Boton cerrar sesion: vuelve al Login
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}