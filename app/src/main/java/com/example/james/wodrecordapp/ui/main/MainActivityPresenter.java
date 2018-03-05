package com.example.james.wodrecordapp.ui.main;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.retrofit.MockWODService;
import com.example.james.wodrecordapp.ui.base.BasePresenter;
import com.google.gson.Gson;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    public void getListOfWods() {
        Log.d(TAG, "Getting WODs");
        //TODO Make a global method to display progress dialogs
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Single<WODResponse> wodResponseSingle = Single.fromCallable(() ->
                gson.fromJson(MockWODService.getWODs(getContext()), WODResponse.class));

        wodResponseSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((wodResponse, throwable) -> {
                    if (throwable == null) {
                        //TODO Throw these results back to view
                        Log.d(TAG, String.format("I have %d wods", wodResponse.wods.size()));
                        progressDialog.dismiss();
                    } else {
                        throwable.printStackTrace();
                    }
                });
    }
}
