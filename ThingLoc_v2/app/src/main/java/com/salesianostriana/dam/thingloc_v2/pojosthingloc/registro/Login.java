package com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro;

/**
 * Created by flopez on 18/02/2016.
 */
public class Login {
    String username,password;


    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Login() {

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



    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
