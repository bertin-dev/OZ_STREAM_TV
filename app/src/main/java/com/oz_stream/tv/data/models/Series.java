package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Series implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title = null;
    @SerializedName("description")
    @Expose
    private String description = null;
    @SerializedName("created_at")
    @Expose
    private String created_at = null;
    @SerializedName("updated_at")
    @Expose
    private String updated_at = null;
    @SerializedName("user_id")
    @Expose
    private String user_id = null;
    @SerializedName("updated_by")
    @Expose
    private String updated_by = null;
    @SerializedName("created_by")
    @Expose
    private String created_by = null;
    @SerializedName("pivot")
    @Expose
    private Pivot pivot = null;
    @SerializedName("diffuser")
    @Expose
    private Diffuser diffuser = null;
    @SerializedName("photo")
    @Expose
    private Photo photo = null;
    @SerializedName("bande_anonce")
    @Expose
    private BandeAnonce bande_anonce = null;
    @SerializedName("genders")
    @Expose
    private List<Gender> genders = null;
    @SerializedName("saisons_with")
    @Expose
    private List<SaisonsWith> saisons_with = null;


    protected Series(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        user_id = in.readString();
        updated_by = in.readString();
        created_by = in.readString();
        diffuser = in.readParcelable(Diffuser.class.getClassLoader());
        photo = in.readParcelable(Photo.class.getClassLoader());
        bande_anonce = in.readParcelable(BandeAnonce.class.getClassLoader());
        genders = in.createTypedArrayList(Gender.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(user_id);
        dest.writeString(updated_by);
        dest.writeString(created_by);
        dest.writeParcelable(diffuser, flags);
        dest.writeParcelable(photo, flags);
        dest.writeParcelable(bande_anonce, flags);
        dest.writeTypedList(genders);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    public Diffuser getDiffuser() {
        return diffuser;
    }

    public void setDiffuser(Diffuser diffuser) {
        this.diffuser = diffuser;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public BandeAnonce getBande_anonce() {
        return bande_anonce;
    }

    public void setBande_anonce(BandeAnonce bande_anonce) {
        this.bande_anonce = bande_anonce;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public List<SaisonsWith> getSaisons_with() {
        return saisons_with;
    }

    public void setSaisons_with(List<SaisonsWith> saisons_with) {
        this.saisons_with = saisons_with;
    }

    public static Creator<Series> getCREATOR() {
        return CREATOR;
    }
}
