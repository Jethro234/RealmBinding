package com.example.james.realmbinding.ui.progress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.MvpView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: Workout Logger App
 * Created by James on 14-Aug-16.
 */
public class ViewProgress extends AppCompatActivity implements MvpView {
    private ViewProgressPresenter viewProgressPresenter;
    private WorkoutAdapter workoutAdapter;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewProgressPresenter = new ViewProgressPresenter(ViewProgress.this);

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
