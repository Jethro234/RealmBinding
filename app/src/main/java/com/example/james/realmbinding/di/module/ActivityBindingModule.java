package com.example.james.realmbinding.di.module;

import com.example.james.realmbinding.di.ActivityContext;
import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.progress.ViewProgressActivity;
import com.example.james.realmbinding.ui.progress.ViewProgressPresenter;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jimmy on 10/12/2017.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = ViewProgressModule.class)
    abstract ViewProgressActivity viewProgressActivity();
}
