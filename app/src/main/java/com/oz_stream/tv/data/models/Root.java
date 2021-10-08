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
    @SerializedName("frees")
    @Expose
    private Frees frees = null;
    @SerializedName("genders")
    @Expose
    private List<Gender> genders = null;
    @SerializedName("categories")
    @Expose
    private Categories categories = null;
    @SerializedName("playlists")
    @Expose
    private Playlist playlists = null;

    protected Root(Parcel in) {
        news = in.readParcelable(News.class.getClassLoader());
        genders = in.createTypedArrayList(Gender.CREATOR);
        categories = in.readParcelable(Categories.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(news, flags);
        dest.writeTypedList(genders);
        dest.writeParcelable(categories, flags);
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

    public Frees getFrees() {
        return frees;
    }

    public void setFrees(Frees frees) {
        this.frees = frees;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Playlist getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
    }

    public static Creator<Root> getCREATOR() {
        return CREATOR;
    }
}
