package com.salesianostriana.dam.thingloc_v2.pojosthingloc;

/**
 * Created by flopez on 17/02/2016.
 */
public class Login {

    private String username;
    private String password;
    private String key;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login(String username, String password, String key) {
        this.username = username;
        this.password = password;
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
