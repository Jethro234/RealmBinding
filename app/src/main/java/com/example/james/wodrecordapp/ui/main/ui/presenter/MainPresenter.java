package com.example.james.wodrecordapp.ui.main.ui.presenter;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.james.wodrecordapp.retrofit.MockWODService;
import com.example.james.wodrecordapp.ui.base.BasePresenter;
import com.example.james.wodrecordapp.ui.main.ui.presenterbinders.MainMvpPresenter;
import com.example.james.wodrecordapp.ui.main.ui.MainActivity;
import com.example.james.wodrecordapp.ui.main.retrofit.response.WODResponse;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainPresenter extends BasePresenter implements MainMvpPresenter {

    private final static String TAG = MainPresenter.class.getSimpleName();

    @Inject
    Gson gson;

    @Inject
    public MainPresenter(MainActivity mvpView) {
        super(mvpView);
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
