package com.example.james.wodrecordapp.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.james.wodrecordapp.MvpPresenter;
import com.example.james.wodrecordapp.MvpView;


/**
 * Created by jimmy on 22/10/2017.
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();
    private V mvpView;

    public BasePresenter() {

    }

    public BasePresenter(V mvpView) {
        this.mvpView = mvpView;
        onAttach(this.mvpView);
    }

    public V getMvpView() {
        return mvpView;
    }

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
        return (mvpView instanceof Fragment) ? ((Fragment) mvpView).getActivity() : (Context) mvpView;
    }
}
