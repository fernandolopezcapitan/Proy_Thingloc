package com.salesianostriana.dam.thingloc_v2.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.categoria.Categoria;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto.ObjetoU;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ObjetoEditarActivity extends AppCompatActivity {
    EditText textVerObjetoNombre,textVerObjetoRec,textVerObjetoCoor;
    ImageView imageVistaObje;
    Switch sw;
    Long id;
    int errorCode;
    Spinner spinner2;
    List<String> listas  = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto_editar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=getBaseContext();

        id = getIntent().getLongExtra("id", 0);
        spinner2 = (Spinner) findViewById(R.id.spinnercat);

        textVerObjetoNombre = (EditText) findViewById(R.id.textEditarObjetoNombre);
        textVerObjetoRec = (EditText) findViewById(R.id.textEditarObjetoRec);
        textVerObjetoCoor = (EditText) findViewById(R.id.textEditarObjetoCoor);

        sw = (Switch) findViewById(R.id.switch2);
        imageVistaObje = (ImageView) findViewById(R.id.imageEditarObje);


        // Petición 6. Obtiene los datos de un objeto concreto
        // DEVUELVE los datos de un objeto.
        // ESPERA objectId, como parámetro en la URL. Token, obtenido en el login
        loadDataObjetos();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                    String[]cat = result.getCategoria().split("/");
                    loadDataCategoria();


                } else {


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

    }

    public void loadDataCategoria(){

        Call<Categoria> objetoCall = Utiles.serviceConInterceptorsAuth(getBaseContext()).obtenerCategoria();

        objetoCall.enqueue(new Callback<Categoria>() {

            @Override
            public void onResponse(Response<Categoria> response, Retrofit retrofit) {

                Categoria result = response.body();
                errorCode = response.code();

                Log.i("ERROR", String.valueOf(errorCode));

                if (errorCode == 200) {

                    for (int i=0; i<result.getResults().size();i++){

                        listas.add(result.getResults().get(i).getNombre());
                        Log.i("IteracionesCat", result.getResults().get(i).getNombre());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_spinner_item, listas);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(dataAdapter);

                } else {

                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }

}
