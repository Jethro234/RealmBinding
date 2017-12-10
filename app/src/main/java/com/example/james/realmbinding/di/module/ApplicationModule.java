package com.example.james.realmbinding.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.james.realmbinding.ControlApplication;
import com.example.james.realmbinding.di.ApplicationContext;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(ControlApplication application);
}
