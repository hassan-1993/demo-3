package com.example.inmobile.core.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hassan on 3/27/2019.
 **/
public class ApiConfig {

    private static  InMobileApi api ;
    private static Retrofit retrofit;
    private static OkHttpClient httpClient;



    private ApiConfig(){}


    public static InMobileApi getClient(){
        if(api==null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            api = getRetrofit().create(InMobileApi.class);
        }

        return api;
    }


    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.mocky.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient())
                    .build();

        }

        return retrofit;
    }


    private static OkHttpClient getHttpClient(){
        if(httpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            httpClient = builder.build();
        }
        return httpClient;
    }
}
