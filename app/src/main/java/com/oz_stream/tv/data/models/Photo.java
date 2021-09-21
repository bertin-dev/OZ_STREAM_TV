package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("link")
    @Expose
    private String link = null;
    @SerializedName("created_at")
    @Expose
    private String created_at = null;
    @SerializedName("updated_at")
    @Expose
    private String updated_at = null;
    @SerializedName("user_id")
    @Expose
    private String user_id = null;
    @SerializedName("created_by")
    @Expose
    private String created_by = null;
    @SerializedName("updated_by")
    @Expose
    private String updated_by = null;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
