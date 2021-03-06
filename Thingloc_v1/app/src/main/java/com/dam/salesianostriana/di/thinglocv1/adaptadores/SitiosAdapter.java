package com.dam.salesianostriana.di.thinglocv1.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.salesianostriana.di.thinglocv1.R;
import com.dam.salesianostriana.di.thinglocv1.actitities.ScrollingActivity;
import com.dam.salesianostriana.di.thinglocv1.greendao.Sitios;
import com.dam.salesianostriana.di.thinglocv1.greendao.SitiosDao;
import com.dam.salesianostriana.di.thinglocv1.greendao.ValoracionesDao;
import com.dam.salesianostriana.di.thinglocv1.pojoschema.Result;
import com.dam.salesianostriana.di.thinglocv1.utiles.Utiles;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by flopez on 14/12/2015.
 */
public class SitiosAdapter extends RecyclerView.Adapter<SitiosAdapter.ViewHolder>{

    Context context;
    private List<Result> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, categoria, direccion, telefono;
        public ImageView foto;
        public View mView;


        public ViewHolder(View v) {
            super (v);
            mView = v;
            nombre = (TextView) v.findViewById(R.id.tv_nombre_sitio);
            categoria = (TextView) v.findViewById(R.id.tv_categoria_sitio);
            direccion = (TextView) v.findViewById(R.id.tv_direcc_sitio);
            telefono = (TextView) v.findViewById(R.id.tv_tlf_sitio);
            foto = (ImageView) v.findViewById(R.id.foto_sitio);
        }

    }
    public SitiosAdapter(List<Result> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public SitiosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_sitios,viewGroup,false);
        context = v.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SitiosAdapter.ViewHolder holder, final int position) {

        // GREENDAO (HITO 2)
        final ValoracionesDao valoracionesDao = Utiles.makeDataBase(context).getValoracionesDao();
        final SitiosDao sitiosDao = Utiles.makeDataBase(context).getSitiosDao();

        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        final String obj_id = mDataset.get(position).getObjectId();
        String json_desde_fecha = "{\"sitio\":{ \"__type\":\"Pointer\", \"className\":\"sitio\", \"objectId\":\""+obj_id+"\"},\"updatedAt\":{\"$gt\":{\"__type\":\"Date\",\"iso\":\""+preferences.getString("fecha_valoraciones",null)+"\"}}}";
        final Sitios sitios = sitiosDao.queryBuilder().where(SitiosDao.Properties.ObjectId.eq(obj_id)).unique();


        holder.nombre.setText(mDataset.get(position).getNombre());
        holder.categoria.setText(mDataset.get(position).getCategoria());
        holder.direccion.setText(mDataset.get(position).getDireccion());
        holder.telefono.setText(mDataset.get(position).getTelefono());
        Picasso.with(context).load(mDataset.get(position).getUrl_foto()).fit().placeholder(R.drawable.logoconletra)
               .into(holder.foto);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,ScrollingActivity.class);
                String objectId = mDataset.get(position).getObjectId();
                i.putExtra("objectId",objectId);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()   {
        return mDataset.size();
    }
}
