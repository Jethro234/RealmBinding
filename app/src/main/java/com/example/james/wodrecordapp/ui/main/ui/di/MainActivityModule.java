package com.example.james.wodrecordapp.ui.main.ui.di;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.data.WorkoutDao;
import com.example.james.wodrecordapp.data.WorkoutDaoImpl;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.di.ActivityScoped;
import com.example.james.wodrecordapp.di.FragmentScoped;
import com.example.james.wodrecordapp.di.module.BaseActivityModule;
import com.example.james.wodrecordapp.ui.main.ui.MainActivity;
import com.example.james.wodrecordapp.ui.main.ui.screens.MainActivityFrag;
import com.example.james.wodrecordapp.ui.main.ui.screens.ToolsFrag;
import com.example.james.wodrecordapp.ui.main.ui.presenterbinders.MainMvpPresenter;
import com.example.james.wodrecordapp.ui.main.ui.presenter.MainActivityPresenter;
import com.example.james.wodrecordapp.ui.main.ui.presenterbinders.RecordMvpPresenter;
import com.example.james.wodrecordapp.ui.main.ui.screens.RecordWODFrag;
import com.example.james.wodrecordapp.ui.main.ui.presenter.RecordWODPresenter;

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
    abstract ToolsFrag toolsFragment();

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

    @Binds
    @ActivityScoped
    abstract AppCompatActivity appCompatActivity(MainActivity mainActivity);

}
