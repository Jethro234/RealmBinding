package com.example.james.realmbinding.data;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.interfaces.RealmCallback;
import com.example.james.realmbinding.interfaces.WorkoutDao;
import com.example.james.realmbinding.model.Workout;
import com.example.james.realmbinding.modelview.WorkoutViewModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
 * Project: Workout Logger App
 * Created by James on 27-Aug-16.
 */
public class WorkoutDaoImpl implements WorkoutDao {

    private Context context;
    private Resources res;
    private RealmConfiguration realmConfiguration;

    public WorkoutDaoImpl(Context context, RealmConfiguration realmConfiguration) {
        this.context = context;
        this.res = context.getResources();
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public List<Workout> queryWorkout(long id) {
        final Realm realm = Realm.getInstance(realmConfiguration);
        List<Workout> workouts = (id == -1) ? realm.copyFromRealm(realm.where(Workout.class).findAll())
                : realm.copyFromRealm(realm.where(Workout.class).equalTo(Workout.Fields.ID, id).findAll());
        realm.close();
        return workouts;
    }

    @Override
    public void insertWorkout(final WorkoutViewModel workoutViewModel, final RealmCallback realmCallback) {
        final Realm realm = Realm.getInstance(realmConfiguration);
        try {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    long initialId = (realm.where(Workout.class).max("id") != null)
                            ? realm.where(Workout.class).max("id").longValue() + 1 : 0;

                    Workout workout = realm.createObject(Workout.class, initialId);
                    workout.setWodDateTime(workoutViewModel.getWodDateTime());
                    workout.setWodExercise(workoutViewModel.getWodExercise());
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    realm.close();
                    if (realm.isClosed()) {
                        realmCallback.Success();
                    } else {
                        realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                    }
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    realm.close();
                    if (realm.isClosed()) {
                        realmCallback.Failure(error, error.getMessage());
                    } else {
                        realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                    }
                }
            });
        } catch (Exception e) {
            Log.e(WorkoutDaoImpl.class.getSimpleName(), e.toString());
        }
    }

    @Override
    public void updateWorkout(final WorkoutViewModel workoutViewModel, final RealmCallback realmCallback) {
        final Realm realm = Realm.getInstance(realmConfiguration);
        try {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(workoutViewModel.getWorkout());
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    realm.close();
                    if (realm.isClosed()) {
                        realmCallback.Success();
                    } else {
                        realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                    }
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    realm.close();
                    if (realm.isClosed()) {
                        realmCallback.Failure(error, error.getMessage());
                    } else {
                        realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                    }
                }
            });
        } catch (Exception e) {
            Log.e(WorkoutDaoImpl.class.getSimpleName(), e.toString());
        }
    }

    @Override
    public void deleteWorkout(final WorkoutViewModel workoutViewModel, final RealmCallback realmCallback) {
        final Realm realm = Realm.getInstance(realmConfiguration);
        try {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.where(Workout.class)
                            .equalTo(Workout.Fields.ID, workoutViewModel.getWorkoutId())
                            .findAll()
                            .deleteAllFromRealm();
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    realm.close();
                    if (realm.isClosed()) {
                        realmCallback.Success();
                    } else {
                        realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                    }
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    realm.close();
                    if (realm.isClosed()) {
                        realmCallback.Failure(error, error.getMessage());
                    } else {
                        realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                    }
                }
            });
        } catch (Exception e) {
            Log.e(WorkoutDaoImpl.class.getSimpleName(), e.toString());
        }
    }
}
