package com.salesianostriana.dam.thingloc_v2.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje.MensajeU;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios.Usuarios;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MensajeActivity extends AppCompatActivity {

    Long id;
    int errorCode;
    Spinner spinner2;
    Context context;
    List<String> listas  = new ArrayList<>();
    EditText texto;
    String usuE,usuRe,idobjeto,comentario,fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=getBaseContext();
        id = getIntent().getLongExtra("id", 0);
        spinner2 = (Spinner) findViewById(R.id.spineruser2);
        texto = (EditText) findViewById(R.id.editTextmensaje);

        // Petición para obtainOfPreferences datos de usuarios
        loadDataUsuario();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEXTOMENSAJE", "Spiner " + spinner2.getSelectedItem().toString() + " texto " + texto.getText().toString());
                comentario =texto.getText().toString();
                idobjeto = Utiles.urlbase+"api/objeto/"+id+"/";

                // Petición para obtainOfPreferences datos del emisor
                loadDataUserEmisor();
            }
        });
    }

    public void loadDataUsuario() {

        Call<Usuarios> objetoCall = Utiles.serviceConInterceptorsAuth(context).obtenerUsuarios();

        objetoCall.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {

                Usuarios result = response.body();
                errorCode = response.code();

                Log.i("ERRORCodeU", String.valueOf(errorCode));

                if (errorCode == 200) {

                    for (int i = 0; i < result.getResults().size(); i++) {

                        listas.add(result.getResults().get(i).getUsername());
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

    public void loadDataUserEmisor() {

        Call<Usuarios> objetoCall = Utiles.serviceConInterceptorsAuth(context).obtenerUsuarioPorId(Utiles.obtainOfPreferences(getBaseContext(), "username"));

        objetoCall.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {

                Usuarios result = response.body();
                errorCode = response.code();

                Log.i("ERRORCodeEmi", String.valueOf(errorCode));

                if (errorCode == 200) {

                    usuE=result.getResults().get(0).getUrl();

                    // Petición para obtainOfPreferences datos del receptor
                    loadDataUserReceptor();

                } else {
                    Toast.makeText(getBaseContext(), "No se ha podido enviar el mensaje", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Throwable t) {
            }

        });
    }

    public void loadDataUserReceptor() {
        Call<Usuarios> objetoCall = Utiles.serviceConInterceptorsAuth(context).obtenerUsuarioPorId(spinner2.getSelectedItem().toString());

        objetoCall.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Response<Usuarios> response, Retrofit retrofit) {

                Usuarios result = response.body();
                errorCode = response.code();

                Log.i("ERRORCodeRec", String.valueOf(errorCode));

                if (errorCode == 200) {

                    usuRe=result.getResults().get(0).getUrl();

                    // Petición 10. Almacenar un comentario.
                    // INSERTA un mensaje sobre un objeto.
                    // ESPERA Token, obtenido en el login, Y EL OBJETO EN JSON.
                    loadDataUpMsg();

                } else {
                    Toast.makeText(getBaseContext(), "No se ha podido enviar el mensaje", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Throwable t) {
            }

        });
    }

    public void loadDataUpMsg() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(new Date());
        Log.i("Fecha",fecha);
        MensajeU msgAEnviar = new MensajeU(usuE,usuRe,idobjeto,comentario,fecha);
        Call<MensajeU> objetoCall = Utiles.serviceConInterceptorsContent(context).subirMensajeObject(msgAEnviar);

        objetoCall.enqueue(new Callback<MensajeU>() {
            @Override
            public void onResponse(Response<MensajeU> response, Retrofit retrofit) {

                MensajeU result = response.body();
                errorCode = response.code();
                Log.i("ERROR", String.valueOf(errorCode));

                if ((errorCode == 200) || (errorCode == 201)) {
                    Log.i("MENSAJE", "Se ha enviado el msg");


                } else {
                    Toast.makeText(getBaseContext(), "No se ha podido enviar el mensaje", Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onFailure(Throwable t) {
            }

        });
    }

}
