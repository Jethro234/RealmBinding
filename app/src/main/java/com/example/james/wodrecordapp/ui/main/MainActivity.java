package com.example.james.wodrecordapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.james.wodrecordapp.R;
import com.example.james.wodrecordapp.data.WorkoutDaoImpl;
import com.example.james.wodrecordapp.data.interfaces.RealmCallback;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.ui.base.BaseActivity;
import com.example.james.wodrecordapp.ui.progress.ViewProgressActivity;
import com.example.james.wodrecordapp.ui.record.RecordWODActivity;
import com.example.james.wodrecordapp.ui.scan.OcrCaptureActivity;
import com.example.james.wodrecordapp.ui.scan.utils.DetectedGestureArrayList;
import com.example.james.wodrecordapp.utils.ActivityUtils;
import com.example.james.wodrecordapp.utils.Constants;

import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.james.wodrecordapp.utils.Constants.SCANNED_EXERCISE;
import static com.example.james.wodrecordapp.utils.Constants.SCANNED_TIME;
import static com.example.james.wodrecordapp.utils.Constants.SCAN_WOD_BUNDLE_KEY;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, RealmCallback, MainMvpView {

    private Context context;
    private SmoothActionBarDrawerToggle smoothToggle;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer_layout;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Inject
    MainActivityFrag mainActivityFrag;

    @Inject
    ToolsFragment toolsFragment;

    @Inject
    MainMvpPresenter mainMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));

        setSupportActionBar(toolbar);

        context = this;

        fab.setOnClickListener((View v) -> scanWod());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        smoothToggle = new SmoothActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(smoothToggle);
        smoothToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        MainActivityFrag cachedFrag =
                (MainActivityFrag) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (cachedFrag == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mainActivityFrag, R.id.contentFrame);
        }
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

        switch (id) {
            case R.id.reload_questions:
                mainActivityFrag.reloadQuestions();
                break;
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void scanWod() {
        Intent i = new Intent(context, OcrCaptureActivity.class);
        startActivityForResult(i, Constants.SCAN_WOD);
    }

    @Override
    public void showHomeFragment() {
        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                mainActivityFrag, R.id.contentFrame);
    }

    @Override
    public void showRecordWodActivity() {
        startActivity(RecordWODActivity.getRecordWodActIntent(this));
    }

    @Override
    public void showToolsFragment() {
        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                toolsFragment, R.id.contentFrame);
    }

    @Override
    public void showViewProgressActivity() {
        startActivity(ViewProgressActivity.getViewProgressActIntent(this));
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
                WorkoutDaoImpl workoutDao = new WorkoutDaoImpl(context);

                Workout workout = new Workout();

                DateTime dateTime = new DateTime();
                workout.setWodDateTime(String.format("%s", dateTime.toLocalDate().toString()));

                if (confirmedExercises.size() > 0) {
                    workout.setWodDetails(confirmedExercises.toString());
                }

                if (confirmedTime.size() > 0) {
                    workout.setWodTime(confirmedTime.toString());
                }

                workoutDao.insertOrUpdateWorkout(workout, this);
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
            case R.id.nav_home:
                mainMvpPresenter.onDrawerOptionHomeClick();
                break;
            case R.id.nav_record_wod:
                mainMvpPresenter.onDrawerOptionRecordWODClick();
                break;
            case R.id.nav_view_progress:
                mainMvpPresenter.onDrawerOptionViewProgressClick();
                break;
            case R.id.nav_tools:
                mainMvpPresenter.onDrawerOptionToolsClick();
                break;
        }

        return true;
    }

    @Override
    public void closeNavigationDrawer() {
        drawer_layout.closeDrawer(Gravity.START);
    }

    @Override
    public void lockDrawer() {

    }

    @Override
    public void unlockDrawer() {

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
