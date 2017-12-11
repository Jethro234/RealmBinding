package com.example.james.realmbinding.di.module;

import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.progress.ViewProgressMvp;
import com.example.james.realmbinding.ui.progress.ViewProgressPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public abstract class ViewProgressModule {

    @ActivityScoped
    @Binds
    abstract ViewProgressMvp viewProgressMvpPresenter(ViewProgressPresenter viewProgressPresenter);
}
