package com.example.james.realmbinding.ui.progress;

import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BasePresenter;
import com.example.james.realmbinding.ui.base.MvpView;

import java.util.List;

/**
 * Created by jimmy on 29/10/2017.
 */

public class ViewProgressPresenter extends BasePresenter implements ViewProgressMvpPresenter {

    private final static String TAG = ViewProgressPresenter.class.getSimpleName();
    private WorkoutDao workoutDao;

    public ViewProgressPresenter(MvpView mvpView) {
        super.onAttach(mvpView);
        workoutDao = new WorkoutDaoImpl(super.getContext());
    }

    @Override
    public List<Workout> getRecordedWorkouts() {
        return workoutDao.queryWorkout(-1);
    }
}
