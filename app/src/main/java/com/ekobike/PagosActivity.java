package com.ekobike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PagosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Recibe el total que viene del Resumen
        double total = getIntent().getDoubleExtra("TOTAL", 0.0);

        TextView txtTotalPagar = findViewById(R.id.txtTotalPagar);
        txtTotalPagar.setText(String.format(Locale.getDefault(), "$%.2f", total));

        EditText edtNumeroTarjeta = findViewById(R.id.edtNumeroTarjeta);
        EditText edtVencimiento   = findViewById(R.id.edtVencimiento);
        EditText edtCvv           = findViewById(R.id.edtCvv);
        Button btnGuardarPago     = findViewById(R.id.btnGuardarPago);

        btnGuardarPago.setOnClickListener(v -> {
            String numero = edtNumeroTarjeta.getText().toString().trim();
            String venc   = edtVencimiento.getText().toString().trim();
            String cvv    = edtCvv.getText().toString().trim();

            if (numero.isEmpty() || venc.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(this, "Completa los datos de la tarjeta", Toast.LENGTH_LONG).show();
            } else if (numero.length() < 16) {
                Toast.makeText(this, "El numero debe tener 16 digitos", Toast.LENGTH_LONG).show();
            } else if (cvv.length() < 3) {
                Toast.makeText(this, "El CVV debe tener 3 digitos", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Pago exitoso. Gracias por usar EkoBike", Toast.LENGTH_LONG).show();
                // Vuelve al inicio (estaciones) limpiando el historial
                Intent intent = new Intent(PagosActivity.this, EstacionesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}