package com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro;

/**
 * Created by flopez on 18/02/2016.
 */
public class Token {
    String key;

    public Token(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Token{" +
                "key='" + key + '\'' +
                '}';
    }
}
