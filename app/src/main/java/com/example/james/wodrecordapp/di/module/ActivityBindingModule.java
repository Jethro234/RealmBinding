package com.example.james.wodrecordapp.di.module;

import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.ui.main.MainActivity;
import com.example.james.wodrecordapp.ui.progress.ViewProgressActivity;
import com.example.james.wodrecordapp.ui.record.RecordWODActivity;

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

    @ActivityScoped
    @ContributesAndroidInjector(modules = RecordWODModule.class)
    abstract RecordWODActivity recordWOD();
}
