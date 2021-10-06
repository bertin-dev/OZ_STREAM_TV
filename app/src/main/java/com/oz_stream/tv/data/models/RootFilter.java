package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootFilter implements Parcelable {

    @SerializedName("current_page")
    @Expose
    private int current_page;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;


    protected RootFilter(Parcel in) {
        current_page = in.readInt();
        data = in.createTypedArrayList(Data.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(current_page);
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RootFilter> CREATOR = new Creator<RootFilter>() {
        @Override
        public RootFilter createFromParcel(Parcel in) {
            return new RootFilter(in);
        }

        @Override
        public RootFilter[] newArray(int size) {
            return new RootFilter[size];
        }
    };

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static Creator<RootFilter> getCREATOR() {
        return CREATOR;
    }
}
