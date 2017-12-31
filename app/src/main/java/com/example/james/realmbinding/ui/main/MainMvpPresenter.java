package com.example.james.realmbinding.ui.main;

import com.example.james.realmbinding.MvpPresenter;
import com.example.james.realmbinding.di.ActivityScoped;

/**
 * Created by jimmy on 31/12/2017.
 */

@ActivityScoped
public interface MainMvpPresenter extends MvpPresenter {
    void onDrawerOptionHomeClick();
    void onDrawerOptionRecordWODClick();
    void onDrawerOptionViewProgressClick();
    void onDrawerOptionToolsClick();
}
