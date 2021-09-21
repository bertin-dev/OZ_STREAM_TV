package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

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


    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private String getIsValidated() {
        return isValidated;
    }

    private void setIsValidated(String isValidated) {
        this.isValidated = isValidated;
    }

    private String getCreated_at() {
        return created_at;
    }

    private void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    private String getUpdated_at() {
        return updated_at;
    }

    private void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    private String getMedia_id() {
        return media_id;
    }

    private void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    private String getUser_id() {
        return user_id;
    }

    private void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String getComment_reply_id() {
        return comment_reply_id;
    }

    private void setComment_reply_id(String comment_reply_id) {
        this.comment_reply_id = comment_reply_id;
    }

    private String getComments_reply() {
        return comments_reply;
    }

    private void setComments_reply(String comments_reply) {
        this.comments_reply = comments_reply;
    }
}
