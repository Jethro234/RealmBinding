package com.example.james.realmbinding.ui.base;

/**
 * Created by jimmy on 22/10/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
}
