package com.salesianostriana.dam.thingloc_v2.pojosthingloc.objeto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("recompensa")
    @Expose
    private Double recompensa;
    @SerializedName("perdido")
    @Expose
    private Boolean perdido;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("loadDataCategoria")
    @Expose
    private String categoria;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param nombre
     * @param id
     * @param categoria
     * @param recompensa
     * @param perdido
     * @param coordenadas
     * @param foto
     */
    public Result(Long id, String nombre, Double recompensa, Boolean perdido, String foto, String coordenadas, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.recompensa = recompensa;
        this.perdido = perdido;
        this.foto = foto;
        this.coordenadas = coordenadas;
        this.categoria = categoria;
    }

    /**
     *
     * @return
     * The id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     * The nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     * The recompensa
     */
    public Double getRecompensa() {
        return recompensa;
    }

    /**
     *
     * @param recompensa
     * The recompensa
     */
    public void setRecompensa(Double recompensa) {
        this.recompensa = recompensa;
    }

    /**
     *
     * @return
     * The perdido
     */
    public Boolean getPerdido() {
        return perdido;
    }

    /**
     *
     * @param perdido
     * The perdido
     */
    public void setPerdido(Boolean perdido) {
        this.perdido = perdido;
    }

    /**
     *
     * @return
     * The foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     * The foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     * The coordenadas
     */
    public String getCoordenadas() {
        return coordenadas;
    }

    /**
     *
     * @param coordenadas
     * The coordenadas
     */
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     *
     * @return
     * The loadDataCategoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     * The loadDataCategoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
