package com.example.james.wodrecordapp.ui.main.ui.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.example.james.wodrecordapp.data.WorkoutDao;
import com.example.james.wodrecordapp.data.interfaces.RealmCallback;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.ui.base.BasePresenter;
import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.ui.calendar.SublimePickerFragment;
import com.example.james.wodrecordapp.ui.main.ui.presenterbinders.RecordMvpPresenter;
import com.example.james.wodrecordapp.ui.main.ui.viewbinders.RecordMvpView;
import com.example.james.wodrecordapp.utils.DateTimeUtils;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by jimmy on 22/10/2017.
 */

public class RecordWODPresenter extends BasePresenter implements RecordMvpPresenter {

    private final static String TAG = RecordWODPresenter.class.getSimpleName();
    private RecordMvpView recordMvpView;
    @Nullable
    private WorkoutDao workoutDao;
    @NonNull
    private Workout workout;

    @Inject
    public RecordWODPresenter(@Nullable WorkoutDao workoutDao, @NonNull Workout workout) {
        this.workoutDao = workoutDao;
        this.workout = workout;
    }

    @Override
    public void onAttach(MvpView mvpView) {
        recordMvpView = (RecordMvpView) mvpView;
    }

    @NonNull
    public Workout getWorkout() {
        return workout;
    }

    public void addWOD(String sets, String exercises, String weight, String details, String time) {
        Workout workout = createWorkout(sets, exercises, weight, details, time);
        insertOrUpdateWorkout(workout, (RealmCallback) recordMvpView);
    }

    private Workout createWorkout(String sets, String exercise, String weight, String details, String time) {
        workout.setWodSets(sets);
        workout.setWodExercise(exercise);
        workout.setWodWeight(weight);
        workout.setWodDetails(details);
        workout.setWodTime(time);
        return workout;
    }

    public void insertOrUpdateWorkout(Workout workout, RealmCallback realmCallback) {
        if (workoutDao != null) {
            workoutDao.insertOrUpdateWorkout(workout, realmCallback);
        }
    }

    public void displayCalender(Context context, final Workout workout) {
        // DialogFragment to host SublimePicker
        SublimePickerFragment pickerFrag = new SublimePickerFragment();

        // Options
        Pair<Boolean, SublimeOptions> optionsPair = getOptions();

        if (!optionsPair.first) { // If options are not valid
            Toast.makeText(context, "No pickers activated", Toast.LENGTH_SHORT).show();
            return;
        }

        // Valid options
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);

        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.setCallback(new SublimePickerFragment.Callback() {
            @Override
            public void onCancelled() {

            }

            @Override
            public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
                                                SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                                String recurrenceRule) {
                String formattedDate = DateTimeUtils.formatDate(selectedDate.getFirstDate().getTime());

                // Create the workout object with the datetime selected
                workout.setWodDateTime(String.format("%s", formattedDate));

                recordMvpView.updateWodDate(workout.getWodDateTime());
            }
        });

        recordMvpView.showDateTimePicker(pickerFrag);
    }

    private Pair<Boolean, SublimeOptions> getOptions() {
        // Validates & returns SublimePicker options
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0;

        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;

        options.setDisplayOptions(displayOptions);

        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);

        // Enable/disable the date range selection feature
        //options.setCanPickDateRange(cbAllowDateRangeSelection.isChecked());

        // If 'displayOptions' is zero, the chosen options are not valid
        return new Pair<>(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }
}
