package com.example.james.wodrecordapp.data;

import com.example.james.wodrecordapp.data.interfaces.RealmCallback;
import com.example.james.wodrecordapp.data.model.Workout;

import java.util.List;

/**
 * Project: Workout Logger App
 * Created by James on 27-Aug-16.
 */
public interface WorkoutDao {
    List<Workout> queryWorkout(long id);

    void deleteWorkout(Workout workout, RealmCallback realmCallback);

    void insertOrUpdateWorkout(Workout workout, RealmCallback realmCallback);
}
