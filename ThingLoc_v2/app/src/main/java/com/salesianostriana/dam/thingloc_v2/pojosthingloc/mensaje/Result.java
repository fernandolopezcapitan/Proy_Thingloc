
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("usuarioEmisor")
    @Expose
    private String usuarioEmisor;
    @SerializedName("usuarioReceptor")
    @Expose
    private String usuarioReceptor;
    @SerializedName("objeto")
    @Expose
    private String objeto;
    @SerializedName("comentario")
    @Expose
    private String comentario;
    @SerializedName("fechaMensaje")
    @Expose
    private String fechaMensaje;

    /**
     * 
     * @return
     *     The id
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(long id) {
        this.id = id;
    }

    public Result withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The usuarioEmisor
     */
    public String getUsuarioEmisor() {
        return usuarioEmisor;
    }

    /**
     * 
     * @param usuarioEmisor
     *     The usuarioEmisor
     */
    public void setUsuarioEmisor(String usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

    public Result withUsuarioEmisor(String usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
        return this;
    }

    /**
     * 
     * @return
     *     The usuarioReceptor
     */
    public String getUsuarioReceptor() {
        return usuarioReceptor;
    }

    /**
     * 
     * @param usuarioReceptor
     *     The usuarioReceptor
     */
    public void setUsuarioReceptor(String usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

    public Result withUsuarioReceptor(String usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
        return this;
    }

    /**
     * 
     * @return
     *     The objeto
     */
    public String getObjeto() {
        return objeto;
    }

    /**
     * 
     * @param objeto
     *     The objeto
     */
    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Result withObjeto(String objeto) {
        this.objeto = objeto;
        return this;
    }

    /**
     * 
     * @return
     *     The comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * 
     * @param comentario
     *     The comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Result withComentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    /**
     * 
     * @return
     *     The fechaMensaje
     */
    public String getFechaMensaje() {
        return fechaMensaje;
    }

    /**
     * 
     * @param fechaMensaje
     *     The fechaMensaje
     */
    public void setFechaMensaje(String fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public Result withFechaMensaje(String fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
        return this;
    }

}
