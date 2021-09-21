package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {

    @SerializedName("news")
    @Expose
    private News news = null;
    @SerializedName("previews")
    @Expose
    private Previews previews = null;
    @SerializedName("populars")
    @Expose
    private Populars populars = null;
    @SerializedName("willbePostes")
    @Expose
    private WillbePostes willbePostes = null;
    @SerializedName("frees")
    @Expose
    private Frees frees = null;
    @SerializedName("gender")
    @Expose
    private Gender gender = null;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("playlist")
    @Expose
    private List<Playlist> playlist = null;


    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Previews getPreviews() {
        return previews;
    }

    public void setPreviews(Previews previews) {
        this.previews = previews;
    }

    public Populars getPopulars() {
        return populars;
    }

    public void setPopulars(Populars populars) {
        this.populars = populars;
    }

    public WillbePostes getWillbePostes() {
        return willbePostes;
    }

    public void setWillbePostes(WillbePostes willbePostes) {
        this.willbePostes = willbePostes;
    }

    public Frees getFrees() {
        return frees;
    }

    public void setFrees(Frees frees) {
        this.frees = frees;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }
}
