package com.example.james.realmbinding.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.james.realmbinding.ControlApplication;
import com.example.james.realmbinding.di.ApplicationContext;
import com.example.james.realmbinding.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jimmy on 19/11/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ControlApplication controlApplication);

    @ApplicationContext
    Context context();

    SharedPreferences sharedPrefs();
}
