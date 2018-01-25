package com.example.james.realmbinding.ui.record;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.interfaces.RealmCallback;
import com.example.james.realmbinding.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jimmy on 25/01/2018.
 */

public class RecordWODFrag extends BaseFragment implements RecordMvpView {

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

    @Inject
    public RecordWODFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.record_wod_frag, container, false);

        setUnBinder(ButterKnife.bind(this, root));

        context = root.getContext();
        res = getResources();

        final String[] WodArray = res.getStringArray(R.array.skills_array);
        final String[] WodSetsArray= res.getStringArray(R.array.sets_array);

        //Set up the spinner
        final ArrayAdapter<String> wodExercisesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,
                WodArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodExercise.setAdapter(wodExercisesAdapter);

        //Set up the sets spinner
        final ArrayAdapter<String> wodSetsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,
                WodSetsArray);
        wodExercisesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerWodSets.setAdapter(wodSetsAdapter);

        txt_wod_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //displayCalender(context);
            }
        });

        btn_addWod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*workout.setWodSets(spinnerWodSets.getSelectedItem().toString());
                workout.setWodExercise(spinnerWodExercise.getSelectedItem().toString());
                workout.setWodWeight(txt_wod_weight.getText().toString());
                workout.setWodDetails(txt_wod_details.getText().toString());
                workout.setWodTime(txt_wod_time.getText().toString());
                recordWODPresenter.insertOrUpdateWorkout(workout, (RealmCallback)context);*/
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateWodDate(String dateTime) {
        txt_wod_date.setText(dateTime);
    }
}
