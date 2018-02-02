package com.example.james.realmbinding.ui.progress;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Project: Workout Logger App
 * Created by James on 28-Aug-16.
 */
public class WorkoutAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_WORKOUT= 1;

    private List<Workout> workoutList;

    static class Filter extends BaseViewHolder {
        @BindView(R.id.filter_bar) EditText filter_bar;

        Filter(View itemView) {
            super(itemView);
            //TODO implement filter functionality
            filter_bar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

     static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.list_item_wod_date) TextView list_item_wod_date;
        @BindView(R.id.list_item_wod_skill) TextView list_item_wod_skill;
        @BindView(R.id.list_item_wod_sets) TextView list_item_wod_sets;
        @BindView(R.id.list_item_wod_details) TextView list_item_wod_details;
        @BindView(R.id.list_item_wod_time) TextView list_item_wod_time;

        ViewHolder(View v) {
            super(v);
        }

        private void setDetails(Workout workout) {
            list_item_wod_date.setText(workout.getWodDateTime());
            list_item_wod_sets.setText(workout.getWodSets());
            list_item_wod_skill.setText(String.format("%1$s - %2$s", workout.getWodExercise(), workout.getWodWeight()));
            list_item_wod_details.setText(workout.getWodDetails());
            list_item_wod_time.setText(workout.getWodTime());
        }
    }

    // Constructor
    WorkoutAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false);

        return (isHeaderViewType(viewType)) ? new WorkoutAdapter.Filter(view) : new WorkoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.setDetails(workoutList.get(position));
        }
    }

    private int getLayout(int viewType) {
        return (isHeaderViewType(viewType)) ? R.layout.adapter_filter_bar : R.layout.workout_row;
    }

    private boolean isHeaderViewType(int viewType) {
        return (viewType == TYPE_HEADER);
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_WORKOUT;
    }

    List<Workout> getWorkoutList() {
        return workoutList;
    }

    void setWorkoutList(List<Workout> workoutList) {
        this.workoutList.clear();
        this.workoutList.addAll(workoutList);
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
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
