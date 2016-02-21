package com.salesianostriana.dam.thingloc_v2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.fragments.ObjetosAddFragment;
import com.salesianostriana.dam.thingloc_v2.fragments.ObjetosFragment;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro.Logout;
import com.salesianostriana.dam.thingloc_v2.scancode.DecoderActivity;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String sessionToken;
    int errorCode;
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

                Intent inte = new Intent(MainActivity.this, DecoderActivity.class);
                startActivity(inte);
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

        String nombreCabecera = Utiles.obtainOfPreferences(getBaseContext(), "username");
        usuarioCabecera.setText(nombreCabecera);

        // TODO Pediente, imagen de cabecera
        emailCabecera = (TextView)cabeceraMenuLateral.findViewById(R.id.email_navigation);

        transicionPagina(new ObjetosFragment());

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

            Intent inte = new Intent(this, DecoderActivity.class);
            startActivity(inte);

        } else if (id == R.id.objetos_perdidos) {

            f = new ObjetosFragment();

        } else if (id == R.id.add_objeto_nuevo){

            //TODO Funcionalidad añadir objetos
            f = new ObjetosAddFragment();

        } else if (id == R.id.nav_cerrar_sesion) {

            // Petición 3. Logout.
            // REALIZA el proceso de logout, eliminando el session token.
            cancelDataSessionToken();

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



    private void cancelDataSessionToken() {

        final Call<Logout> loginCall = Utiles.serviceConInterceptorsAuth(getBaseContext()).cerrarSesion();
        loginCall.enqueue(new Callback<Logout>() {

            @Override
            public void onResponse(Response<Logout> response, Retrofit retrofit) {

                Logout result = response.body();
                errorCode = response.code();

                Log.i("ERROR", String.valueOf(errorCode));

                if (errorCode == 200) {

                    Log.i("Logout", result.getSuccess());
                    Utiles.cleanPreferences(getBaseContext());
                    Intent inte = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(inte);
                    finish();
                    Toast.makeText(MainActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

                } else {


                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
