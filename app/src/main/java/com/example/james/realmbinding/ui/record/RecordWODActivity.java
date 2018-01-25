package com.example.james.realmbinding.ui.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import com.example.james.realmbinding.R;
import com.example.james.realmbinding.ui.base.BaseActivity;
import com.example.james.realmbinding.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: Workout Logger App
 * Created by James on 07-Aug-16.
 */
public class RecordWODActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject
    RecordWODFrag recordWODFrag;

    private Context context;

    public static Intent getRecordWodActIntent(Context context) {
        return new Intent(context, RecordWODActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_wod);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        context = this;

        RecordWODFrag cachedFrag =
                (RecordWODFrag) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (cachedFrag == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    recordWODFrag, R.id.contentFrame);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
