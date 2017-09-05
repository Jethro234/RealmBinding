package com.example.james.realmbinding.modelview;

import com.example.james.realmbinding.model.Workout;

/**
 * Project: Crossfit Calendar App
 * Created by James on 14-Aug-16.
 */
public class WorkoutViewModel {

    private final Workout workout;

    public WorkoutViewModel(Workout workout) {
        this.workout = workout;
    }

    public Workout getWorkout() {
        return workout;
    }

    public long getWorkoutId() {
        return workout.getId();
    }

    public void setWorkoutId(long id) {
        workout.setId(id);
    }

    public String getWodDateTime() {
        return workout.getWodDateTime();
    }

    public void setWodDateTime(String wodDateTime) {
        workout.setWodDateTime(wodDateTime);
    }

    public String getWodExercise() {
        return workout.getWodExercise();
    }

    public void setWodExercise(String wodExercise) {
        workout.setWodExercise(wodExercise);
    }
}
