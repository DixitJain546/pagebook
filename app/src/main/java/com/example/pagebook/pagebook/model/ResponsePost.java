package com.example.pagebook.pagebook.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ResponsePost {

    @SerializedName("postId")
    private String postId;
    @SerializedName("postUrl")
    private String postUrl;

    @SerializedName("postCategory")
    private String postCategory;

    @SerializedName("postText")
    private String postText;

    @SerializedName("postType")
    private int postType;

    @SerializedName("userId")
    private String userId;

    @SerializedName("sharedPostId")
    private String sharedPostId;

    @SerializedName("timestamp")
    private Date timestamp;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public int getPostType() {
        return postType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setSharedPostId(String sharedPostId) {
        this.sharedPostId = sharedPostId;
    }

    public String getSharedPostId() {
        return sharedPostId;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return
                "AddPost{" +
                        "postUrl = '" + postUrl + '\'' +
                        ",postCategory = '" + postCategory + '\'' +
                        ",postText = '" + postText + '\'' +
                        ",postType = '" + postType + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",sharedPostId = '" + sharedPostId + '\'' +
                        ",timestamp = '" + timestamp + '\'' +
                        "}";
    }
}


