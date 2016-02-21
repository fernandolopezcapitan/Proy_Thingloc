package com.salesianostriana.dam.thingloc_v2.utiles;


import com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto.Objeto;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro.Login;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro.Logout;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro.Token;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.categoria.Categoria;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.categoria.CategoriaU;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje.Mensaje;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje.MensajeU;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto.ObjetoU;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios.Usuario;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios.UsuarioU;
import com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios.Usuarios;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by flopez on 17/02/2016.
 */
public interface Api {


    // Petición 1. Login.
    // REALIZA el proceso de autenticación del usuario y nos permite obtainOfPreferences el
    // código de sesión necesario para realizar otras.
    // ESPERA username y password en el cuerpo de la petición (JSON).
    // DEVUELVE devuelve un json con el token del usuario (key).
    // @Headers({"Content-Type: application/json"})
    @POST("/rest-auth/login/")
    Call<Token> loginUsuario(@Body Login nuevaPeticion);


    // Petición 2. User (Me).
    // Identifica el usuario logueado a partir de un token, y obtiene sus datos.
    // ESPERA Token (obtenido en el login).
    // DEVUELVE un json con todos los datos del usuario menos el password.
    @GET("/rest-auth/user/")
    Call<Usuario> obtenerMisDatos();

    // Petición 3. Logout.
    // REALIZA el proceso de logout, eliminando el session token.
    // ESPERA Token (obtenido en el login).
    // DEVUELVE un json con un mensaje de éxito.
    @POST("/rest-auth/logout/")
    Call<Logout> cerrarSesion();


    // Petición 4. Registro.
    // REALIZA el proceso de registro.
    // ESPERA username, password1, password2, email, first_name, last_name.
    // DEVUELVE un json con el token generado.
    @POST("/rest-auth/registration/")
    Call<Usuario> registerUsuario(@Body Usuario usuario);


    // Petición 5. Obtener todos los objetos.
    // DEVUELVE el listado con todos los objetos.
    // ESPERA Token (obtenido en el login).
    // DEVUELVE un json con todos los datos de todos los objetos.
    @GET("/api/objeto/")
    Call<Objeto> obtenerObjetos(@Query("search")String nombre);


    // Petición 6. Obtiene los datos de un objeto concreto
    // DEVUELVE los datos de un objeto.
    // ESPERA objectId, como parámetro en la URL. Token, obtenido en el login
    // DEVUELVE un json con todos los datos de un objeto.
    @GET("/api/objeto/{id}")
    Call<ObjetoU> objectDeUsuario(@Path("id") String id);

    // Peticion para obtainOfPreferences usuarios
    @GET("/api/users/")
    Call<Usuarios> obtenerUsuarios();

    // Petición para obtainOfPreferences un usuario
    @GET("/api/users/{id}")
    Call<UsuarioU> obtenerUsuario(@Path("id") String id);

    // Petición búsqueda usuario por id
    @GET("/api/users/")
    Call<Usuarios> obtenerUsuarioPorId(@Query("search")String username);


    // Petición 7. Registrar un objeto
    // REGISTRA un nuevo objeto en la base de datos.
    // ESPERA Token (obtenido en el login). Todos los datos del objeto, incluidas imágenes.
    // DEVUELVE un json con todos los datos de todos los objetos.
    @Multipart
    @POST("/api/objeto/")
    Call<String> upload( @Part("myfile\"; filename=\"image.png\" ") RequestBody file,
                         @Part("file_name") RequestBody filename,
                         @Part("description") RequestBody description);


    // Petición 8. Obtener todas las categorias.
    // DEVUELVE un listado con todas las categorias
    // ESPERA Token (obtenido en el login).
    // DEVUELVE un json con todas las categorias.
    @GET("/api/categoria/")
    Call<Categoria> obtenerCategoria();

    @GET("/api/categoria/{id}")
    Call<CategoriaU> obtenerCategoriaUser(@Path("id") String id);


    // Petición 9. Obtener todos los mensajes de un objeto.
    // DEVUELVE el listado con todos los mensajes de chat de un objeto concreto.
    // ESPERA Token, obtenido en el login. Id del objeto, proporcionado como valor URL-encoded.
    // DEVUELVE un json con todos los mensajes de un objeto concreto.
    @GET("/api/mensaje/")
    Call<Mensaje> obtenerMsgObjet(@Query("search")String id);


    // Petición 10. Almacenar un comentario.
    // INSERTA un mensaje sobre un objeto.
    // ESPERA Token, obtenido en el login, Y EL OBJETO EN JSON.
    // DEVUELVE un json con el objeto y el nuevo mensaje.
    @POST("/api/mensaje/")
    Call<MensajeU> subirMensajeObject(@Body MensajeU mensaje);
}
