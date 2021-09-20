package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gender implements Parcelable  {

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


    protected Gender(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        created_by = in.readString();
        updated_by = in.readString();
    }

    public static final Creator<Gender> CREATOR = new Creator<Gender>() {
        @Override
        public Gender createFromParcel(Parcel in) {
            return new Gender(in);
        }

        @Override
        public Gender[] newArray(int size) {
            return new Gender[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(created_by);
        parcel.writeString(updated_by);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    public static Creator<Gender> getCREATOR() {
        return CREATOR;
    }
}
