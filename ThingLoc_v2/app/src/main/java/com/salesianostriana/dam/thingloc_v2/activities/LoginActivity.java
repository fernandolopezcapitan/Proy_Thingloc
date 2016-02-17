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
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.Login;
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

        prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        editor = prefs.edit();

        if(prefs.getString("Token ",null) != null){
            i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = user.getText().toString();
                passw = pass.getText().toString();

                // Petición 1 con Retrofit
                // REALIZA el proceso de autenticación del usuario y nos permite obtener el
                // código de sesión necesario para realizar otras.
                loadDataLogin(usuario, passw);

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


        final Call<Login> loginCall = Utiles.pedirServicioConInterceptores().loginUsuario(newLogin);
        loginCall.enqueue(new Callback<Login>() {

            @Override
            public void onResponse(Response<Login> response, Retrofit retrofit) {
                Login login = response.body();

                errorCode = response.code();
                Log.d("TOKEN", login.getKey());

                if ((errorCode == 200) || (errorCode == 201)){

                    editor.putString("Token ", login.getKey().toString());
                    editor.commit();
                    i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();

                }else{
                    Toast.makeText(LoginActivity.this, "Fallo de usuario o contraseña", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
