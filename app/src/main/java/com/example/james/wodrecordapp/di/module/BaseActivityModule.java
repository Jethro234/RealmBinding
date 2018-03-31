package com.example.james.wodrecordapp.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.james.wodrecordapp.di.ActivityScoped;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jimmy on 10/12/2017.
 */

@Module
public abstract class BaseActivityModule {
    // DI Named Annotation TAG
    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

    @Binds
    @ActivityScoped
    /*
     * Annotation isn't necessary since Activity instance is unique but is here for
     * convention.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    abstract Activity activity(AppCompatActivity appCompatActivity);

    @Binds
    @ActivityScoped
    abstract Context activityContext(Activity activity);

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @ActivityScoped
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
