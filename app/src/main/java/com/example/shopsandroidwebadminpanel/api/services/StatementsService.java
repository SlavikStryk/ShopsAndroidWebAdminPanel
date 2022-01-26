package com.example.shopsandroidwebadminpanel.api.services;

import com.example.shopsandroidwebadminpanel.api.StatementsAPI;
import com.example.shopsandroidwebadminpanel.constans.Urls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatementsService {
    private static StatementsService mInstance;
    private static final String BASE_URL = Urls.BASE;
    private Retrofit mRetrofit;

    private StatementsService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static StatementsService getInstance() {
        if (mInstance == null) {
            mInstance = new StatementsService();
        }
        return mInstance;
    }

    public StatementsAPI getStatementsApi() {
        return mRetrofit.create(StatementsAPI.class);
    }
}

