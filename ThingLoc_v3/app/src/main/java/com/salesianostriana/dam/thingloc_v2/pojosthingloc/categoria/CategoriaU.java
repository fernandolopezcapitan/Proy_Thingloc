
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.categoria;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CategoriaU {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;

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

    public CategoriaU withId(long id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public CategoriaU withName(String name) {
        this.name = name;
        return this;
    }

}
