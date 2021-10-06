package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories implements Parcelable {

    @SerializedName("noSeries")
    @Expose
    private List<NoSery> noSeries = null;
    @SerializedName("series")
    @Expose
    private List<Series> series = null;


    protected Categories(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };

    public List<NoSery> getNoSeries() {
        return noSeries;
    }

    public void setNoSeries(List<NoSery> noSeries) {
        this.noSeries = noSeries;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public static Creator<Categories> getCREATOR() {
        return CREATOR;
    }
}

