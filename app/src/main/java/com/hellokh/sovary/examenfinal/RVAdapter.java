package com.hellokh.sovary.examenfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<Mesa> list = new ArrayList<>();

    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Mesa> m)
    {
        list.addAll(m);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new com.hellokh.sovary.examenfinal.MesaVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Mesa e = null;
        this.onBindViewHolder(holder,position,e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Mesa e)
    {
        com.hellokh.sovary.examenfinal.MesaVH vh = (com.hellokh.sovary.examenfinal.MesaVH) holder;
        Mesa m = e==null? list.get(position):e;

        String ocupado;
        if(m.isOcupado()){
            ocupado = "Ocupado";
        }else{
            ocupado = "Libre";
        }

        vh.lblMesa.setText(m.getNumero());
        vh.lblOcupado.setText(ocupado);
        vh.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_remove:
                        com.hellokh.sovary.examenfinal.DAOMesa dao=new com.hellokh.sovary.examenfinal.DAOMesa();
                        dao.remove(m.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Cliente atendido", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(m);
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
