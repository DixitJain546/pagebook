package com.example.pagebook.pagebook.network;

import com.example.pagebook.pagebook.model.AddPost;
import com.example.pagebook.pagebook.model.ResponsePost;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//apis for post
public interface IPostApis {

    @POST("/pb/post/addPost")
    Call<ResponsePost> addPost(@Body AddPost post);


}
