package com.salesianostriana.dam.thingloc_v2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.adapters.AdapterMensaje;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.categoria.CategoriaU;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje.Mensaje;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje.Result;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto.ObjetoU;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScrollingActivity extends AppCompatActivity {

    TextView textVerObjetoNombre,textVerObjetoRec,textVerObjetoCoor,textVerObjetoCat,textEmisor,textReceptor;
    ImageView imageVistaObje;
    Switch sw;
    int id;
    int errorCode;
    Toolbar toolbar;
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        id = getIntent().getIntExtra("id", 0);

        textVerObjetoNombre = (TextView) findViewById(R.id.tv_nombre_objeto);
        textVerObjetoRec = (TextView) findViewById(R.id.tv_recompensa_dinero);
        textVerObjetoCoor = (TextView) findViewById(R.id.tv_coordenadas_latlong);
        textVerObjetoCat = (TextView) findViewById(R.id.tv_categoria_elegir);
        sw = (Switch) findViewById(R.id.switch1);
        sw.setEnabled(false);
        imageVistaObje = (ImageView) findViewById(R.id.img_logo_scroll);
        textEmisor = (TextView) findViewById(R.id.tv_Emisor);
        textReceptor = (TextView) findViewById(R.id.tv_Receptor);
        mRecycler = (RecyclerView) findViewById(R.id.comentarios_recycler_view);
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecycler.setLayoutManager(mLayoutManager);


        // Petición 6. Obtiene los datos de un objeto concreto
        // DEVUELVE los datos de un objeto.
        // ESPERA objectId, como parámetro en la URL. Token, obtenido en el login
        loadDataObjetos();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getBaseContext(), MensajeActivity.class);
                inte.putExtra("id", id);
                startActivity(inte);
            }
        });
    }

    public void loadDataObjetos(){

        Call<ObjetoU> objetoCall = Utiles.serviceConInterceptorsAuth(getBaseContext()).objectDeUsuario(String.valueOf(id));

        objetoCall.enqueue(new Callback<ObjetoU>() {

            @Override
            public void onResponse(Response<ObjetoU> response, Retrofit retrofit) {

                ObjetoU result = response.body();
                errorCode = response.code();

                Log.i("ERROR", String.valueOf(errorCode));

                if (errorCode == 200) {

                    textVerObjetoNombre.setText(result.getNombre());
                    textVerObjetoRec.setText(String.valueOf(result.getRecompensa()));
                    textVerObjetoCoor.setText(result.getCoordenadas());

                    if (result.isPerdido()){
                        sw.setChecked(true);
                    }else{
                        sw.setChecked(false);
                    }

                    Picasso.with(getBaseContext())
                            .load(result.getFoto())
                            .into(imageVistaObje);



                    // Petición 8. Obtener todas las categorias.
                    // DEVUELVE un listado con todas las categorias
                    // ESPERA Token (obtenido en el login).
//                    String[]cat = result.getCategoria().split("/");
//                    Log.i("categoria0", cat[0]);
//                    Log.i("categoria1", cat[1]);
//                    Log.i("categoria2", cat[2]);
//                    Log.i("categoria3", cat[3]);
//                    Log.i("categoria4", cat[4]);
//                    Log.i("categoria5", cat[5]);
//
//                    loadDataCategoria(cat[5]);

                    // Petición 9. Obtener todos los mensajes de un objeto.
                    // DEVUELVE el listado con todos los mensajes de chat de un objeto concreto.
                    // ESPERA Token, obtenido en el login. Id del objeto, proporcionado como valor URL-encoded.
                    loadDataMensajes(String.valueOf(result.getId()));

                } else {


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

    }

    public void loadDataCategoria(String idd){

        Call<CategoriaU> objetoCall = Utiles.serviceConInterceptorsAuth(getBaseContext()).obtenerCategoriaUser(idd);

        objetoCall.enqueue(new Callback<CategoriaU>() {

            @Override
            public void onResponse(Response<CategoriaU> response, Retrofit retrofit) {

                CategoriaU result = response.body();
                errorCode = response.code();

                Log.i("ERROR", String.valueOf(errorCode));

                if (errorCode == 200) {

                    Log.i("NOMBRECAT", result.getName());
                    textVerObjetoCat.setText(result.getName());

                } else {

                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }

    public void loadDataMensajes(String idd){

        Call<Mensaje> userCall = Utiles.serviceConInterceptorsAuth(getApplicationContext()).obtenerMsgObjet(String.valueOf(idd));

        userCall.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Response<Mensaje> response, Retrofit retrofit) {


                Mensaje result = response.body();
                errorCode = response.code();
                Log.i("ERROR_Mensajes", String.valueOf(errorCode));

                if (errorCode == 200) {
                    List<Result> listadoDeMensajes= new ArrayList<>();

                    for (int i = 0; i< result.getResults().size(); i++){
                        listadoDeMensajes.add(result.getResults().get(i));

                    }

                    mAdapter = new AdapterMensaje(listadoDeMensajes);
                    mRecycler.setAdapter(mAdapter);


                } else {


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }
}
