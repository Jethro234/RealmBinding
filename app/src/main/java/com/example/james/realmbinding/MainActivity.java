package com.example.james.realmbinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.james.realmbinding.progress.ViewProgress;
import com.example.james.realmbinding.scan.OcrCaptureActivity;
import com.example.james.realmbinding.utils.Constants;
import com.example.james.realmbinding.workout.RecordWOD;

import java.util.List;

import static com.example.james.realmbinding.utils.Constants.*;

/**
 * Project: Workout Logger App
 * Created by James on 06-Aug-16.
 */
public class MainActivity extends AppCompatActivity {

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
                //TODO Add this to local storage
                List<String> scannedExercises = scan_wod_bundle.getStringArrayList(SCANNED_EXERCISES);
            }
        }
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
