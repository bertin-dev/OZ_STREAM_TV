package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Access_token {

    @SerializedName("token_type")
    @Expose
    private String token_type = null;
    @SerializedName("expires_in")
    @Expose
    private String expires_in = null;
    @SerializedName("access_token")
    @Expose
    private String access_token = null;
    @SerializedName("refresh_token")
    @Expose
    private String refresh_token = null;


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message = null;







    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    


//statut 422
    /*@SerializedName("message")
    @Expose
    private String message = null;
    @SerializedName("errors")
    @Expose
    private Errors errors = null;



    private class Errors{

        @SerializedName("phone")
        @Expose
        private List<Phone> phone;

        @SerializedName("email")
        @Expose
        private List<Email> email;

        @SerializedName("password")
        @Expose
        private List<Password> password;

    }*/

}
