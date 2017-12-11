package com.example.james.realmbinding.ui.progress;

import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.MvpView;

import java.util.List;

/**
 * Created by jimmy on 29/10/2017.
 */

public interface ViewProgressMvpPresenter extends MvpView {
    List<Workout> getRecordedWorkouts();
}
