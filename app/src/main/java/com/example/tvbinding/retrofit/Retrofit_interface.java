package com.example.tvbinding.retrofit;

import com.example.tvbinding.models.Channel_List_model;
import com.example.tvbinding.models.Channel_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Retrofit_interface {

    @GET("67f54db98561e97a50fb2e85")
    Call<Channel_List_model> getCategory(@Header("X-Master-Key") String masterKey);

    @GET("67fd1acd8a456b79668923a8")
    Call<Channel_List_model> getRadioChannels(@Header("X-Master-Key") String masterKey);

}
