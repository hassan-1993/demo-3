package com.example.inmobile.core.api;

import android.util.Log;

import com.example.inmobile.core.Callback;
import com.example.inmobile.core.api.ApiConfig;
import com.example.inmobile.core.api.InMobileApi;
import com.example.inmobile.core.db.AppDatabase;
import com.example.inmobile.core.db.entities.Data;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Hassan on 3/27/2019.
 **/
public class Repository {

    AppDatabase appDatabase = AppDatabase.getDatabase();

    /**
     * get data from backend
     * @param errorListener
     * @return
     */
    public LiveData<List<Data>> getData(Callback<Throwable> errorListener){

        Single<List<Data>> dataRx = ApiConfig.getClient().getData();

                dataRx
                .subscribeOn(Schedulers.io())
                .doOnSuccess((data)->{
                    appDatabase.dataDao().insertAll(
                            data.toArray(new Data[0]));
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((success)->{},(err)->{
                    errorListener.onResult(err);
                });



        return appDatabase.dataDao().getAll();
    }



}
