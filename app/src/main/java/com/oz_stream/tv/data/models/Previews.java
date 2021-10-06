package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Previews implements Parcelable {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("datas")
    @Expose
    private List<Data> datas = null;


    protected Previews(Parcel in) {
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

    public static final Creator<Previews> CREATOR = new Creator<Previews>() {
        @Override
        public Previews createFromParcel(Parcel in) {
            return new Previews(in);
        }

        @Override
        public Previews[] newArray(int size) {
            return new Previews[size];
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

    public static Creator<Previews> getCREATOR() {
        return CREATOR;
    }
}
