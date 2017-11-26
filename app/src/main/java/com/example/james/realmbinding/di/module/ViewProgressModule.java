package com.example.james.realmbinding.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.james.realmbinding.di.ActivityContext;
import com.example.james.realmbinding.di.PerActivity;
import com.example.james.realmbinding.ui.progress.ViewProgressActivity;
import com.example.james.realmbinding.ui.progress.ViewProgressMvpPresenter;
import com.example.james.realmbinding.ui.progress.ViewProgressPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jimmy on 19/11/2017.
 */

@Module
public class ViewProgressModule {

    private ViewProgressActivity viewProgressActivity;

    public ViewProgressModule(ViewProgressActivity viewProgressActivity) {
        this.viewProgressActivity = viewProgressActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return viewProgressActivity;
    }

    @Provides
    public ViewProgressActivity provideActivity() {
        return viewProgressActivity;
    }

    @Provides
    ViewProgressMvpPresenter viewProgressMvpPresenter() {
        return new ViewProgressPresenter(viewProgressActivity);
    }
}
