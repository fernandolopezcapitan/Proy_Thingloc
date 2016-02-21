package com.salesianostriana.dam.thingloc_v2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.salesianostriana.dam.thingloc_v2.R;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro.Login;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro.Token;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios.Usuario;
import com.salesianostriana.dam.thingloc_v2.utiles.Utiles;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button btn_entrar, btn_registrar;
    SharedPreferences prefs;
    SharedPreferences.Editor editor = null;
    Intent i;
    int errorCode;
    String usuario,passw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.usuario);
        pass = (EditText) findViewById(R.id.pass);
        btn_entrar = (Button) findViewById(R.id.btn_entrar);
        btn_registrar = (Button) findViewById(R.id.btn_register);

        /*prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = prefs.edit();

        if(prefs.getString("Token ",null) != null){
            i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }*/

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = user.getText().toString();
                passw = pass.getText().toString();

                if (usuario.equalsIgnoreCase("") || passw.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Falta usuario o contraseña", Toast.LENGTH_LONG).show();
                } else {

                    // Petición 1. Login. con Retrofit
                    // REALIZA el proceso de autenticación del usuario y nos permite obtainOfPreferences el
                    // código de sesión necesario para realizar otras.
                    loadDataLogin(usuario, passw);
                }


            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ir a registro

            }
        });
    }

    private void loadDataLogin(final String user, final String password){
        Login newLogin = new Login();
        newLogin.setUsername(usuario);
        newLogin.setPassword(passw);


        final Call<Token> loginCall = Utiles.pedirServicioConInterceptores().loginUsuario(newLogin);
        loginCall.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(Response<Token> response, Retrofit retrofit) {

                // Obtenidos todos los datos y guardados en Token
                Token login = response.body();
                errorCode = response.code();

                Utiles.saveInPreferences(LoginActivity.this, "token", "Token " + login.getKey());

                Log.i("TOKEN", String.valueOf(response.body()));
                Log.i("CÓDIGO DE ERROR", String.valueOf(errorCode));

                if ((errorCode == 200) || (errorCode == 201)) {

                    // Petición 2. User (Me). Con Retrofit
                    // Identifica el usuario logueado a partir de un token, y obtiene sus datos.
                    // ESPERA Token (obtenido en el login).
                    loadDataUser();

                    /*editor.putString("Token ", login.getKey().toString());
                    editor.commit();
                    i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();*/

                } else {
                    Toast.makeText(LoginActivity.this, "No es posible entrar", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void loadDataUser(){

        Call<Usuario> userCall = Utiles.serviceConInterceptorsAuth(getBaseContext()).obtenerMisDatos();

        userCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Response<Usuario> response, Retrofit retrofit) {

                Usuario result = response.body();
                errorCode = response.code();

                Log.i("USUARIO", String.valueOf(result));
                Log.i("CÓDIGO DE ERROR", String.valueOf(errorCode));

                if ((errorCode == 200)|| (errorCode == 201)) {

                    Log.i("USERNAME", result.getUsername());

                    Utiles.saveInPreferences(getBaseContext(), "username", result.getUsername());
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();


                } else {
                    Toast.makeText(LoginActivity.this, "No es posible entrar (toekn)", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Throwable t) {

            }


        });
    }
}
