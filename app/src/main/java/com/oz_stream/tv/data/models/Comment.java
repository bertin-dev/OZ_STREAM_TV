package com.oz_stream.tv.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("content")
    @Expose
    private String content = null;
    @SerializedName("isValidated")
    @Expose
    private String isValidated = null;
    @SerializedName("created_at")
    @Expose
    private String created_at = null;
    @SerializedName("updated_at")
    @Expose
    private String updated_at = null;
    @SerializedName("media_id")
    @Expose
    private String media_id = null;
    @SerializedName("user_id")
    @Expose
    private String user_id = null;
    @SerializedName("comment_reply_id")
    @Expose
    private String comment_reply_id = null;
    @SerializedName("comments_reply")
    @Expose
    private String comments_reply = null;


    protected Comment(Parcel in) {
        id = in.readInt();
        content = in.readString();
        isValidated = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        media_id = in.readString();
        user_id = in.readString();
        comment_reply_id = in.readString();
        comments_reply = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(content);
        dest.writeString(isValidated);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(media_id);
        dest.writeString(user_id);
        dest.writeString(comment_reply_id);
        dest.writeString(comments_reply);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(String isValidated) {
        this.isValidated = isValidated;
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

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_reply_id() {
        return comment_reply_id;
    }

    public void setComment_reply_id(String comment_reply_id) {
        this.comment_reply_id = comment_reply_id;
    }

    public String getComments_reply() {
        return comments_reply;
    }

    public void setComments_reply(String comments_reply) {
        this.comments_reply = comments_reply;
    }

    public static Creator<Comment> getCREATOR() {
        return CREATOR;
    }
}
