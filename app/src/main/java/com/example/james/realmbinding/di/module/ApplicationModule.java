package com.example.james.realmbinding.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.james.realmbinding.ControlApplication;
import com.example.james.realmbinding.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;
/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public class ApplicationModule {

    private ControlApplication controlApplication;
    private SharedPreferences sharedPreferences;

    public ApplicationModule(ControlApplication controlApplication) {
        this.controlApplication = controlApplication;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(controlApplication);
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return controlApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return sharedPreferences;
    }
}
