package com.example.james.realmbinding.di.component;

import com.example.james.realmbinding.di.PerActivity;
import com.example.james.realmbinding.di.module.ViewProgressModule;
import com.example.james.realmbinding.ui.progress.ViewProgressMvpPresenter;

import dagger.Component;

/**
 * Created by jimmy on 19/11/2017.
 */

@PerActivity
@Component(modules = ViewProgressModule.class, dependencies = ApplicationComponent.class)
public interface ViewProgressComponent {

    ViewProgressMvpPresenter getPresenter();

}
