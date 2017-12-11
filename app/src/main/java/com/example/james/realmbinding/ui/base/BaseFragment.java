package com.example.james.realmbinding.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Created by buxtonj on 11/12/2017.
 */

public class BaseFragment extends DaggerFragment {

    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }
}
