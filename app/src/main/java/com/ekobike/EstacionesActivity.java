package com.ekobike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EstacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estaciones);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // Recibe el correo del Login y busca el nombre
        String correo = getIntent().getStringExtra("CORREO");
        final String correoFinal = (correo != null) ? correo : "";
        String nombre = "Usuario";
        if (correo != null) {
            nombre = Sesion.obtenerNombre(this, correo);
        }
        final String nombreFinal = nombre;

        // Saludo personalizado (ahora clickeable: abre el Perfil)
        TextView txtSaludo = findViewById(R.id.txtSaludo);
        txtSaludo.setText("Hola " + nombre);

        // Circulo con la inicial del nombre
        TextView txtInicialMini = findViewById(R.id.txtInicialMini);
        if (!nombre.isEmpty()) {
            txtInicialMini.setText(nombre.substring(0, 1).toUpperCase());
        }

        // Al tocar el circulo, abre el Perfil
        txtInicialMini.setOnClickListener(v -> {
            Intent intent = new Intent(EstacionesActivity.this, PerfilActivity.class);
            intent.putExtra("NOMBRE", nombreFinal);
            intent.putExtra("CORREO", correoFinal);
            startActivity(intent);
        });

        // Boton salir
        TextView txtSalir = findViewById(R.id.txtSalir);
        txtSalir.setOnClickListener(v -> {
            Toast.makeText(EstacionesActivity.this, "Sesion cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EstacionesActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Boton administrar: solo visible para el admin
        Button btnAdministrar = findViewById(R.id.btnAdministrar);
        if (correo != null && correo.equals("admin@ekobike.com")) {
            btnAdministrar.setVisibility(android.view.View.VISIBLE);
        }
        btnAdministrar.setOnClickListener(v ->
                startActivity(new Intent(EstacionesActivity.this, AdminActivity.class)));

        RecyclerView recycler = findViewById(R.id.recyclerEstaciones);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Estacion> lista = new ArrayList<>();
        lista.add(new Estacion("Cumbayá", 7));
        lista.add(new Estacion("La Carolina", 5));
        lista.add(new Estacion("Valle de los Chillos", 3));
        lista.add(new Estacion("Moran Valverde", 8));
        lista.add(new Estacion("Calderon", 10));

        EstacionAdapter adapter = new EstacionAdapter(lista, estacion -> {
            Intent intent = new Intent(this, BicicletasActivity.class);
            intent.putExtra("ESTACION", estacion.getNombre());
            startActivity(intent);
        });

        recycler.setAdapter(adapter);
    }
}