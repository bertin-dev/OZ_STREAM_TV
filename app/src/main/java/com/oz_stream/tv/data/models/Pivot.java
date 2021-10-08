package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot{


    @SerializedName("saison_id")
    @Expose
    private String saison_id = null;
    @SerializedName("gender_id")
    @Expose
    private String gender_id = null;
    @SerializedName("serie_id")
    @Expose
    private String serie_id = null;
    @SerializedName("media_id")
    @Expose
    private String media_id = null;
    @SerializedName("actor_id")
    @Expose
    private String actor_id = null;

    public String getSaison_id() {
        return saison_id;
    }

    public void setSaison_id(String saison_id) {
        this.saison_id = saison_id;
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
}
