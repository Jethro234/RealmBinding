package com.example.james.realmbinding.di.module;

import com.example.james.realmbinding.ui.record.RecordWODFrag;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jimmy on 25/01/2018.
 */

@Module
public abstract class RecordWODModule {

    @ContributesAndroidInjector
    abstract RecordWODFrag recordWODFrag();
}