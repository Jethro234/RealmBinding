package com.example.james.realmbinding.ui.progress;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jimmy on 29/10/2017.
 */

public class ViewProgressPresenter extends BasePresenter implements ViewProgressMvp {

    private final static String TAG = ViewProgressPresenter.class.getSimpleName();
    private ViewProgressMvpView viewProgressMvpView;
    @NonNull
    private WorkoutDao workoutDao;

    @Inject
    public ViewProgressPresenter(@NonNull WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    @Override
    public void onAttach(MvpView mvpView) {
        viewProgressMvpView = (ViewProgressMvpView) mvpView;
    }

    @Override
    public List<Workout> getRecordedWorkouts() {
        return workoutDao.queryWorkout(-1);
    }

    @Override
    public SwipeRefreshLayout.OnRefreshListener getRefreshWorkoutsListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshWorkouts();
            }
        };
    }

    private void refreshWorkouts() {
        viewProgressMvpView.refreshWorkouts(getRecordedWorkouts());
    }
}
