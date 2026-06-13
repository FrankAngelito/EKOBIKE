package com.ekobike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BicicletasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicicletas);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Recibe el nombre de la estación que llegó desde Estaciones
        String estacion = getIntent().getStringExtra("ESTACION");
        if (estacion == null) estacion = "Cumbayá";

        // Cambia el título para mostrar la estación
        TextView txtTitulo = findViewById(R.id.txtTituloBicis);
        txtTitulo.setText("Bicicletas en " + estacion);

        RecyclerView recycler = findViewById(R.id.recyclerBicis);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // Trae las bicicletas de esa estación
        ArrayList<Bicicleta> bicis = DatosBicicletas.porEstacion(estacion);

        // Crea el adapter con el detector de clics
        BicicletaAdapter adapter = new BicicletaAdapter(bicis, bici -> {
            if (bici.isDisponible()) {
                // Va al viaje, llevando los datos de la bici
                Intent intent = new Intent(this, ViajeActivity.class);
                intent.putExtra("CODIGO", bici.getCodigo());
                intent.putExtra("ESTACION", bici.getEstacion());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Esa bicicleta está ocupada", Toast.LENGTH_SHORT).show();
            }
        });

        recycler.setAdapter(adapter);
    }
}