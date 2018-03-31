package com.example.james.wodrecordapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.james.wodrecordapp.ControlApplication;
import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.ui.main.ui.di.MainActivityModule;
import com.example.james.wodrecordapp.ui.main.ui.screens.MainActivity;
import com.example.james.wodrecordapp.ui.progress.ViewProgressActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by jimmy on 19/11/2017.
 */

@Module(includes = AndroidSupportInjectionModule.class)
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

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ViewProgressModule.class)
    abstract ViewProgressActivity viewProgressActivity();
}
