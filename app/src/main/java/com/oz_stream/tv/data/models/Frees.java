package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Frees implements Parcelable {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("datas")
    @Expose
    private List<Data> datas = null;


    protected Frees(Parcel in) {
        total = in.readInt();
        datas = in.createTypedArrayList(Data.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
        dest.writeTypedList(datas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Frees> CREATOR = new Creator<Frees>() {
        @Override
        public Frees createFromParcel(Parcel in) {
            return new Frees(in);
        }

        @Override
        public Frees[] newArray(int size) {
            return new Frees[size];
        }
    };

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static Creator<Frees> getCREATOR() {
        return CREATOR;
    }
}
