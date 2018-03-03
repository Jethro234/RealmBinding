package com.example.james.wodrecordapp.ui.progress;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.wodrecordapp.R;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by buxtonj on 11/12/2017.
 */

public class ViewProgressFrag extends BaseFragment implements ViewProgressMvpView {

    private static final String TAG = ViewProgressFrag.class.getSimpleName();

    @Inject
    ViewProgressPresenter viewProgressPresenter;

    @BindView(R.id.my_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipe_refresh;

    private Context context;
    private WorkoutAdapter workoutAdapter;

    @Inject
    public ViewProgressFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutAdapter = new WorkoutAdapter(new ArrayList<>());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_progress_frag, container, false);

        setUnBinder(ButterKnife.bind(this, root));

        viewProgressPresenter.onAttach(this);

        context = root.getContext();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(workoutAdapter);
        recyclerView.setHasFixedSize(true);
        swipe_refresh.setOnRefreshListener(viewProgressPresenter.getRefreshWorkoutsListener());

        List<Workout> workouts = viewProgressPresenter.getRecordedWorkouts();
        updateWorkoutsList(workouts);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void updateWorkoutsList(final List<Workout> workouts) {
        viewProgressPresenter.calculateWorkoutListDiff(workoutAdapter.getWorkoutList(), workouts);
    }

    @Override
    public void displayWorkouts(DiffUtil.DiffResult diffResult, List<Workout> workouts) {
        swipe_refresh.setRefreshing(false);

        workoutAdapter.setWorkoutList(workouts);
        diffResult.dispatchUpdatesTo(workoutAdapter);
    }
}
