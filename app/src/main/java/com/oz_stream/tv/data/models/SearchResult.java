package com.oz_stream.tv.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private SearchResultActor searchResultActor = null;
    @SerializedName("message")
    @Expose
    private Message message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SearchResultActor getSearchResultActor() {
        return searchResultActor;
    }

    public void setSearchResultActor(SearchResultActor searchResultActor) {
        this.searchResultActor = searchResultActor;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
