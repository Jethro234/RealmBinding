package com.example.james.wodrecordapp.ui.main;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.retrofit.MockWODService;
import com.example.james.wodrecordapp.ui.base.BasePresenter;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainActivityPresenter extends BasePresenter implements MainMvpPresenter {

    private final static String TAG = MainActivityPresenter.class.getSimpleName();
    private MainMvpView mainMvpView;

    @Inject
    Gson gson;

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

    @Override
    public WODResponse getListOfWods() {
        return gson.fromJson(MockWODService.getWODs(getContext()), WODResponse.class);
    }
}
