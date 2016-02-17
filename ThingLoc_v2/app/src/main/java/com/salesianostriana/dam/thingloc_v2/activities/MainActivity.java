package com.salesianostriana.dam.thingloc_v2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.Usuario;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;
import com.squareup.picasso.Picasso;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String sessionToken;
    //ImageView avatarCabecera;
    TextView usuarioCabecera, emailCabecera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO Añadir la funcionalidad escanear códigos Qr

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View cabeceraMenuLateral = navigationView.getHeaderView(0);
        //avatarCabecera = (ImageView)cabeceraMenuLateral.findViewById(R.id.icono_navegation);
        usuarioCabecera = (TextView)cabeceraMenuLateral.findViewById(R.id.username_navigation);
        emailCabecera = (TextView)cabeceraMenuLateral.findViewById(R.id.email_navigation);



        // TODO Abre de inicio Sitios Fragment
        //transicionPagina(new ObjetosFragment());

        prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        sessionToken = prefs.getString("Token ", null);


        // TODO
        //loadDataSessionToken(sessionToken,sessionToken);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment f = null;

        if (id == R.id.escanear) {

            // TODO Añadir la funcionalidad escanear códigos Qr

        } else if (id == R.id.objetos_perdidos) {

            //TODO
            //f = new ObjetosFragment();

        } else if (id == R.id.nav_cerrar_sesion) {

            //TODO
            //cancelDataSessionToken(sessionToken);

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            editor = prefs.edit();
            editor.remove("Token ");
            editor.apply();
            startActivity(i);
            this.finish();

        }

        if(f!=null) {
            transicionPagina(f);
        }

        // Marco el elemento del menú como elemento
        // seleccionado.
        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void transicionPagina(Fragment f) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor,f).commit();
    }

    private void loadDataSessionToken(final String sessionToken/*, final String sessionToken1*/){

        final Call<Usuario> loginCall = Utiles.serviceConInterceptors2(MainActivity.this).obtenerMisDatos(sessionToken);
        loginCall.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Response<Usuario> response, Retrofit retrofit) {
                Usuario usuario = response.body();

                if (usuario != null) {

                    usuarioCabecera.setText(usuario.getUsername().toString());
                    emailCabecera.setText(usuario.getEmail().toString());
                    //Picasso.with(MainActivity.this).load(usuario.getFoto().getUrl()).fit().placeholder(R.drawable.ic_usuarios).into(avatarCabecera);

                } else {

                    Toast.makeText(MainActivity.this, "No se han podido cargar los datos de usuario", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void cancelDataSessionToken(final String sessionToken) {
        final Call<Usuario> loginCall = Utiles.pedirServicioConInterceptores().cerrarSesion(sessionToken);
        loginCall.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Response<Usuario> response, Retrofit retrofit) {

                Toast.makeText(MainActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
