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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: Workout Logger App
 * Created by James on 14-Aug-16.
 */
@ActivityScoped
public class ViewProgressActivity extends BaseActivity implements MvpView {

    @Inject
    ViewProgressMvpPresenter viewProgressPresenter;

    @BindView(R.id.my_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress);

        setUnBinder(ButterKnife.bind(this));

        setSupportActionBar(toolbar);

        workoutAdapter = new WorkoutAdapter(new ArrayList<Workout>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(workoutAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Workout> workouts = viewProgressPresenter.getRecordedWorkouts();
        workoutAdapter.refreshData(workouts);
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
