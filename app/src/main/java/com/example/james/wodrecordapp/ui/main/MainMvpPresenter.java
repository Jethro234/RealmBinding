package com.example.james.wodrecordapp.ui.main;

import com.example.james.wodrecordapp.MvpPresenter;
import com.example.james.wodrecordapp.di.ActivityScoped;

/**
 * Created by jimmy on 31/12/2017.
 */

@ActivityScoped
public interface MainMvpPresenter extends MvpPresenter {
    void onDrawerOptionHomeClick();
    void onDrawerOptionRecordWODClick();
    void onDrawerOptionViewProgressClick();
    void onDrawerOptionToolsClick();

    WODResponse getListOfWods();
}
