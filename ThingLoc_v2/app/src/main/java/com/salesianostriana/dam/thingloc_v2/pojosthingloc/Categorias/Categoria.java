package com.salesianostriana.dam.thingloc_v2.pojosthingloc.Categorias;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by flopez on 17/02/2016.
 */
public class Categoria {

    private Long count;
    private Object next;
    private Object previous;
    private List<Result> results = new ArrayList<Result>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Categoria() {
    }

    /**
     *
     * @param results
     * @param previous
     * @param count
     * @param next
     */
    public Categoria(Long count, Object next, Object previous, List<Result> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    /**
     *
     * @return
     * The count
     */
    public Long getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     *
     * @return
     * The next
     */
    public Object getNext() {
        return next;
    }

    /**
     *
     * @param next
     * The next
     */
    public void setNext(Object next) {
        this.next = next;
    }

    /**
     *
     * @return
     * The previous
     */
    public Object getPrevious() {
        return previous;
    }

    /**
     *
     * @param previous
     * The previous
     */
    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }
}
