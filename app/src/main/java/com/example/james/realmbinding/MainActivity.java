package com.example.james.realmbinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.james.realmbinding.databinding.MainActivityBinding;
import com.example.james.realmbinding.progress.ViewProgress;
import com.example.james.realmbinding.workout.RecordWOD;

/**
 * Project: Crossfit Calendar App
 * Created by James on 06-Aug-16.
 */
public class MainActivity extends AppCompatActivity {

    MainActivityBinding mainActivity;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = DataBindingUtil.setContentView(this, R.layout.main_activity);
        context = this;
    }

    public void onRecordWodClick(View view){
        Intent i = new Intent(context, RecordWOD.class);
        startActivity(i);
    }

    public void onViewProgress(View view) {
        Intent i = new Intent(context, ViewProgress.class);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
