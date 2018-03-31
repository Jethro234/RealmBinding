package com.example.james.wodrecordapp.ui.main.ui.screens;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.wodrecordapp.R;
import com.example.james.wodrecordapp.data.interfaces.RealmCallback;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.ui.base.BaseFragment;
import com.example.james.wodrecordapp.ui.calendar.SublimePickerFragment;
import com.example.james.wodrecordapp.ui.main.ui.viewbinders.RecordMvpView;
import com.example.james.wodrecordapp.utils.DateTimeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jimmy on 25/01/2018.
 */

public class RecordWODFrag extends BaseFragment implements RealmCallback, RecordMvpView {

    // Bind the views
    @BindView(R.id.spinner_wod_exercise) Spinner spinnerWodExercise;
    @BindView(R.id.spinner_wod_sets) Spinner spinnerWodSets;
    @BindView(R.id.btn_add_wod) Button btn_addWod;
    @BindView(R.id.txt_wod_date) TextView txt_wod_date;
    @BindView(R.id.txt_weight) EditText txt_wod_weight;
    @BindView(R.id.txt_wod_details) EditText txt_wod_details;
    @BindView(R.id.txt_wod_time) EditText txt_wod_time;


    private Context context;
    private Resources res;

    private Workout workout;

    @Inject
    public RecordWODFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get new workout object
        //workout = recordWODPresenter.getWorkout();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.record_wod_frag, container, false);

        setUnBinder(ButterKnife.bind(this, root));

        //recordWODPresenter.onAttach(this);

        context = root.getContext();
        res = getResources();

        final String[] WodArray = res.getStringArray(R.array.skills_array);
        final String[] WodSetsArray= res.getStringArray(R.array.sets_array);

        //Set up the spinner
        final ArrayAdapter<String> wodExercisesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,
                WodArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodExercise.setAdapter(wodExercisesAdapter);

        //Set up the sets spinner
        final ArrayAdapter<String> wodSetsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,
                WodSetsArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodSets.setAdapter(wodSetsAdapter);

        txt_wod_date.setOnClickListener((View v) -> {
            //recordWODPresenter.displayCalender(context, workout);
        });

        btn_addWod.setOnClickListener((View v) -> {
            String sets = spinnerWodSets.getSelectedItem().toString();
            String exercise = spinnerWodExercise.getSelectedItem().toString();
            String weight = txt_wod_weight.getText().toString();
            String details = txt_wod_details.getText().toString();
            String time = txt_wod_time.getText().toString();

            //recordWODPresenter.addWOD(sets, exercise, weight, details, time);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWodDate(DateTimeUtils.getCurrentDate());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateWodDate(String dateTime) {
        txt_wod_date.setText(dateTime);
    }

    @Override
    public void showDateTimePicker(SublimePickerFragment sublimePickerFrag) {
        sublimePickerFrag.show(getActivity().getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    @Override
    public void Failure(Throwable e, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    @Override
    public void Success() {
        Toast.makeText(context, getString(R.string.record_wod), Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

}
