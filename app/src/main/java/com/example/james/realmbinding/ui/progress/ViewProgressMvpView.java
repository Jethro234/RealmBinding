package com.example.james.realmbinding.ui.progress;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.data.model.Workout;

import java.util.List;

/**
 * Created by jimmy on 31/01/2018.
 */

public interface ViewProgressMvpView extends MvpView {
    void refreshWorkouts(List<Workout> workouts);
}
