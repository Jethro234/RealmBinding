package com.example.james.wodrecordapp.ui.progress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.james.wodrecordapp.R;
import com.example.james.wodrecordapp.ui.base.BaseActivity;
import com.example.james.wodrecordapp.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: Workout Logger App
 * Created by James on 14-Aug-16.
 */

public class ViewProgressActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject
    ViewProgressFrag viewProgressFrag;

    public static Intent getViewProgressActIntent(Context context) {
        return new Intent(context, ViewProgressActivity.class);
    }

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
