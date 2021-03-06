package com.example.james.wodrecordapp.di.component;

import com.example.james.wodrecordapp.ControlApplication;
import com.example.james.wodrecordapp.di.module.ApplicationModule;
import com.example.james.wodrecordapp.di.module.WebServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by jimmy on 19/11/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, WebServiceModule.class})
public interface ApplicationComponent extends AndroidInjector<ControlApplication> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<ControlApplication> {
    }
}
