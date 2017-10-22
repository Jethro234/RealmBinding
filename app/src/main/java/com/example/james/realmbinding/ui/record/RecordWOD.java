package com.example.james.realmbinding.ui.record;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.data.interfaces.RealmCallback;
import com.example.james.realmbinding.data.WorkoutDao;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.calendar.SublimePickerFragment;

import org.joda.time.DateTime;

/**
 * Project: Workout Logger App
 * Created by James on 07-Aug-16.
 */
public class RecordWOD extends AppCompatActivity implements RealmCallback {
    Spinner spinnerWodExercise;
    Spinner spinnerWodSets;
    Button btn_addWod;
    TextView txt_wod_date;

    private Context context;
    private Workout workout;
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

            DateTime dateTime = new DateTime(selectedDate.getFirstDate().getTime());
            // Create the workout object with the datetime selected
            workout = new Workout(String.format("%s", dateTime.toLocalDate().toString()));
            txt_wod_date.setText(workout.getWodDateTime());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_wod);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        spinnerWodExercise = findViewById(R.id.spinner_wod_exercise);
        spinnerWodSets = findViewById(R.id.spinner_wod_sets);
        btn_addWod = findViewById(R.id.btn_add_wod);
        txt_wod_date = findViewById(R.id.txt_wod_date);
        final EditText txt_wod_weight = findViewById(R.id.txt_weight);
        final EditText txt_wod_details = findViewById(R.id.txt_wod_details);
        final EditText txt_wod_time = findViewById(R.id.txt_wod_time);

        workoutDao = new WorkoutDaoImpl(context);

        // Get Resources
        Resources res = getResources();
        final String[] WodArray = res.getStringArray(R.array.skills_array);
        final String[] WodSetsArray= res.getStringArray(R.array.sets_array);

        //Set up the spinner
        final ArrayAdapter<String> wodExercisesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                WodArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodExercise.setAdapter(wodExercisesAdapter);

        //Set up the sets spinner
        final ArrayAdapter<String> wodSetsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                WodSetsArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodSets.setAdapter(wodSetsAdapter);

        btn_addWod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout.setWodSets(spinnerWodSets.getSelectedItem().toString());
                workout.setWodExercise(spinnerWodExercise.getSelectedItem().toString());
                workout.setWodWeight(txt_wod_weight.getText().toString());
                workout.setWodDetails(txt_wod_details.getText().toString());
                workout.setWodTime(txt_wod_time.getText().toString());
                workoutDao.insertOrUpdateWorkout(workout, (RealmCallback)context);
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