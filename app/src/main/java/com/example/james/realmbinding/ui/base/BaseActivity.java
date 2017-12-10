package com.example.james.realmbinding.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by jimmy on 22/10/2017.
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {
    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
