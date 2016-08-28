package com.example.james.realmbinding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.realmbinding.BR;
import com.example.james.realmbinding.R;
import com.example.james.realmbinding.modelview.WorkoutViewModel;

import java.util.List;

/**
 * Project: Crossfit Calendar App
 * Created by James on 28-Aug-16.
 */
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    public static class WorkoutHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public WorkoutHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    private List<WorkoutViewModel> workoutList;

    public WorkoutAdapter(List<WorkoutViewModel> workoutList) {
        this.workoutList = workoutList;
    }

    @Override
    public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row, parent, false);
        return new WorkoutHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutHolder holder, int position) {
        final WorkoutViewModel workoutViewModel = workoutList.get(position);
        holder.getBinding().setVariable(BR.workout, workoutViewModel);
        holder.getBinding().executePendingBindings();
    }



    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
