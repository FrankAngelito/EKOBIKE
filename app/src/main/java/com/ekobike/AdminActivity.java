package com.ekobike;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        EditText edtCodigo   = findViewById(R.id.edtCodigoBici);
        EditText edtEstacion = findViewById(R.id.edtEstacionBici);
        Spinner spinnerEstado = findViewById(R.id.spinnerEstado);
        Button btnRegistrar  = findViewById(R.id.btnRegistrarBici);

        // Opciones del spinner: estados posibles de una bici
        String[] estados = {"Disponible", "Mantenimiento"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, estados);
        spinnerEstado.setAdapter(adapter);

        btnRegistrar.setOnClickListener(v -> {
            String codigo   = edtCodigo.getText().toString().trim();
            String estacion = edtEstacion.getText().toString().trim();
            String estado   = spinnerEstado.getSelectedItem().toString();

            if (codigo.isEmpty() || estacion.isEmpty()) {
                Toast.makeText(this, "Completa codigo y estacion", Toast.LENGTH_LONG).show();
            } else {
                // Mensaje distinto segun el estado elegido
                String mensaje;
                if (estado.equals("Mantenimiento")) {
                    mensaje = "Bici " + codigo + " de " + estacion +
                            " marcada en MANTENIMIENTO. No estara disponible.";
                } else {
                    mensaje = "Bici " + codigo + " de " + estacion +
                            " registrada como DISPONIBLE.";
                }

                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

                // Limpia los campos para registrar otra
                edtCodigo.setText("");
                edtEstacion.setText("");
            }
        });
    }
}