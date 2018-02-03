package com.example.james.realmbinding.ui.progress;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.widget.Toast;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BasePresenter;
import com.example.james.realmbinding.ui.progress.callback.WorkoutDiffCallback;
import com.example.james.realmbinding.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jimmy on 29/10/2017.
 */

public class ViewProgressPresenter extends BasePresenter implements ViewProgressMvp {

    private final static String TAG = ViewProgressPresenter.class.getSimpleName();

    private ProgressDialog progressDialog;
    private ViewProgressMvpView viewProgressMvpView;
    @NonNull
    private WorkoutDao workoutDao;
    @NonNull
    private SchedulerProvider schedulerProvider;

    @Inject
    public ViewProgressPresenter(@NonNull WorkoutDao workoutDao, @NonNull SchedulerProvider schedulerProvider) {
        this.workoutDao = workoutDao;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void onAttach(MvpView mvpView) {
        super.onAttach(mvpView);
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
        viewProgressMvpView.updateWorkoutsList(getRecordedWorkouts());
    }

    @Override
    public void calculateWorkoutListDiff(final List<Workout> oldWorkouts, final List<Workout> newWorkouts) {
        //getMvpView().showLoading();

        final Map<String, DiffUtil.DiffResult> diffMap = new HashMap<>();
        final List<Workout> completedList = new ArrayList<>();

        //TODO make this cleaner!
        Observable.just(newWorkouts)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Observer<List<Workout>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        final WorkoutDiffCallback workoutDiffCallback = new WorkoutDiffCallback(oldWorkouts, newWorkouts);
                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(workoutDiffCallback);
                        diffMap.put("diffResult", diffResult);
                    }

                    @Override
                    public void onNext(List<Workout> workouts) {
                        Log.d(TAG, "calculateWorkoutListDiff: OnNext");
                        completedList.addAll(workouts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "calculateWorkoutListDiff: OnComplete");
                        //getMvpView().hideLoading();

                        DiffUtil.DiffResult diffResult = diffMap.get("diffResult");
                        viewProgressMvpView.displayWorkouts(diffResult, completedList);
                    }
                });
    }
}
