
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjetoU {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("recompensa")
    @Expose
    private long recompensa;
    @SerializedName("perdido")
    @Expose
    private boolean perdido;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("loadDataCategoria")
    @Expose
    private Integer categoria;
    @SerializedName("usuario")
    @Expose
    private Integer usuario;

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
    public void setId(int id) {
        this.id = id;
    }

    public ObjetoU withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre
     *     The nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ObjetoU withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     * 
     * @return
     *     The recompensa
     */
    public long getRecompensa() {
        return recompensa;
    }

    /**
     * 
     * @param recompensa
     *     The recompensa
     */
    public void setRecompensa(long recompensa) {
        this.recompensa = recompensa;
    }

    public ObjetoU withRecompensa(long recompensa) {
        this.recompensa = recompensa;
        return this;
    }

    /**
     * 
     * @return
     *     The perdido
     */
    public boolean isPerdido() {
        return perdido;
    }

    /**
     * 
     * @param perdido
     *     The perdido
     */
    public void setPerdido(boolean perdido) {
        this.perdido = perdido;
    }

    public ObjetoU withPerdido(boolean perdido) {
        this.perdido = perdido;
        return this;
    }

    /**
     * 
     * @return
     *     The foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * 
     * @param foto
     *     The foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ObjetoU withFoto(String foto) {
        this.foto = foto;
        return this;
    }

    /**
     * 
     * @return
     *     The coordenadas
     */
    public String getCoordenadas() {
        return coordenadas;
    }

    /**
     * 
     * @param coordenadas
     *     The coordenadas
     */
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public ObjetoU withCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
        return this;
    }

    /**
     * 
     * @return
     *     The loadDataCategoria
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * 
     * @param categoria
     *     The loadDataCategoria
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public ObjetoU withCategoria(int categoria) {
        this.categoria = categoria;
        return this;
    }

    /**
     * 
     * @return
     *     The usuario
     */
    public int getUsuario() {
        return usuario;
    }

    /**
     * 
     * @param usuario
     *     The usuario
     */
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public ObjetoU withUsuario(int usuario) {
        this.usuario = usuario;
        return this;
    }

}
