package com.salesianostriana.dam.thingloc_v2.utiles;


import com.salesianostriana.dam.thingloc_v2.pojosthingloc.Login;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.Usuario;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by flopez on 17/02/2016.
 */
public interface Api {


    // Petición 1. Login.
    // REALIZA el proceso de autenticación del usuario y nos permite obtener el
    // código de sesión necesario para realizar otras.
    // ESPERA username y password en el cuerpo de la petición (JSON).
    // DEVUELVE devuelve un json con el token del usuario (key).
    @Headers({"Content-Type: application/json"})
    @POST("/rest-auth/login/")
    Call<Login> loginUsuario(@Body Login nuevaPeticion);


    // Petición 2. User (Me).
    // Identifica el usuario logueado a partir de un token, y obtiene sus datos.
    // ESPERA Token (obtenido en el login).
    // DEVUELVE un json con todos los datos del usuario menos el password.
    @GET("/rest-auth/user/")
    Call<Usuario> obtenerMisDatos(@Header("Token") String sessionToken/*, @Query("sessionToken") String sessionToken*/);

    // Petición 3. Logout.
    // REALIZA el proceso de logout, eliminando el session token.
    // ESPERA Token (obtenido en el login).
    // DEVUELVE un json con un mensaje de éxito.
    @POST("/1/logout")
    Call<Usuario> cerrarSesion(@Header("Token") String sessionToken);





}
