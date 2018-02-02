package com.example.james.realmbinding.ui.progress;

import android.support.v7.util.DiffUtil;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.data.model.Workout;

import java.util.List;

/**
 * Created by jimmy on 31/01/2018.
 */

public interface ViewProgressMvpView extends MvpView {
    void updateWorkoutsList(List<Workout> workouts);
    void displayWorkouts(DiffUtil.DiffResult diffResult, List<Workout> workouts);
}
