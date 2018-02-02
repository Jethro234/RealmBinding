package com.example.james.realmbinding.di.module;

import android.support.annotation.NonNull;

import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.progress.ViewProgressActivity;
import com.example.james.realmbinding.ui.progress.ViewProgressFrag;
import com.example.james.realmbinding.ui.progress.ViewProgressMvp;
import com.example.james.realmbinding.ui.progress.ViewProgressPresenter;

import javax.annotation.Nullable;

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

    @ContributesAndroidInjector
    abstract ViewProgressFrag viewProgressFrag();

    @ActivityScoped
    @Binds
    abstract ViewProgressMvp viewProgressMvpPresenter(ViewProgressPresenter viewProgressPresenter);
}
