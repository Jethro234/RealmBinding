package com.example.james.realmbinding.ui.progress;

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

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        viewProgressMvpView.updateWorkoutsList(getRecordedWorkouts());
    }

    @Override
    public void calculateWorkoutListDiff(final List<Workout> oldWorkouts, final List<Workout> newWorkouts) {
        final Map<String, DiffUtil.DiffResult> diffMap = new HashMap<>();

        //TODO make this cleaner!
        Flowable.just(newWorkouts)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Workout>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        final WorkoutDiffCallback workoutDiffCallback = new WorkoutDiffCallback(oldWorkouts, newWorkouts);
                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(workoutDiffCallback);

                        diffMap.put("diffResult", diffResult);

                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<Workout> workouts) {
                        Log.d(TAG, "OnNext");
                        DiffUtil.DiffResult diffResult = diffMap.get("diffResult");
                        viewProgressMvpView.displayWorkouts(diffResult, workouts);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, t.getMessage());
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "OnComplete");
                    }
                });
    }
}
