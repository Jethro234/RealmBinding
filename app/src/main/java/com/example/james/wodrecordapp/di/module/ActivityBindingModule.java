package com.example.james.wodrecordapp.di.module;

import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.ui.main.ui.screens.MainActivity;
import com.example.james.wodrecordapp.ui.progress.ViewProgressActivity;

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

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
