package com.example.james.wodrecordapp.ui.main.ui.di;

import android.support.annotation.NonNull;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.data.WorkoutDao;
import com.example.james.wodrecordapp.data.WorkoutDaoImpl;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.di.FragmentScoped;
import com.example.james.wodrecordapp.di.module.BaseActivityModule;
import com.example.james.wodrecordapp.ui.main.ui.screens.MainActivityFrag;
import com.example.james.wodrecordapp.ui.main.ui.screens.ToolsFragment;
import com.example.james.wodrecordapp.ui.main.ui.presenter.MainMvpPresenter;
import com.example.james.wodrecordapp.ui.main.ui.presenter.MainActivityPresenter;
import com.example.james.wodrecordapp.ui.record.RecordMvpPresenter;
import com.example.james.wodrecordapp.ui.record.RecordWODFrag;
import com.example.james.wodrecordapp.ui.record.RecordWODPresenter;

import javax.annotation.Nullable;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by buxtonj on 12/12/2017.
 */

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainActivityFrag mainActivityFrag();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ToolsFragment toolsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract RecordWODFrag recordWODFrag();

    @ActivityScoped
    @Binds
    abstract MainMvpPresenter mainActivityMvp(MainActivityPresenter mainActivityPresenter);

    @Provides
    @ActivityScoped
    static WorkoutDao provideWorkoutDao(MvpView mvpView) {
        return new WorkoutDaoImpl(mvpView);
    }

    @Provides
    @NonNull
    @ActivityScoped
    static Workout provideWorkout() {
        return new Workout();
    }

    @ActivityScoped
    @Binds
    abstract RecordMvpPresenter recordMvpPresenter(RecordWODPresenter recordWODPresenter);

}
