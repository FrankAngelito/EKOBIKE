package com.ekobike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResumenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Datos que llegan desde el viaje
        int codigo      = getIntent().getIntExtra("CODIGO", 0);
        String estacion = getIntent().getStringExtra("ESTACION");
        int segundos    = getIntent().getIntExtra("SEGUNDOS", 0);
        if (estacion == null) estacion = "Desconocida";

        TextView txtBici     = findViewById(R.id.txtResBici);
        TextView txtEstacion = findViewById(R.id.txtResEstacion);
        TextView txtTiempo   = findViewById(R.id.txtResTiempo);
        TextView txtTotal    = findViewById(R.id.txtResTotal);
        Button btnPagar      = findViewById(R.id.btnPagar);

        // Mostrar bici y estación
        txtBici.setText("Bici #" + codigo);
        txtEstacion.setText(estacion);

        // Mostrar tiempo en formato hh:mm:ss
        int horas   = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs    = segundos % 60;
        String tiempo = String.format(Locale.getDefault(),
                "%02d:%02d:%02d", horas, minutos, segs);
        txtTiempo.setText(tiempo);

        // Calcular el cobro: $1.99 por hora.
        // Como es por hora, calculamos la fracción de hora usada.
        // Mínimo se cobra $1.99 aunque el viaje sea corto.
        double tarifaHora = 1.99;
        double horasUsadas = segundos / 3600.0;
        double total = horasUsadas * tarifaHora;
        if (total < tarifaHora) {
            total = tarifaHora; // cobro mínimo de una hora
        }

        txtTotal.setText(String.format(Locale.getDefault(), "$%.2f", total));

        // Al pagar, va a la pantalla de Pagos
        final double totalFinal = total;
        btnPagar.setOnClickListener(v -> {
            Intent intent = new Intent(this, PagosActivity.class);
            intent.putExtra("TOTAL", totalFinal);
            startActivity(intent);
        });
    }
}