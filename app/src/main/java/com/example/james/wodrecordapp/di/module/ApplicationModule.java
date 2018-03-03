package com.example.james.wodrecordapp.di.module;

import android.content.Context;

import com.example.james.wodrecordapp.ControlApplication;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(ControlApplication application);
}
