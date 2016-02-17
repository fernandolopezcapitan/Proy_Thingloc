package com.salesianostriana.dam.thingloc_v2.utiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
    public static Api pedirServicioConInterceptores() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

                //Aqui es donde añade las cabeceras anteriores para poder acceder al servicio
                Request newRequest = chain.request().newBuilder()
                        //.addHeader("X-Parse-Application-Id", "Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy")
                        //.addHeader("X-Parse-REST-API-Key","4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR")
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://172.60.0.8:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Api service = retrofit.create(Api.class);

        return service;
    }

    public static Api serviceConInterceptors2(final Context c) {

        Interceptor interceptor = new Interceptor() {@Override
                                                     public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {

            //Cogemos la cadena y le añadimos dos cabeceras, luego se devuelve la petición
            // Es más comodo hacerlo así para no tener que copiar tantisimas veces y pegar
            // el @Header del API
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", Utiles.obtenerSessionToken(c,"token"))
                    .build();

            return chain.proceed(newRequest);
        }

        };

        // OkHttpClient es el encargado dde realizar las peticiones HTTP.
        OkHttpClient client = new OkHttpClient();

        // Al cliente se le añade el interceptor.
        client.interceptors().add(interceptor);

        // Cuando construimos Retrofit le decimos que el cliente sea el que acabamos de crea.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.27.0.70:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        Api service = retrofit.create(Api.class);

        return service;
    }

    private Api pedirServicio(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://172.60.0.8:8000/")
                .addConverterFactory(GsonConverterFactory.create())
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
    public static boolean checkInternet(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null || !i.isConnected() || !i.isAvailable()) {

            return false;

        } else {

            return true;
        }
    }

    public static String FORMATO_FECHA = "yyyy-MM-dd'T'HH:mm:ss";

    public static String obtenerFechaSistema() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat(Utiles.FORMATO_FECHA);
        return format1.format(date);
    }

    public static String obtenerSessionToken(Context c, String token){


        return null;
    }

}
