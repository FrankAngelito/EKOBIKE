package com.ekobike;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ViajeActivity extends AppCompatActivity {

    private int segundos = 0;
    private Handler handler = new Handler();
    private Runnable cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Recibe los datos de la bici que llegaron desde Bicicletas
        int codigo      = getIntent().getIntExtra("CODIGO", 0);
        String estacion = getIntent().getStringExtra("ESTACION");
        if (estacion == null) estacion = "Desconocida";

        TextView txtCronometro    = findViewById(R.id.txtCronometro);
        TextView txtBiciActual    = findViewById(R.id.txtBiciActual);
        TextView txtEstacionSalida = findViewById(R.id.txtEstacionSalida);
        Button btnTerminar        = findViewById(R.id.btnTerminarViaje);

        // Muestra los datos REALES de la bici elegida
        txtBiciActual.setText("Bici #" + codigo);
        txtEstacionSalida.setText(estacion);

        // Para usarlos dentro del botón, los guardamos en variables finales
        final String estacionFinal = estacion;
        final int codigoFinal = codigo;

        // El cronómetro
        cronometro = new Runnable() {
            @Override
            public void run() {
                int horas   = segundos / 3600;
                int minutos = (segundos % 3600) / 60;
                int segs    = segundos % 60;
                String tiempo = String.format(Locale.getDefault(),
                        "%02d:%02d:%02d", horas, minutos, segs);
                txtCronometro.setText(tiempo);
                segundos++;
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(cronometro);

        btnTerminar.setOnClickListener(v -> {
            handler.removeCallbacks(cronometro); // detiene el cronómetro
            Intent intent = new Intent(ViajeActivity.this, ResumenActivity.class);
            intent.putExtra("CODIGO", codigoFinal);
            intent.putExtra("ESTACION", estacionFinal);
            intent.putExtra("SEGUNDOS", segundos);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(cronometro);
    }
}