package com.dam.salesianostriana.di.thinglocv1.actitities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dam.salesianostriana.di.thinglocv1.R;
import com.dam.salesianostriana.di.thinglocv1.pojosthingloc.Login;
import com.dam.salesianostriana.di.thinglocv1.utiles.Utiles;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {


    EditText user, pass;
    Button btn_entrar;
    SharedPreferences prefs;
    SharedPreferences.Editor editor = null;
    Intent i;
    int errorCode;
    String usuario,passw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getSupportActionBar().hide(); Si la dejo puesta da error de version support v7

        user = (EditText) findViewById(R.id.usuario);
        pass = (EditText) findViewById(R.id.pass);
        btn_entrar = (Button) findViewById(R.id.btn_entrar);

        prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = prefs.edit();

        if(prefs.getString("Token ",null)!=null){
            i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }

        // GREENDAO (HITO 2)
        // Creo la base de datos y la guardo en UsuariosDao
        //usuarioDao = Utiles.makeDataBase(this).getUsuariosDao();

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = user.getText().toString();
                passw = pass.getText().toString();

                // GREENDAO (HITO 2)
                // Chequeo la conexión.
                // Inserto los datos en la base de datos cuando haya conexión, para usarlos
                // cuando no tenga internet.
//                if (Utiles.checkInternet(LoginActivity.this)) {
                    loadDataLogin(usuario, passw);
//                } else {
//                    i = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(i);
//                    LoginActivity.this.finish();
//                }

            }
        });

    }

    private void loadDataLogin(final String user, final String password){
        Login newLogin = new Login();
        newLogin.setUsername(usuario);
        newLogin.setPassword(passw);


        final Call<Login> loginCall = Utiles.pedirServicioConInterceptores().loginUsuario(newLogin);
        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login login = response.body();


                errorCode = response.code();


                Log.d("TOKEN", login.getKey());

//                if (login != null) {
//                    if (login.getUsername().equals(user) && password.equals("12345")) {
//                        String sessionToken = login.getSessionToken();
//                        i = new Intent(LoginActivity.this, MainActivity.class);
//
//                        // GREENDAO (HITO 2)
//                        // Guardo en prefs la fecha de los sitios para más adelante comprobar updateAt
//                        editor.putString("sessionToken", sessionToken);
//                        if(prefs.getString("fecha_sitios",null) == null || prefs.getString("fecha_valoraciones",null) == null ||prefs.getString("fecha_comentarios",null) == null) {
//                            editor.putString("fecha_sitios", "0000-01-01T00:00:00.000Z");
//                            editor.putString("fecha_valoraciones", "0000-01-01T00:00:00.000Z");
//                            editor.putString("fecha_comentarios", "0000-01-01T00:00:00.000Z");
//                        }
//                        editor.apply();
//
//                        i.putExtra("sessionToken", sessionToken);
//
//                        startActivity(i);
//                        LoginActivity.this.finish();
//
//                        // GREENDAO (HITO 2)
//                        // Cuando tengo internet inserto los datos del usuario logueado
//                        Usuarios usuario_green = new Usuarios(login.getObjectId(),login.getUpdatedAt(),login.getSessionToken(),login.getUsername(),login.getNombre(),login.getEmail(),login.getFoto().getUrl());
//                        usuarioDao.insert(usuario_green);
//
//                    }else {
//                        Toast.makeText(LoginActivity.this, "Fallo de usuario o contraseña", Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                    Toast.makeText(LoginActivity.this, "Fallo de usuario o contraseña", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    // Este método pide conexión a internet para continuar la app
    // En caso de no tenerla, abre los ajustes de conexión
    // Nota: no lo usaremos porque la app soportará una base de datos sin conexión (GreenDao)

    /*private boolean checkInternet() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null || !i.isConnected() || !i.isAvailable()) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle(R.string.alert_title_internet);
            builder.setMessage(R.string.alert_need_internet_to_download);
            builder.setPositiveButton(R.string.dialog_settings_internet,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Lanzo un intent implícito que hace que se abra
                            // la pantalla de Configuración del móvil
                            // para que el usuario se conecte a Internet
                            // Internet
                            Intent intent = new Intent(
                                    Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    });

            builder.setNegativeButton(R.string.alert_dialog_cancel,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();


                        }
                    });

            builder.create();
            builder.show();
            return false;

        } else {

            // Si hay conexión...
            return true;
        }

    }*/


}
