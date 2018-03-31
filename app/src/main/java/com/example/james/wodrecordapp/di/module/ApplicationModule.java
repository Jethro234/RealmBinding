package com.example.james.wodrecordapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.james.wodrecordapp.ControlApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public abstract class ApplicationModule {
    @Binds
    @Singleton
    /*
     * Singleton annotation isn't necessary since Application instance is unique but is here for
     * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
     * them to be scoped since they are the components being injected and their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    abstract Application application(ControlApplication appVar);

    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(ControlApplication application);
}
