package com.example.james.realmbinding.ui.main;

import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainActivityPresenter extends BasePresenter implements MainActivityMvp {

    private final static String TAG = MainActivityPresenter.class.getSimpleName();
    private WorkoutDao workoutDao;

    @Inject
    public MainActivityPresenter(MainActivity mvpView) {
        super(mvpView);
    }
}
