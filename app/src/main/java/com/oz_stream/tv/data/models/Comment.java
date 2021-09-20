package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("isValidated")
    @Expose
    public String isValidated;
    @SerializedName("created_at")
    @Expose
    public String created_at;
    @SerializedName("updated_at")
    @Expose
    public String updated_at;
    @SerializedName("media_id")
    @Expose
    public String media_id;
    @SerializedName("user_id")
    @Expose
    public String user_id;
    @SerializedName("comment_reply_id")
    @Expose
    public String comment_reply_id;
    @SerializedName("comments_reply")
    @Expose
    public String comments_reply;


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
}
