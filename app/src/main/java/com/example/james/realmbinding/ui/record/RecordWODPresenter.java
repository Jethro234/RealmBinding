package com.example.james.realmbinding.ui.record;

import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.data.interfaces.RealmCallback;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BasePresenter;
import com.example.james.realmbinding.MvpView;

import javax.inject.Inject;

/**
 * Created by jimmy on 22/10/2017.
 */

public class RecordWODPresenter extends BasePresenter implements RecordMvpPresenter {

    private final static String TAG = RecordWODPresenter.class.getSimpleName();
    private WorkoutDao workoutDao;

    @Inject
    public RecordWODPresenter(RecordWODActivity mvpView) {
        super(mvpView);
    }

    @Override
    public void onAttach(MvpView mvpView) {
        super.onAttach(mvpView);
        workoutDao = new WorkoutDaoImpl(super.getContext());
    }

    public void insertOrUpdateWorkout(Workout workout, RealmCallback realmCallback) {
        workoutDao.insertOrUpdateWorkout(workout, realmCallback);
    }
}
