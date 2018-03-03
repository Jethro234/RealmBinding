package com.example.james.wodrecordapp;

import android.content.Context;

/**
 * Created by jimmy on 22/10/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
    Context getContext();
}
