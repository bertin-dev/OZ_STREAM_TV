package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot{

    @SerializedName("media_id")
    @Expose
    private String media_id = null;
    @SerializedName("actor_id")
    @Expose
    private String actor_id = null;
    @SerializedName("category_id")
    @Expose
    private String category_id = null;
    @SerializedName("gender_id")
    @Expose
    private String gender_id = null;
    @SerializedName("serie_id")
    @Expose
    private String serie_id = null;


    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getActor_id() {
        return actor_id;
    }

    public void setActor_id(String actor_id) {
        this.actor_id = actor_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getGender_id() {
        return gender_id;
    }

    public void setGender_id(String gender_id) {
        this.gender_id = gender_id;
    }

    public String getSerie_id() {
        return serie_id;
    }

    public void setSerie_id(String serie_id) {
        this.serie_id = serie_id;
    }
}
