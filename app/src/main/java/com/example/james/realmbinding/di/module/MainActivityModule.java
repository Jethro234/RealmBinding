package com.example.james.realmbinding.di.module;

import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.main.MainActivityFrag;
import com.example.james.realmbinding.ui.main.MainActivityFragTwo;
import com.example.james.realmbinding.ui.main.MainActivityMvp;
import com.example.james.realmbinding.ui.main.MainActivityPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by buxtonj on 12/12/2017.
 */

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract MainActivityFrag mainActivityFrag();

    @ContributesAndroidInjector
    abstract MainActivityFragTwo mainActivityFragTwo();

    @ActivityScoped
    @Binds
    abstract MainActivityMvp mainActivityMvp(MainActivityPresenter mainActivityPresenter);

}
