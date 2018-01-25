package com.example.james.realmbinding.ui.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.interfaces.RealmCallback;
import com.example.james.realmbinding.data.model.Workout;
import com.example.james.realmbinding.ui.base.BaseActivity;
import com.example.james.realmbinding.ui.calendar.SublimePickerFragment;
import com.example.james.realmbinding.utils.ActivityUtils;
import com.example.james.realmbinding.utils.DateTimeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: Workout Logger App
 * Created by James on 07-Aug-16.
 */
public class RecordWODActivity extends BaseActivity implements RealmCallback,RecordMvpView {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject
    RecordWODFrag recordWODFrag;

    @Inject
    RecordMvpPresenter recordMvpPresenter;

    private Context context;
    private Workout workout;

    public static Intent getRecordWodActIntent(Context context) {
        return new Intent(context, RecordWODActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_wod);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //todo: add presenter to dagger

        context = this;

        RecordWODFrag cachedFrag =
                (RecordWODFrag) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (cachedFrag == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    recordWODFrag, R.id.contentFrame);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWodDate(DateTimeUtils.getCurrentDate());
    }

    @Override
    public void Failure(Throwable e, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    public void Success() {
        Toast.makeText(context, getString(R.string.record_wod), Toast.LENGTH_SHORT).show();
        finish();
    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {

            String formattedDate = DateTimeUtils.formatDate(selectedDate.getFirstDate().getTime());

            // Create the workout object with the datetime selected
            workout = new Workout(String.format("%s", formattedDate));

            updateWodDate(workout.getWodDateTime());
        }
    };

    @Override
    public void updateWodDate(String dateTime) {

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
    private Pair<Boolean, SublimeOptions> getOptions() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
