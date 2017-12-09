package com.example.axce.movie.rest;

import com.example.axce.movie.model.ModelMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by AXCE on 07/12/2017.
 */

public interface ApiClient {
    public static final String KEY = "abdf48fd44cb74007720836ffe506983";

    @GET
    Call<ModelMovie> getMovie(@Url String url);

}
