package com.ekobike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BicicletaAdapter extends RecyclerView.Adapter<BicicletaAdapter.BiciViewHolder> {

    // Esto permite avisar a la Activity cuando se toca una bici
    public interface OnBiciClick {
        void alTocar(Bicicleta bici);
    }

    private ArrayList<Bicicleta> lista;
    private OnBiciClick listener;

    public BicicletaAdapter(ArrayList<Bicicleta> lista, OnBiciClick listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BiciViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bicicleta, parent, false);
        return new BiciViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull BiciViewHolder holder, int position) {
        Bicicleta bici = lista.get(position);

        holder.txtNombre.setText("Bici #" + bici.getCodigo());

        if (bici.isDisponible()) {
            holder.txtEstado.setText("$1.99/hora · Con Casco");
            holder.txtPastilla.setText("Disponible");
            holder.txtPastilla.setBackgroundColor(0xFF2E7D32); // verde
        } else {
            holder.txtEstado.setText("No disponible ahora");
            holder.txtPastilla.setText("Ocupada");
            holder.txtPastilla.setBackgroundColor(0xFFD32F2F); // rojo
        }

        // Al tocar la bici, avisa a la Activity
        holder.itemView.setOnClickListener(v -> listener.alTocar(bici));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class BiciViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtEstado, txtPastilla;

        public BiciViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre   = itemView.findViewById(R.id.txtNombreBici);
            txtEstado   = itemView.findViewById(R.id.txtEstadoBici);
            txtPastilla = itemView.findViewById(R.id.txtPastillaBici);
        }
    }
}