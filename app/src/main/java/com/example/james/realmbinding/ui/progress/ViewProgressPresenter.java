package com.example.james.realmbinding.ui.progress;

import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jimmy on 29/10/2017.
 */

public class ViewProgressPresenter extends BasePresenter implements ViewProgressMvpPresenter {

    private final static String TAG = ViewProgressPresenter.class.getSimpleName();
    private WorkoutDao workoutDao;

    @Inject
    public ViewProgressPresenter(ViewProgressActivity mvpView) {
        super(mvpView);
        workoutDao = new WorkoutDaoImpl(super.getContext());
    }

    @Override
    public List<Workout> getRecordedWorkouts() {
        return workoutDao.queryWorkout(-1);
    }
}
