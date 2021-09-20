package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThisUserStatVideo {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("downloaded")
    @Expose
    private String downloaded;
    @SerializedName("isliked")
    @Expose
    private String isliked;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("alreadyBought")
    @Expose
    private String alreadyBought;
    @SerializedName("bandeLooked")
    @Expose
    private String bandeLooked;
    @SerializedName("streamLooked")
    @Expose
    private String streamLooked;
    @SerializedName("timeLineLookBande")
    @Expose
    private String timeLineLookBande;
    @SerializedName("timeLineLookStream")
    @Expose
    private String timeLineLookStream;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("media_id")
    @Expose
    private String media_id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("created_by")
    @Expose
    private String created_by;
    @SerializedName("updated_by")
    @Expose
    private String updated_by;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(String downloaded) {
        this.downloaded = downloaded;
    }

    public String getIsliked() {
        return isliked;
    }

    public void setIsliked(String isliked) {
        this.isliked = isliked;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getAlreadyBought() {
        return alreadyBought;
    }

    public void setAlreadyBought(String alreadyBought) {
        this.alreadyBought = alreadyBought;
    }

    public String getBandeLooked() {
        return bandeLooked;
    }

    public void setBandeLooked(String bandeLooked) {
        this.bandeLooked = bandeLooked;
    }

    public String getStreamLooked() {
        return streamLooked;
    }

    public void setStreamLooked(String streamLooked) {
        this.streamLooked = streamLooked;
    }

    public String getTimeLineLookBande() {
        return timeLineLookBande;
    }

    public void setTimeLineLookBande(String timeLineLookBande) {
        this.timeLineLookBande = timeLineLookBande;
    }

    public String getTimeLineLookStream() {
        return timeLineLookStream;
    }

    public void setTimeLineLookStream(String timeLineLookStream) {
        this.timeLineLookStream = timeLineLookStream;
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

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
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
