package com.example.james.wodrecordapp.ui.progress;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.james.wodrecordapp.MvpPresenter;
import com.example.james.wodrecordapp.data.model.Workout;

import java.util.List;

/**
 * Created by jimmy on 29/10/2017.
 */

public interface ViewProgressMvp extends MvpPresenter {
    List<Workout> getRecordedWorkouts();
    SwipeRefreshLayout.OnRefreshListener getRefreshWorkoutsListener();
    void calculateWorkoutListDiff(final List<Workout> oldWorkouts, final List<Workout> newWorkouts);
}
