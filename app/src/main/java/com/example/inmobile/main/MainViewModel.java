package com.example.inmobile.main;

import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.inmobile.core.api.Repository;
import com.example.inmobile.core.db.entities.Data;

import java.net.UnknownHostException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by Hassan on 3/27/2019.
 **/
public class MainViewModel extends AndroidViewModel {



    Repository repository = new Repository();


    //for timer
    private MutableLiveData<Long> timerEvent = null;
    //for error messages
    private MutableLiveData<String> msgEvent = new MutableLiveData<>();
    //for getting data
    private LiveData<List<Data>> dataListEvent = null;


    public MainViewModel(@NonNull Application application) {
        super(application);
    }




    private void handleError(Throwable e){
        if(e instanceof UnknownHostException){
              msgEvent.setValue("Connection Failed!");
        }else{
              msgEvent.setValue("Something went wrong");
        }

    }


    public LiveData<Long> getTimerEvent(){
        if(timerEvent == null){
            timerEvent = new MutableLiveData<>();
            timerEvent.setValue(SystemClock.elapsedRealtime());
        }

        return timerEvent;
    }


    public MutableLiveData<String> getMsgEvent() {
        msgEvent.setValue(null);
        return msgEvent;
    }





    public LiveData<List<Data>> getData(){
        if(dataListEvent == null){
            dataListEvent = repository.getData(this::handleError);
        }
        return dataListEvent;
    }



    public void onSaveInstance(Bundle outState) {
        if(timerEvent.getValue()!=null)
        outState.putLong("time",timerEvent.getValue());
    }


    public void onRestoreInstance(Bundle savedInstanceState) {
        Long time = savedInstanceState.getLong("time",0);

        if(time!=0){
            timerEvent.setValue(time);
        }
    }
}
