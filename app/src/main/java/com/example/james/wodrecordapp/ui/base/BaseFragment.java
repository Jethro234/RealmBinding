package com.example.james.wodrecordapp.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.utils.CommonUtils;

import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Created by buxtonj on 11/12/2017.
 */

public abstract class BaseFragment extends DaggerFragment implements MvpView {

    private ProgressDialog progressDialog;

    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(getContext());
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}