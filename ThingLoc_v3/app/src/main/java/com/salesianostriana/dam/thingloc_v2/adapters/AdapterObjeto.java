package com.salesianostriana.dam.thingloc_v2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.activities.ScrollingActivity;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by flopez on 20/02/2016.
 */
public class AdapterObjeto extends RecyclerView.Adapter<AdapterObjeto.CardViewHolder>{
    private List<Result> mDataset;
    Context context;


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre,recompensa,encontrado;
        public ImageView img;
        public View mView;

        public CardViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.tv_nombre_objeto);
            recompensa = (TextView) v.findViewById(R.id.tv_recompensa);
            encontrado = (TextView) v.findViewById(R.id.tv_perdido);
            img = (ImageView) v.findViewById(R.id.foto_objeto);
            mView = v;
        }
    }

    public AdapterObjeto(List<Result> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_objetos, viewGroup, false);

        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardViewHolder viewHolder, final int i) {
        context = viewHolder.nombre.getContext();
        viewHolder.nombre.setText(mDataset.get(i).getNombre());
        if(mDataset.get(i).getRecompensa() == 0){
            viewHolder.recompensa.setText("Sin recompensa");
        } else {
            viewHolder.recompensa.setText(String.valueOf(mDataset.get(i).getRecompensa()));
        }

        if(mDataset.get(i).getPerdido()){
            viewHolder.encontrado.setText("Perdido");
        }else{
            viewHolder.encontrado.setText("Localizado");
        }
        Picasso.with(context)
                .load(mDataset.get(i).getFoto())
                .into(viewHolder.img);


        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ScrollingActivity.class);
                intent.putExtra("id", mDataset.get(i).getId());
                context.startActivity(intent);
            }
        });

    }
}
