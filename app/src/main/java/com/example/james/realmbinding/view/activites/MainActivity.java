package com.example.james.realmbinding.view.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.interfaces.RealmCallback;
import com.example.james.realmbinding.model.Workout;
import com.example.james.realmbinding.modelview.WorkoutViewModel;
import com.example.james.realmbinding.progress.ViewProgress;
import com.example.james.realmbinding.scan.OcrCaptureActivity;
import com.example.james.realmbinding.scan.utils.DetectedGestureArrayList;
import com.example.james.realmbinding.utils.Constants;
import com.example.james.realmbinding.workout.RecordWOD;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmConfiguration;

import static com.example.james.realmbinding.utils.Constants.*;

/**
 * Project: Workout Logger App
 * Created by James on 06-Aug-16.
 */
public class MainActivity extends AppCompatActivity implements RealmCallback {

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        context = this;
    }

    public void onScanWodClick(View view) {
        Intent i = new Intent(context, OcrCaptureActivity.class);
        startActivityForResult(i, Constants.SCAN_WOD);
    }

    public void onRecordWodClick(View view){
        Intent i = new Intent(context, RecordWOD.class);
        startActivity(i);
    }

    public void onViewProgress(View view) {
        Intent i = new Intent(context, ViewProgress.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.SCAN_WOD) {
            if (resultCode == RESULT_OK) {
                Bundle scan_wod_bundle = data.getBundleExtra(SCAN_WOD_BUNDLE_KEY);
                DetectedGestureArrayList confirmedExercises = new DetectedGestureArrayList(scan_wod_bundle.getStringArrayList(SCANNED_EXERCISE));
                DetectedGestureArrayList confirmedTime = new DetectedGestureArrayList(scan_wod_bundle.getStringArrayList(SCANNED_TIME));



                // Obtain realm instance
                RealmConfiguration config = new RealmConfiguration.Builder(context).build();
                WorkoutDaoImpl workoutDao = new WorkoutDaoImpl(context, config);

                WorkoutViewModel workoutViewModel = new WorkoutViewModel(new Workout());

                DateTime dateTime = new DateTime();
                workoutViewModel.setWodDateTime(String.format("%s", dateTime.toLocalDate().toString()));

                if (confirmedExercises.size() > 0) {
                    workoutViewModel.setWodDetails(confirmedExercises.toString());
                }

                if (confirmedTime.size() > 0) {
                    workoutViewModel.setWodTime(confirmedTime.toString());
                }

                workoutDao.insertWorkout(workoutViewModel, (RealmCallback)context);
            }
        }
    }

    @Override
    public void Success() {
        Toast.makeText(context, "Record added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failure(Throwable error, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        error.printStackTrace();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
