package com.hellokh.sovary.examenfinal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MesaVH extends RecyclerView.ViewHolder
{
    public TextView lblMesa, lblOcupado;
    public MesaVH(@NonNull View itemView)
    {
        super(itemView);
        lblMesa = itemView.findViewById(R.id.lblMesa);
        lblOcupado = itemView.findViewById(R.id.lblOcupado);
    }
}
