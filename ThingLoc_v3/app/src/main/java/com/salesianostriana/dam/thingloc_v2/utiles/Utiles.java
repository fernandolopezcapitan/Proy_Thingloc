package com.salesianostriana.dam.thingloc_v2.utiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by flopez on 17/02/2016.
 */
public class Utiles {


    // Declaración de variables de preferencias estáticas
    public static String urlbase ="http://172.27.60.8:8000";//colegio
    //public static String urlbase ="http://192.168.1.34:8000/";//maquinacasa
    public static SharedPreferences sp;
    public static String ruta="";


    private Api pedirServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.27.60.8:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        return service;
    }
    public static Api pedirServicioConInterceptores() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

                //Aqui es donde añade las cabeceras anteriores para poder acceder al servicio
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        //.addHeader("X-Parse-Application-Id", "Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy")
                        //.addHeader("X-Parse-REST-API-Key","4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR")
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlbase)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Api service = retrofit.create(Api.class);

        return service;
    }




    public static Api serviceConInterceptorsAuth(final Context c) {

        Interceptor interceptor = new Interceptor() {@Override
                                                     public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", Utiles.obtainOfPreferences(c, "token"))
                    .build();

            return chain.proceed(newRequest);

        }

        };

        // OkHttpClient es el encargado dde realizar las peticiones HTTP.
        OkHttpClient client = new OkHttpClient();

        // Al cliente se le añade el interceptor.
        client.interceptors().add(interceptor);

        Log.i("TokenInterce", Utiles.obtainOfPreferences(c, "token"));

        // Cuando construimos Retrofit le decimos que el cliente sea el que acabamos de crea.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlbase)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Api service = retrofit.create(Api.class);

        return service;
    }

    public static Api serviceConInterceptorsContent(final Context c) {

        Interceptor interceptor = new Interceptor() {@Override
                                                     public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", Utiles.obtainOfPreferences(c, "token"))
                    .addHeader("Content-Type", "application/json")
                    .build();

            return chain.proceed(newRequest);

        }

        };

        // OkHttpClient es el encargado dde realizar las peticiones HTTP.
        OkHttpClient client = new OkHttpClient();

        // Al cliente se le añade el interceptor.
        client.interceptors().add(interceptor);

        Log.i("TokenInterce", Utiles.obtainOfPreferences(c, "token"));

        // Cuando construimos Retrofit le decimos que el cliente sea el que acabamos de crea.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlbase)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Api service = retrofit.create(Api.class);

        return service;
    }



    public static String codificarEnUtf8(String datoAcodificar) {
        String encodedData = "";

        try {
            encodedData = URLEncoder.encode(datoAcodificar, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return encodedData;
    }


    // Este método pide conexión a internet para continuar la app según el activity
    // En caso de no tenerla, y si quieres que abra los ajustes de conexión, ver clase LoginActivity
    //Comprueba internet
    public static boolean isOnline(Context con) {
        ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }

    public static String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ss";

    public static String obtenerFechaSistema() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat(Utiles.FORMATO_FECHA);
        return format1.format(date);
    }


    public static boolean saveInPreferences(Context mContext, String titulo, String texto){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();

        mEdit1.putString(titulo, texto);

        return mEdit1.commit();
    }

    public static String obtainOfPreferences(Context mContext, String titulo){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        String user = sp.getString(titulo, "0");
        return user;
    }

    public static void cleanPreferences(Context mContext){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.clear();
        mEdit1.commit();

    }



}
