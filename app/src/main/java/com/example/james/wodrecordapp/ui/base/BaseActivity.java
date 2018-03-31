package com.example.james.wodrecordapp.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.james.wodrecordapp.MvpView;

import javax.inject.Inject;

import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jimmy on 22/10/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView, HasFragmentInjector, HasSupportFragmentInjector {
    @Inject DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;
    @Inject DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    private ActionBar actionBar;
    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
