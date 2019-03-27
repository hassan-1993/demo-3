package com.example.inmobile;

import android.app.Application;
import android.content.Context;

/**
 * Created by Hassan on 3/27/2019.
 **/
public class App extends Application {

    private static App app;

    public App(){
        app  =this;
    }


    public static Context getContext(){
        return app;
    }
}
