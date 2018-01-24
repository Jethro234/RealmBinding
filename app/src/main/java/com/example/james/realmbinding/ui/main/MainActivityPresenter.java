package com.example.james.realmbinding.ui.main;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainActivityPresenter extends BasePresenter implements MainMvpPresenter {

    private final static String TAG = MainActivityPresenter.class.getSimpleName();
    private MainMvpView mainMvpView;

    @Inject
    public MainActivityPresenter(MainActivity mvpView) {
        super(mvpView);
    }

    @Override
    public void onAttach(MvpView mvpView) {
        super.onAttach(mvpView);
        mainMvpView = (MainMvpView) mvpView;
    }

    @Override
    public void onDrawerOptionHomeClick() {
        mainMvpView.closeNavigationDrawer();
        mainMvpView.showHomeFragment();
    }

    @Override
    public void onDrawerOptionRecordWODClick() {
        mainMvpView.closeNavigationDrawer();
        mainMvpView.showRecordWodActivity();
    }

    @Override
    public void onDrawerOptionViewProgressClick() {
        mainMvpView.closeNavigationDrawer();
        mainMvpView.showViewProgressActivity();
    }

    @Override
    public void onDrawerOptionToolsClick() {
        mainMvpView.closeNavigationDrawer();
        mainMvpView.showToolsFragment();
    }
}
