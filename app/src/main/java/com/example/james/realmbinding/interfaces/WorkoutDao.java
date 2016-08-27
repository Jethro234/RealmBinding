package com.example.james.realmbinding.interfaces;

import com.example.james.realmbinding.model.Workout;
import com.example.james.realmbinding.modelview.WorkoutViewModel;

import java.util.List;

/**
 * Project: Crossfit Calendar App
 * Created by James on 27-Aug-16.
 */
public interface WorkoutDao {
    List<Workout> queryWorkout(long id);
    void deleteWorkout(WorkoutViewModel workoutViewModel, RealmCallback realmCallback);
    void updateWorkout(WorkoutViewModel workoutViewModel, RealmCallback realmCallback);
    void insertWorkout(WorkoutViewModel workoutViewModel, RealmCallback realmCallback);
}
