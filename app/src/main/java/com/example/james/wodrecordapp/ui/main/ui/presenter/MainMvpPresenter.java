package com.example.james.wodrecordapp.ui.main.ui.presenter;

import com.example.james.wodrecordapp.MvpPresenter;
import com.example.james.wodrecordapp.di.ActivityScoped;

/**
 * Created by jimmy on 31/12/2017.
 */

@ActivityScoped
public interface MainMvpPresenter extends MvpPresenter {

    void getListOfWods();
}
