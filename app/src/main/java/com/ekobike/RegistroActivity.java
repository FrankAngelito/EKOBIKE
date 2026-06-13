package com.ekobike;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        EditText edtNombre   = findViewById(R.id.edtNombre);
        EditText edtCorreo   = findViewById(R.id.edtCorreo);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtTelefono = findViewById(R.id.edtTelefono);
        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString().trim();
            String correo = edtCorreo.getText().toString().trim();
            String pass   = edtPassword.getText().toString().trim();
            String tel    = edtTelefono.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || tel.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_LONG).show();
            } else if (!correo.contains("@")) {
                Toast.makeText(this, "Correo inválido", Toast.LENGTH_LONG).show();
            } else if (Sesion.existe(this, correo)) {
                Toast.makeText(this, "Ese correo ya está registrado", Toast.LENGTH_LONG).show();
            } else {
                Sesion.registrar(this, correo, pass, nombre);
                Toast.makeText(this, "Cuenta creada, ahora inicia sesión", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}