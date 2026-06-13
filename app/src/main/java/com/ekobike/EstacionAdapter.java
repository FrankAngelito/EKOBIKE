package com.ekobike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EstacionAdapter extends RecyclerView.Adapter<EstacionAdapter.EstacionViewHolder> {

    // Detector de clics para avisar a la Activity
    public interface OnEstacionClick {
        void alTocar(Estacion estacion);
    }

    private ArrayList<Estacion> listaEstaciones;
    private OnEstacionClick listener;

    public EstacionAdapter(ArrayList<Estacion> listaEstaciones, OnEstacionClick listener) {
        this.listaEstaciones = listaEstaciones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EstacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_estacion, parent, false);
        return new EstacionViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EstacionViewHolder holder, int position) {
        Estacion estacion = listaEstaciones.get(position);
        holder.txtNombre.setText(estacion.getNombre());
        holder.txtBicis.setText("bicicletas disponibles");
        holder.txtCantidad.setText(String.valueOf(estacion.getCantidad()));

        // Al tocar la estación, avisa a la Activity
        holder.itemView.setOnClickListener(v -> listener.alTocar(estacion));
    }

    @Override
    public int getItemCount() {
        return listaEstaciones.size();
    }

    public static class EstacionViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtBicis, txtCantidad;

        public EstacionViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre   = itemView.findViewById(R.id.txtNombreEstacion);
            txtBicis    = itemView.findViewById(R.id.txtBicisDisponibles);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
        }
    }
}