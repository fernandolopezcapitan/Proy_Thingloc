
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.mensaje;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Mensaje {

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

    public Mensaje withCount(long count) {
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

    public Mensaje withNext(Object next) {
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

    public Mensaje withPrevious(Object previous) {
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

    public Mensaje withResults(List<Result> results) {
        this.results = results;
        return this;
    }

}
