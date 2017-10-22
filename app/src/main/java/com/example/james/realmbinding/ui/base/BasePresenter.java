package com.example.james.realmbinding.ui.base;

import android.content.Context;

/**
 * Created by jimmy on 22/10/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();
    private V mvpView;

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        this.mvpView = null;
    }

    @Override
    public Context getContext() {
        return (Context) mvpView;
    }
}
