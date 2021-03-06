package com.dam.salesianostriana.di.thinglocv1.pojoschema.pojoValoracion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flopez on 17/12/2015.
 */
public class Valoracion {
    @SerializedName("results")
    @Expose
    private List<ResultValoracion> results = new ArrayList<ResultValoracion>();

    /**
     * No args constructor for use in serialization
     */
    public Valoracion() {
    }

    /**
     * @param results
     */
    public Valoracion(List<ResultValoracion> results) {
        this.results = results;
    }

    /**
     * @return The results
     */
    public List<ResultValoracion> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<ResultValoracion> results) {
        this.results = results;
    }
}
