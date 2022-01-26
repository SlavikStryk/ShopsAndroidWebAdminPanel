package com.example.shopsandroidwebadminpanel.api;

import com.example.shopsandroidwebadminpanel.dto.StatementsDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StatementsAPI {
    @POST("/api/Statement/post")
    public Call<StatementsDTO> post(@Body StatementsDTO statementsDTO);

    @GET("/api/Statement/all")
    public Call<StatementsDTO> all();

    @DELETE("/api/Statement/delete")
    public Call<StatementsDTO> delete(@Body StatementsDTO statementsDTO);
}
