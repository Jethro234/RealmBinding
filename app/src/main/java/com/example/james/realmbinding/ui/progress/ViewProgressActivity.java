package com.example.james.realmbinding.ui.progress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.di.ActivityScoped;
import com.example.james.realmbinding.ui.base.BaseActivity;
import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: Workout Logger App
 * Created by James on 14-Aug-16.
 */

public class ViewProgressActivity extends BaseActivity implements MvpView {

    @Inject
    ViewProgressFrag viewProgressFrag;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress);

        setUnBinder(ButterKnife.bind(this));

        setSupportActionBar(toolbar);

        //Initialise the fragment
        ViewProgressFrag cachedViewProgressFrag =
                (ViewProgressFrag) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (cachedViewProgressFrag == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), viewProgressFrag, R.id.contentFrame);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
