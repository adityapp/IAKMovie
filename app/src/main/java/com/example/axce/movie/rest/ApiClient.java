package com.example.axce.movie.rest;

import com.example.axce.movie.model.ModelNew;
import com.example.axce.movie.model.ModelPopular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by AXCE on 07/12/2017.
 */

public interface ApiClient {
    public static final String KEY = "abdf48fd44cb74007720836ffe506983";

    @GET
    Call<ModelPopular> getPopular(@Url String url);

    @GET
    Call<ModelNew> getNew(@Url String url);

}
