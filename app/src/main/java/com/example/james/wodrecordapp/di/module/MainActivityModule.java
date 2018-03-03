package com.example.james.wodrecordapp.di.module;

import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.ui.main.MainActivityFrag;
import com.example.james.wodrecordapp.ui.main.ToolsFragment;
import com.example.james.wodrecordapp.ui.main.MainMvpPresenter;
import com.example.james.wodrecordapp.ui.main.MainActivityPresenter;

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
    abstract ToolsFragment toolsFragment();

    @ActivityScoped
    @Binds
    abstract MainMvpPresenter mainActivityMvp(MainActivityPresenter mainActivityPresenter);

}
