package com.example.james.wodrecordapp;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.example.james.wodrecordapp.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jimmy on 08/10/2017.
 */

public class ControlApplication extends Application implements HasActivityInjector, HasFragmentInjector {

    @Inject DispatchingAndroidInjector<Activity> activityInjector;
    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder().application(this).build().inject(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("workoutDB.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
