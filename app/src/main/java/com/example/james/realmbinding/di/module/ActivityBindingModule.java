package com.example.james.realmbinding.di.module;

import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.main.MainActivity;
import com.example.james.realmbinding.ui.progress.ViewProgressActivity;
import com.example.james.realmbinding.ui.record.RecordWOD;

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
    @ContributesAndroidInjector()
    abstract RecordWOD recordWOD();
}
