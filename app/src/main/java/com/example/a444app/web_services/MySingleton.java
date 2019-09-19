package com.example.a444app.web_services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MySingleton {

    
    //todo add base url
    public static final String BASE_URL = "http://..../APIs/";

    private static ApiMethod apiMethod= null;
    
    private MySingleton() {
    }

    public static ApiMethod getApi() {
        if (apiMethod == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES).connectTimeout(1, TimeUnit.MINUTES).build();

            //ceate Gson builder
            Gson gson = new GsonBuilder().setLenient().create();

            //build Retrofit
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();

            apiMethod = retrofit.create(ApiMethod.class);
        }//end if

        return apiMethod;
    }//end getApi()
    
}//end class
