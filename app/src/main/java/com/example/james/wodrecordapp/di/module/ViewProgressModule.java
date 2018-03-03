package com.example.james.wodrecordapp.di.module;

import android.support.annotation.NonNull;

import com.example.james.wodrecordapp.data.WorkoutDao;
import com.example.james.wodrecordapp.data.WorkoutDaoImpl;
import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.ui.progress.ViewProgressActivity;
import com.example.james.wodrecordapp.ui.progress.ViewProgressFrag;
import com.example.james.wodrecordapp.ui.progress.ViewProgressMvp;
import com.example.james.wodrecordapp.ui.progress.ViewProgressPresenter;
import com.example.james.wodrecordapp.utils.rx.AppSchedulerProvider;
import com.example.james.wodrecordapp.utils.rx.SchedulerProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public abstract class ViewProgressModule {

    @Provides
    @NonNull
    @ActivityScoped
    static WorkoutDao provideWorkoutDao(ViewProgressActivity viewProgressActivity) {
        return new WorkoutDaoImpl(viewProgressActivity);
    }

    @Provides
    @NonNull
    @ActivityScoped
    static SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @ContributesAndroidInjector
    abstract ViewProgressFrag viewProgressFrag();

    @ActivityScoped
    @Binds
    abstract ViewProgressMvp viewProgressMvpPresenter(ViewProgressPresenter viewProgressPresenter);
}
