package com.ekobike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Deja creado el admin la primera vez (si no existe aún)
        if (!Sesion.existe(this, "admin@ekobike.com")) {
            Sesion.registrar(this, "admin@ekobike.com", "1234", "Admin");
        }

        EditText edtCorreo   = findViewById(R.id.edtCorreo);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnIngresar   = findViewById(R.id.btnIngresar);
        TextView txtIrRegistro = findViewById(R.id.txtIrRegistro);

        btnIngresar.setOnClickListener(v -> {
            String correo   = edtCorreo.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa correo y contraseña", Toast.LENGTH_LONG).show();
            } else if (Sesion.validar(this, correo, password)) {
                String nombre = Sesion.obtenerNombre(this, correo);
                Toast.makeText(this, "Bienvenido " + nombre, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, EstacionesActivity.class);
                intent.putExtra("CORREO", correo);  // mandamos el correo para el saludo
                startActivity(intent);
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }
        });

        txtIrRegistro.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));
    }
}