package com.example.localkamcom.Get_People;

import com.example.localkamcom.API.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class api_controler {
    private static final String url="http://192.168.43.94:8080/api/localkam/";
    private static api_controler clinetobject;
    private static Retrofit retrofit;

    api_controler()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized api_controler getInstance()
    {
        if (clinetobject==null)
            clinetobject=new api_controler();
        return clinetobject;
    }
    api getapi()
    {
        return retrofit.create(api.class);
    }
}
