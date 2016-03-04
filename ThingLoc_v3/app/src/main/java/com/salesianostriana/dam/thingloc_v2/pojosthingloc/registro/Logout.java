
package com.salesianostriana.dam.thingloc_v2.pojosthingloc.registro;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Logout {

    @SerializedName("success")
    @Expose
    private String success;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Logout() {
    }

    /**
     * 
     * @param success
     */
    public Logout(String success) {
        this.success = success;
    }

    /**
     * 
     * @return
     *     The success
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    public Logout withSuccess(String success) {
        this.success = success;
        return this;
    }

}
