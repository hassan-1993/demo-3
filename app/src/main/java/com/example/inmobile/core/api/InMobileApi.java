package com.example.inmobile.core.api;

import com.example.inmobile.core.db.entities.Data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;





public interface InMobileApi {

    @GET("/v2/5c98bf7d3200004f00d9059b")
    public<T> Single<List<Data>> getData();



}
