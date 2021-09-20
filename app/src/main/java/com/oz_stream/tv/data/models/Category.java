package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("created_by")
    @Expose
    private String created_by;
    @SerializedName("updated_by")
    @Expose
    private String updated_by;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    protected Category(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        created_by = in.readString();
        updated_by = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(created_by);
        dest.writeString(updated_by);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}

