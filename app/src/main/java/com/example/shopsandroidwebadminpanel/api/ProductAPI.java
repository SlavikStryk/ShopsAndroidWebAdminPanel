package com.example.shopsandroidwebadminpanel.api;

import com.example.shopsandroidwebadminpanel.dto.ProductAddDTO;
import com.example.shopsandroidwebadminpanel.dto.ProductDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductAPI {
    @GET("/api/products/all")
    public Call<List<ProductDTO>> all();

    @GET("/api/Products/get/{id}")
    public Call<ProductDTO> getWithId(@Path("id") Long id);

    @GET("/api/products/getname/{name}")
    public Call<ProductDTO> getWithName(@Path("name") String name);

    @POST("/api/Products/post")
    public Call<ProductAddDTO> post(@Body ProductAddDTO productAddDTO);

    @DELETE("/api/products/delete/{name}")
    public Call<Void> delete(@Path("name") String name);
}