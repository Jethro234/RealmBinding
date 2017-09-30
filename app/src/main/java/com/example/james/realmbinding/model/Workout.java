package com.example.james.realmbinding.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Project: Workout Logger App
 * Created by James on 07-Aug-16.
 */
public class Workout extends RealmObject{
    @PrimaryKey
    private long id;
    private String wodDateTime;
    private String wodExercise;

    public static class Fields {
        public static final String ID = "id";
        public static final String WODDATETIME = "wodDateTime";
        public static final String WODEXERCISE = "wodExercise";
    }

    public Workout() {
    }

    public Workout(long id, String wodDateTime, String wodExercise) {
        this.id = id;
        this.wodDateTime = wodDateTime;
        this.wodExercise = wodExercise;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWodDateTime() {
        return wodDateTime;
    }

    public void setWodDateTime(String wodDateTime) {
        this.wodDateTime = wodDateTime;
    }

    public String getWodExercise() {
        return wodExercise;
    }

    public void setWodExercise(String wodExercise) {
        this.wodExercise = wodExercise;
    }
}
