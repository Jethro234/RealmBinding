package com.example.james.wodrecordapp.di.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jimmy on 04/03/2018.
 */

@Module
public class WebServiceModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }
}
