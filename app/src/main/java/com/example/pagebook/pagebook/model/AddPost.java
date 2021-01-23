package com.example.pagebook.pagebook.model;

import com.google.gson.annotations.SerializedName;

public class AddPost{

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
	private int timestamp;

	public void setPostUrl(String postUrl){
		this.postUrl = postUrl;
	}

	public String getPostUrl(){
		return postUrl;
	}

	public void setPostCategory(String postCategory){
		this.postCategory = postCategory;
	}

	public String getPostCategory(){
		return postCategory;
	}

	public void setPostText(String postText){
		this.postText = postText;
	}

	public String getPostText(){
		return postText;
	}

	public void setPostType(int postType){
		this.postType = postType;
	}

	public int getPostType(){
		return postType;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setSharedPostId(String sharedPostId){
		this.sharedPostId = sharedPostId;
	}

	public String getSharedPostId(){
		return sharedPostId;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
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