package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root implements Parcelable {

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


    protected Root(Parcel in) {
        news = in.readParcelable(News.class.getClassLoader());
        gender = in.readParcelable(Gender.class.getClassLoader());
        category = in.createTypedArrayList(Category.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(news, flags);
        dest.writeParcelable(gender, flags);
        dest.writeTypedList(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Root> CREATOR = new Creator<Root>() {
        @Override
        public Root createFromParcel(Parcel in) {
            return new Root(in);
        }

        @Override
        public Root[] newArray(int size) {
            return new Root[size];
        }
    };

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
