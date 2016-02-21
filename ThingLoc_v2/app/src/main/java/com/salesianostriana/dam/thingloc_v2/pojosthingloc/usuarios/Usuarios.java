
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.usuarios;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {

    @SerializedName("count")
    @Expose
    private long count;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Usuarios() {
    }

    /**
     * 
     * @param results
     * @param previous
     * @param count
     * @param next
     */
    public Usuarios(long count, Object next, Object previous, List<Result> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    /**
     * 
     * @return
     *     The count
     */
    public long getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(long count) {
        this.count = count;
    }

    public Usuarios withCount(long count) {
        this.count = count;
        return this;
    }

    /**
     * 
     * @return
     *     The next
     */
    public Object getNext() {
        return next;
    }

    /**
     * 
     * @param next
     *     The next
     */
    public void setNext(Object next) {
        this.next = next;
    }

    public Usuarios withNext(Object next) {
        this.next = next;
        return this;
    }

    /**
     * 
     * @return
     *     The previous
     */
    public Object getPrevious() {
        return previous;
    }

    /**
     * 
     * @param previous
     *     The previous
     */
    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public Usuarios withPrevious(Object previous) {
        this.previous = previous;
        return this;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Usuarios withResults(List<Result> results) {
        this.results = results;
        return this;
    }

}
