package com.example.james.realmbinding.ui.progress;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Workout Logger App
 * Created by James on 14-Aug-16.
 */
public class ViewProgress extends AppCompatActivity {
    private Context context;
    private WorkoutDao workoutDao;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        workoutDao = new WorkoutDaoImpl(context);
        List<Workout> workouts = getWorkoutViewModels(workoutDao.queryWorkout(-1));

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WorkoutAdapter(workouts));

    }

    private ArrayList<Workout> getWorkoutViewModels(List<Workout> workoutRealmResults) {
        ArrayList<Workout> workoutViewModelArrayList = new ArrayList<>();
        for (Workout workout : workoutRealmResults){
            workoutViewModelArrayList.add(workout);
        }

        return workoutViewModelArrayList;
    }

    @Override
    protected void onStart() {
        super.onStart();
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
