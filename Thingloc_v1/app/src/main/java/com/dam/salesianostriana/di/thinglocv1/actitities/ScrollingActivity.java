package com.dam.salesianostriana.di.thinglocv1.actitities;

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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dam.salesianostriana.di.thinglocv1.R;
import com.dam.salesianostriana.di.thinglocv1.adaptadores.ComentariosAdapter;
import com.dam.salesianostriana.di.thinglocv1.greendao.Sitios;
import com.dam.salesianostriana.di.thinglocv1.greendao.SitiosDao;
import com.dam.salesianostriana.di.thinglocv1.pojoschema.Comentario;
import com.dam.salesianostriana.di.thinglocv1.pojoschema.Result;
import com.dam.salesianostriana.di.thinglocv1.pojoschema.pojoValoracion.Valoracion;
import com.dam.salesianostriana.di.thinglocv1.pojoschema.pojoValoracion.ComentariosAux;
import com.dam.salesianostriana.di.thinglocv1.utiles.Utiles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScrollingActivity extends AppCompatActivity {

    RatingBar rating;
    TextView categoria, direccion, tlf, descripcion, tv_valoraciones;

    Toolbar toolbar;
    ImageView imgBar;
    int error;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ComentariosAux> listadoComentarios;
    SitiosDao sitiosDao;
    double lat, longi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rating = (RatingBar) findViewById(R.id.rating_valoraciones);
        categoria = (TextView) findViewById(R.id.tv_categoria_scroll);
        direccion = (TextView) findViewById(R.id.tv_direcc_scroll);
        tlf =(TextView) findViewById(R.id.tv_tlf_scroll);
        descripcion = (TextView) findViewById(R.id.tv_descripcion_scroll);
        imgBar = (ImageView) findViewById(R.id.img_logo_scroll);
        tv_valoraciones = (TextView) findViewById(R.id.tv_valoraciones_scroll);

        Bundle extras = getIntent().getExtras();
        String objectId="";
        if(extras!=null){
            objectId =  extras.getString("objectId");
        }

        // GREENDAO (HITO 2)
        // Chequeo internet. Si no tengo internet,
        // recupero los datos de sitio y los cargo.
        sitiosDao = Utiles.makeDataBase(this).getSitiosDao();
        if(Utiles.checkInternet(this)){
            loadDataSitios(objectId);
        } else {
            Sitios sitios = sitiosDao.queryBuilder().where(SitiosDao.Properties.ObjectId.eq(objectId)).unique();

            assert sitios!=null;
            toolbar.setTitle(sitios.getNombre());
            descripcion.setText(sitios.getDescripcion());
            direccion.setText(sitios.getDireccion());
            categoria.setText(sitios.getCategoria());
            tv_valoraciones.setText("");
            if(sitios.getTlf() == null){
                tlf.setText("");
            } else {
                tlf.setText(sitios.getTlf());
            }
            Picasso.with(ScrollingActivity.this).load(sitios.getFotoUrl()).fit().placeholder(R.drawable.fondologin1).into(imgBar);
            lat = Double.parseDouble(sitios.getLatitud());
            longi = Double.parseDouble(sitios.getLongitud());
            rating.setRating(0);

        }
        //Carga del recycler de comentarios
        mRecyclerView = (RecyclerView) findViewById(R.id.comentarios_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(ScrollingActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScrollingActivity.this, "Comenta tu experiencia", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ScrollingActivity.this, EnviarcomentariosActivity.class);
                startActivity(i);
            }
        });
    }

    private void loadDataSitios(final String objectId) {
        final Call<Result> sitiosCall = Utiles.pedirServicioConInterceptores().obtenerDatosSitio(objectId);
        sitiosCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Response<Result> response, Retrofit retrofit) {
                Result result = response.body();

                toolbar.setTitle(result.getNombre());
                setSupportActionBar(toolbar);
                categoria.setText(result.getCategoria());
                direccion.setText(result.getDireccion());
                tlf.setText(result.getTelefono());
                descripcion.setText(result.getDescripcion());
                Picasso.with(ScrollingActivity.this).load(result.getFoto().getUrl()).fit().placeholder(R.drawable.fondologin1).into(imgBar);

                loadDataValoracionesSitio(objectId);
                loadDataComentarios(objectId);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    /////////////////////////////////////////
    // FUNCIONALIDAD INTEGRADA PARA HITO 2 //
    /////////////////////////////////////////
    private void loadDataComentarios(final String objectId){

        String consulta ="{\"sitio\": { \"__type\": \"Pointer\", \"className\": \"sitio\", \"objectId\": \""+objectId+"\" } }";

        consulta = Utiles.codificarEnUtf8(consulta);

        final Call<Comentario> comentariosCall = Utiles.pedirServicioConInterceptores().obtenerComentariosSitio(consulta);
        comentariosCall.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Response<Comentario> response, Retrofit retrofit) {
                Comentario result = response.body();

                listadoComentarios = new ArrayList();
                for (int i = 0;i<result.getResults().size();i++){
                    if (result.getResults()!= null){

                        String img = "";
                        if (result.getResults().get(i).getLogin().getFoto().getUrl() != null){
                            img = result.getResults().get(i).getLogin().getFoto().getUrl();
                        } else {
                            img = "";
                        }

                        listadoComentarios.add(new ComentariosAux(
                                img,
                                result.getResults().get(i).getLogin().getNombre(),
                                result.getResults().get(i).getComentario(),
                                result.getResults().get(i).getCreatedAt()));

                    }
                }
                mRecyclerView.setAdapter(new ComentariosAdapter(listadoComentarios));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
    private void loadDataValoracionesSitio(final String objectId){

        String consulta ="{\"sitio\": { \"__type\": \"Pointer\", \"className\": \"sitio\", \"objectId\": \""+objectId+"\" } }";

        consulta = Utiles.codificarEnUtf8(consulta);

        final Call<Valoracion> valoracionCall = Utiles.pedirServicioConInterceptores().obtenerValoracionesSitio(consulta);
        valoracionCall.enqueue(new Callback<Valoracion>() {
            @Override
            public void onResponse(Response<Valoracion> response, Retrofit retrofit) {
                Valoracion valoracion = response.body();

                error = response.code();

                Log.i("fallo", String.valueOf(error));

                String cadena = String.valueOf(error);
                String primer_numero = cadena.substring(0, 1);
                int formateado = Integer.parseInt(primer_numero);

                if(formateado == 2){
                    float mediaValoraciones = 0;
                    if (valoracion != null) {

                        for (int i = 0; i < valoracion.getResults().size(); i++) {
                            if(valoracion.getResults().get(i).getValoracion()!= null)
                                mediaValoraciones = valoracion.getResults().get(i).getValoracion() + mediaValoraciones;
                        }
                        mediaValoraciones = mediaValoraciones / valoracion.getResults().size();
                    }
                    rating.setRating(mediaValoraciones);
                    rating.setIsIndicator(true);
                } else {
                    Toast.makeText(ScrollingActivity.this, "Hemos detectado un error. Estamos trabajando para solucionarlo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
