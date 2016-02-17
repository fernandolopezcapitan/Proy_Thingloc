package com.salesianostriana.dam.thingloc_v2.pojosthingloc.Categorias;


/**
 * Created by flopez on 17/02/2016.
 */
public class Result {

    private Long id;
    private String nombre;

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
     */
    public Result(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
