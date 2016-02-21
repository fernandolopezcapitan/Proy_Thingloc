package com.salesianostriana.dam.thingloc_v2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje.Result;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios.UsuarioU;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by flopez on 21/02/2016.
 */
public class AdapterMensaje extends RecyclerView.Adapter<AdapterMensaje.CardViewHolder> {
    private List<Result> items;
    Context context;
    int errorCode;


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public TextView emisor,receptor,texto;


        public CardViewHolder(View v) {
            super(v);
            emisor = (TextView) v.findViewById(R.id.tv_Emisor);
            receptor =(TextView) v.findViewById(R.id.tv_Receptor);
            texto = (TextView) v.findViewById(R.id.tv_contenido_mensaje);

        }
    }

    public AdapterMensaje(List<Result> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_mensaje, viewGroup, false);

        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder viewHolder, final int i) {
        context = viewHolder.texto.getContext();
        viewHolder.texto.setText(items.get(i).getComentario());
        Log.i("Comentario", items.get(i).getComentario());

        String[]uE = items.get(i).getUsuarioEmisor().split("/");
        String[]uR = items.get(i).getUsuarioReceptor().split("/");
        Log.i("Split", uE[5]+" "+uR[5]);

        Call<UsuarioU> uECall = Utiles.serviceConInterceptorsAuth(context).obtenerUsuario(uE[5]);
        uECall.enqueue(new Callback<UsuarioU>() {

            @Override
            public void onResponse(Response<UsuarioU> response, Retrofit retrofit) {

                UsuarioU result = response.body();
                errorCode = response.code();

                Log.i("ERROR", String.valueOf(errorCode));

                if (errorCode == 200) {

                    Log.i("NOMBREUserE", result.getUsername());
                    viewHolder.emisor.setText(result.getUsername());

                } else {

                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });

        Call<UsuarioU> uRCall = Utiles.serviceConInterceptorsAuth(context).obtenerUsuario(uR[5]);
        uRCall.enqueue(new Callback<UsuarioU>() {

            @Override
            public void onResponse(Response<UsuarioU> response, Retrofit retrofit) {

                UsuarioU result = response.body();
                errorCode = response.code();

                Log.i("ERRORCode", String.valueOf(errorCode));

                if (errorCode == 200) {

                    Log.i("NOMBREUserR", result.getUsername());
                    viewHolder.receptor.setText(result.getUsername());

                } else {

                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });

    }


}
