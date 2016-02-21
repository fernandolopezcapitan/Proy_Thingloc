
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MensajeU {

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
     * No args constructor for use in serialization
     * 
     */
    public MensajeU() {
    }

    /**
     * 
     * @param id
     * @param objeto
     * @param usuarioEmisor
     * @param fechaMensaje
     * @param usuarioReceptor
     * @param comentario
     */
    public MensajeU(long id, String usuarioEmisor, String usuarioReceptor, String objeto, String comentario, String fechaMensaje) {
        this.id = id;
        this.usuarioEmisor = usuarioEmisor;
        this.usuarioReceptor = usuarioReceptor;
        this.objeto = objeto;
        this.comentario = comentario;
        this.fechaMensaje = fechaMensaje;
    }

    public MensajeU( String usuarioEmisor, String usuarioReceptor, String objeto, String comentario, String fechaMensaje) {
        this.usuarioEmisor = usuarioEmisor;
        this.usuarioReceptor = usuarioReceptor;
        this.objeto = objeto;
        this.comentario = comentario;
        this.fechaMensaje = fechaMensaje;
    }

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

    public MensajeU withId(long id) {
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

    public MensajeU withUsuarioEmisor(String usuarioEmisor) {
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

    public MensajeU withUsuarioReceptor(String usuarioReceptor) {
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

    public MensajeU withObjeto(String objeto) {
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

    public MensajeU withComentario(String comentario) {
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

    public MensajeU withFechaMensaje(String fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
        return this;
    }

    @Override
    public String toString() {
        return "MensajeU{" +
                "id=" + id +
                ", usuarioEmisor='" + usuarioEmisor + '\'' +
                ", usuarioReceptor='" + usuarioReceptor + '\'' +
                ", objeto='" + objeto + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fechaMensaje='" + fechaMensaje + '\'' +
                '}';
    }
}
