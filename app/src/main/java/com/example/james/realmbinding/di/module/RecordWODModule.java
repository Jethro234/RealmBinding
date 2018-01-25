package com.example.james.realmbinding.di.module;

import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.record.RecordMvpPresenter;
import com.example.james.realmbinding.ui.record.RecordWODFrag;
import com.example.james.realmbinding.ui.record.RecordWODPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jimmy on 25/01/2018.
 */

@Module
public abstract class RecordWODModule {

    @ContributesAndroidInjector
    abstract RecordWODFrag recordWODFrag();

    @ActivityScoped
    @Binds
    abstract RecordMvpPresenter recordMvpPresenter(RecordWODPresenter recordWODPresenter);
}