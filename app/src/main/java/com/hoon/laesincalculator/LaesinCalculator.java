package com.hoon.laesincalculator;

import android.app.Application;
import android.content.Context;

/**
 * Created by hoon1 on 2015-07-21.
 */
public class LaesinCalculator extends Application {

    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplicationContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }
}
