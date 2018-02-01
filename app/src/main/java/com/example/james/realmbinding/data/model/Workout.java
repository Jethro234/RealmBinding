package com.example.james.realmbinding.data.model;

import com.example.james.realmbinding.utils.DateTimeUtils;

import io.realm.Realm;
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
    private String wodSets;
    private String wodExercise;
    private String wodWeight;
    private String wodDetails;
    private String wodTime;

    public static class Fields {
        public static final String ID = "id";
        public static final String WODDATETIME = "wodDateTime";
        public static final String WODSETS = "wodSets";
        public static final String WODEXERCISE = "wodExercise";
        public static final String WODWEIGHT = "wodWeight";
        public static final String WODDETAILS = "wodDetails";
        public static final String WODTIME = "wodTime";
    }

    public Workout() {
        this.id = (Realm.getDefaultInstance().where(Workout.class).max("id") != null)
                ? Realm.getDefaultInstance().where(Workout.class).max("id").longValue() + 1 : 0;
        this.wodDateTime = DateTimeUtils.getCurrentDate();
    }

    public Workout(String wodDateTime) {
        this.id = (Realm.getDefaultInstance().where(Workout.class).max("id") != null)
                ? Realm.getDefaultInstance().where(Workout.class).max("id").longValue() + 1 : 0;
        this.wodDateTime = wodDateTime;
    }

    public Workout(long id, String wodDateTime, String wodSets, String wodExercise, String wodWeight, String wodDetails, String wodTime) {
        this.id = id;
        this.wodDateTime = wodDateTime;
        this.wodExercise = wodExercise;
        this.wodSets = wodSets;
        this.wodWeight = wodWeight;
        this.wodDetails = wodDetails;
        this.wodTime = wodTime;
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

    public String getWodSets() {
        return wodSets;
    }

    public void setWodSets(String wodSets) {
        this.wodSets = wodSets;
    }

    public String getWodExercise() {
        return wodExercise;
    }

    public void setWodExercise(String wodExercise) {
        this.wodExercise = wodExercise;
    }

    public String getWodWeight() {
        return wodWeight;
    }

    public void setWodWeight(String wodWeight) {
        this.wodWeight = wodWeight;
    }

    public String getWodDetails() {
        return wodDetails;
    }

    public void setWodDetails(String wodDetails) {
        this.wodDetails = wodDetails;
    }

    public String getWodTime() {
        return wodTime;
    }

    public void setWodTime(String wodTime) {
        this.wodTime = wodTime;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) id;
        result = 31 * result + wodDetails.hashCode();
        result = 31 * result + wodExercise.hashCode();
        result = 31 * result + wodWeight.hashCode();
        result = 31 * result + wodDateTime.hashCode();
        result = 31 * result + wodSets.hashCode();
        result = 31 * result + wodTime.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof Workout)) {
            return false;
        }

        Workout workout = (Workout) o;

        return workout.getId() == id &&
                (workout.getWodDetails() != null) ? workout.getWodDetails().equals(wodDetails) : wodDetails == null &&
                (workout.getWodExercise() != null) ? workout.getWodExercise().equals(wodExercise) : wodExercise == null &&
                (workout.getWodWeight() != null) ? workout.getWodWeight().equals(wodWeight) : wodWeight == null &&
                (workout.getWodDateTime() != null) ? workout.getWodDateTime().equals(wodDateTime) : wodDateTime == null &&
                (workout.getWodSets() != null) ? workout.getWodSets().equals(wodSets) : wodSets == null &&
                (workout.getWodTime() != null) ? workout.getWodTime().equals(wodTime) : wodTime == null;

    }
}
