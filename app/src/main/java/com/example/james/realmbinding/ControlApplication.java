package com.example.james.realmbinding;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by jimmy on 08/10/2017.
 */

public class ControlApplication extends Application {

    private static ControlApplication controlApplication;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        controlApplication = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    }

    public static ControlApplication getControlApplication() {
        return controlApplication;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
