package com.example.james.realmbinding.workout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.example.james.realmbinding.R;
import com.example.james.realmbinding.calendar.*;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.interfaces.RealmCallback;
import com.example.james.realmbinding.interfaces.WorkoutDao;
import com.example.james.realmbinding.model.Workout;
import com.example.james.realmbinding.modelview.WorkoutViewModel;

import org.joda.time.DateTime;

import io.realm.RealmConfiguration;

/**
 * Project: Crossfit Calendar App
 * Created by James on 07-Aug-16.
 */
public class RecordWOD extends AppCompatActivity implements RealmCallback {
    Spinner spinnerWodExercise;
    Button btn_addWod;

    private int mHour, mMinute;
    private Context context;
    private WorkoutViewModel workoutViewModel;
    private WorkoutDao workoutDao;

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
            finish();
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {

            //Pass selected values here
            mHour = hourOfDay;
            mMinute = minute;

            workoutViewModel = new WorkoutViewModel(new Workout());

            DateTime dateTime = new DateTime(selectedDate.getFirstDate().getTime());
            workoutViewModel.setWodDateTime(dateTime.toLocalDate().toString());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_wod);

        context = this;

        spinnerWodExercise = (Spinner) findViewById(R.id.spinner_wod_exercise);
        btn_addWod = (Button) findViewById(R.id.btn_add_wod);

        // Obtain realm instance
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        workoutDao = new WorkoutDaoImpl(config);

        // Get Resources
        Resources res = getResources();
        final String[] WodArray = res.getStringArray(R.array.skills_array);

        //Set up the spinner
        final ArrayAdapter<String> wodExercisesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                WodArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodExercise.setAdapter(wodExercisesAdapter);

        btn_addWod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutViewModel.setWodExercise(spinnerWodExercise.getSelectedItem().toString());
                workoutDao.insertWorkout(workoutViewModel, (RealmCallback)context);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayCalender(context);
    }

    @Override
    public void Failure(Throwable e, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    public void Success() {
        Toast.makeText(context, "Record added", Toast.LENGTH_SHORT).show();
    }

    public void displayCalender(Context context) {
        // DialogFragment to host SublimePicker
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);

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
        pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    // Validates & returns SublimePicker options
    Pair<Boolean, SublimeOptions> getOptions() {
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
