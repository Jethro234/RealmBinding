package com.example.james.realmbinding.ui.progress.callback;

import android.support.v7.util.DiffUtil;

import com.example.james.realmbinding.data.model.Workout;

import java.util.List;

/**
 * Created by jimmy on 01/02/2018.
 */

public class WorkoutDiffCallback extends DiffUtil.Callback {

    private final List<Workout> oldWorkoutList;
    private final List<Workout> newWorkoutList;

    public WorkoutDiffCallback(List<Workout> oldWorkoutList, List<Workout> newWorkoutList) {
        this.oldWorkoutList = oldWorkoutList;
        this.newWorkoutList = newWorkoutList;
    }

    @Override
    public int getOldListSize() {
        return oldWorkoutList.size();
    }

    @Override
    public int getNewListSize() {
        return newWorkoutList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWorkoutList.get(oldItemPosition).getId() == newWorkoutList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWorkoutList.get(oldItemPosition).equals(newWorkoutList.get(newItemPosition));
    }
}
