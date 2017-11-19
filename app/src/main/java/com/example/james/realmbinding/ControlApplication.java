package com.example.james.realmbinding;

import android.app.Application;

import com.example.james.realmbinding.di.component.ApplicationComponent;
import com.example.james.realmbinding.di.component.DaggerApplicationComponent;
import com.example.james.realmbinding.di.module.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jimmy on 08/10/2017.
 */

public class ControlApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("workoutDB.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
