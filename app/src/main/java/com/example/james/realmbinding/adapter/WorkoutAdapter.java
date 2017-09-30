package com.example.james.realmbinding.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.modelview.WorkoutViewModel;

import java.util.List;

/**
 * Project: Workout Logger App
 * Created by James on 28-Aug-16.
 */
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private List<WorkoutViewModel> workoutList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView list_item_wod_date;
        TextView list_item_wod_skill;

        public ViewHolder(View v) {
            super(v);
            list_item_wod_date = v.findViewById(R.id.list_item_wod_date);
            list_item_wod_skill = v.findViewById(R.id.list_item_wod_skill);
        }
    }

    public WorkoutAdapter(List<WorkoutViewModel> workoutList) {
        this.workoutList = workoutList;
    }

    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row, parent, false);
        return new WorkoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkoutAdapter.ViewHolder holder, int position) {
        final WorkoutViewModel workoutViewModel = workoutList.get(position);
        holder.list_item_wod_date.setText(workoutViewModel.getWodDateTime());
        holder.list_item_wod_skill.setText(workoutViewModel.getWodExercise());
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
