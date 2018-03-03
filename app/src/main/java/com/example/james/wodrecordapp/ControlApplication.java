package com.example.james.wodrecordapp;

import com.example.james.wodrecordapp.di.component.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jimmy on 08/10/2017.
 */

public class ControlApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("workoutDB.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}
