package com.example.james.realmbinding.ui.progress;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.MvpView;

import java.util.List;

/**
 * Created by jimmy on 29/10/2017.
 */

public interface ViewProgressMvp extends MvpView {
    List<Workout> getRecordedWorkouts();
    SwipeRefreshLayout.OnRefreshListener getRefreshWorkoutsListener();
    void calculateWorkoutListDiff(final List<Workout> oldWorkouts, final List<Workout> newWorkouts);
}
