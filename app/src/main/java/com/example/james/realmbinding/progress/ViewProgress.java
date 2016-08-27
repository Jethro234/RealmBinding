package com.example.james.realmbinding.progress;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.data.WorkoutDaoImpl;
import com.example.james.realmbinding.databinding.ViewProgressBinding;
import com.example.james.realmbinding.interfaces.WorkoutDao;
import com.example.james.realmbinding.model.Workout;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Project: Crossfit Calendar App
 * Created by James on 14-Aug-16.
 */
public class ViewProgress extends AppCompatActivity {
    private ViewProgressBinding viewProgressBinding;
    private Context mContext;
    private WorkoutDao workoutDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewProgressBinding = DataBindingUtil.setContentView(this, R.layout.view_progress);
        mContext = this;

        // Set realm config
        RealmConfiguration config = new RealmConfiguration.Builder(mContext).build();

        workoutDao = new WorkoutDaoImpl(config);

        List<Workout> workoutRealmResults = workoutDao.queryWorkout(-1);

        for (Workout workout : workoutRealmResults) {
            Toast.makeText(mContext,
                    String.format(Locale.UK, "Workout ID - %d: Exercise - %s", workout.getId(), workout.getWodExercise()),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
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
