package com.example.james.realmbinding.view.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import io.realm.RealmConfiguration;

import static com.example.james.realmbinding.utils.Constants.SCANNED_EXERCISE;
import static com.example.james.realmbinding.utils.Constants.SCANNED_TIME;
import static com.example.james.realmbinding.utils.Constants.SCAN_WOD_BUNDLE_KEY;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RealmCallback {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanWod();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void scanWod() {
        Intent i = new Intent(context, OcrCaptureActivity.class);
        startActivityForResult(i, Constants.SCAN_WOD);
    }

    public void recordWod(){
        Intent i = new Intent(context, RecordWOD.class);
        startActivity(i);
    }

    public void viewProgress() {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_record_wod:
                recordWod();
                break;
            case R.id.nav_view_progress:
                viewProgress();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
