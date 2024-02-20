package com.skyblue.easymvvm.retrofit;

import com.skyblue.easymvvm.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("PublicHolidays/2019/us")
    Call<List<PostModel>> getPosts();
}