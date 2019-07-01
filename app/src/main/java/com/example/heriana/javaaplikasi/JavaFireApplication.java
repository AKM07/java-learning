package com.example.heriana.javaaplikasi;

import android.app.Activity;
import android.app.Application;

import com.orm.SugarContext;


/**
 * Created by AKM on 6/29/2019.
 */
public class JavaFireApplication extends Application {

    private static JavaFireApplication instance;


    public JavaFireApplication() {
        instance = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);
    }



    public static JavaFireApplication getInstance() {
        return instance;
    }

    public static void setInstance(JavaFireApplication instance) {
        JavaFireApplication.instance = instance;
    }
}
