package com.example.james.wodrecordapp.di.module;

import android.support.annotation.NonNull;

import com.example.james.wodrecordapp.data.WorkoutDao;
import com.example.james.wodrecordapp.data.WorkoutDaoImpl;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.ui.record.RecordMvpPresenter;
import com.example.james.wodrecordapp.ui.record.RecordWODActivity;
import com.example.james.wodrecordapp.ui.record.RecordWODFrag;
import com.example.james.wodrecordapp.ui.record.RecordWODPresenter;

import javax.annotation.Nullable;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jimmy on 25/01/2018.
 */

@Module
public abstract class RecordWODModule {

    @Provides
    @Nullable
    @ActivityScoped
    static WorkoutDao provideWorkoutDao(RecordWODActivity recordWODActivity) {
        return new WorkoutDaoImpl(recordWODActivity);
    }

    @Provides
    @NonNull
    @ActivityScoped
    static Workout provideWorkout() {
        return new Workout();
    }

    @ContributesAndroidInjector
    abstract RecordWODFrag recordWODFrag();

    @ActivityScoped
    @Binds
    abstract RecordMvpPresenter recordMvpPresenter(RecordWODPresenter recordWODPresenter);
}