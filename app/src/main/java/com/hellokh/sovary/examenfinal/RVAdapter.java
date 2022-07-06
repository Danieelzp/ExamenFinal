package com.hellokh.sovary.examenfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Mesa> list = new ArrayList<>();

    public RVAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<Mesa> m) {
        list.addAll(m);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new com.hellokh.sovary.examenfinal.MesaVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Mesa e = null;
        this.onBindViewHolder(holder, position, e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Mesa e) {
        com.hellokh.sovary.examenfinal.MesaVH vh = (com.hellokh.sovary.examenfinal.MesaVH) holder;
        Mesa m = e == null ? list.get(position) : e;

        String ocupado;
        if (m.isOcupado()) {
            ocupado = "Ocupado";
        } else {
            ocupado = "Libre";
        }

        vh.lblMesa.setText("Mesa " + m.getNumero());
        vh.lblOcupado.setText(ocupado);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface MyCallback {
        void onCallback(Mesa mesa);
    }

    public void removeLastItem(MyCallback callback) {
        if (list == null || list.isEmpty()) return;

        int position = list.size() - 1;
        Mesa m = list.get(position);

        com.hellokh.sovary.examenfinal.DAOMesa dao = new com.hellokh.sovary.examenfinal.DAOMesa();
        dao.remove(m.getKey()).addOnSuccessListener(suc ->
        {
            Toast.makeText(context, "Cliente atendido", Toast.LENGTH_SHORT).show();
            notifyItemRemoved(position);
            list.remove(m);
            callback.onCallback(m);

            //Con esto podriamos mandar la mesa que se está borrando para poder activar de nuevo el boton
            //Lo que pasa es que esto abre otra actividad, y por defecto todos los botones están habilitados
            //Entonces no tiene mucho sentido, igual lo dejo aqui por cualquier vara
            //Hay que encontrar la forma de enviar datos a la otra actividad sin necesidad de abrir una nueva
            /*Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("MESA",m);
            context.startActivity(intent);*/

        }).addOnFailureListener(er ->
        {
            Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
