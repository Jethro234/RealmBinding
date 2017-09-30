package com.example.james.realmbinding.progress;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.adapter.WorkoutAdapter;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.interfaces.WorkoutDao;
import com.example.james.realmbinding.model.Workout;
import com.example.james.realmbinding.modelview.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmConfiguration;

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
        context = this;

        // Set realm config
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();

        workoutDao = new WorkoutDaoImpl(context, config);
        List<WorkoutViewModel> workoutViewModels = getWorkoutViewModels(workoutDao.queryWorkout(-1));

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WorkoutAdapter(workoutViewModels));

    }

    private ArrayList<WorkoutViewModel> getWorkoutViewModels(List<Workout> workoutRealmResults) {
        ArrayList<WorkoutViewModel> workoutViewModelArrayList = new ArrayList<>();
        for (Workout workout : workoutRealmResults){
            workoutViewModelArrayList.add(new WorkoutViewModel(workout));
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
