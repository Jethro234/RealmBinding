package com.example.james.wodrecordapp.data;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.R;
import com.example.james.wodrecordapp.data.interfaces.RealmCallback;
import com.example.james.wodrecordapp.data.model.Workout;
import com.example.james.wodrecordapp.ui.base.BaseActivity;
import com.example.james.wodrecordapp.ui.base.BaseFragment;

import java.util.List;

import io.realm.Realm;

/**
 * Project: Workout Logger App
 * Created by James on 27-Aug-16.
 */
public class WorkoutDaoImpl implements WorkoutDao {

    private Context context;
    private Resources res;

    public WorkoutDaoImpl(MvpView mvpView) {
        this.context = (mvpView instanceof BaseFragment) ? ((BaseFragment) mvpView).getContext()
                : ((BaseActivity) mvpView).getBaseContext();
        this.res = context.getResources();
    }

    @Override
    public List<Workout> queryWorkout(long id) {
        final Realm realm = Realm.getDefaultInstance();
        return  (id == -1) ? realm.where(Workout.class).findAll() : realm.where(Workout.class).equalTo(Workout.Fields.ID, id).findAll();
    }

    @Override
    public void insertOrUpdateWorkout(final Workout workout, final RealmCallback realmCallback) {
        final Realm realmInstance = Realm.getDefaultInstance();
        try {
            realmInstance.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(workout);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    realmCallback.Success();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    realmCallback.Failure(null, res.getString(R.string.error_realm_cannot_close));
                }
            });
        } catch (Exception e) {
            Log.e(WorkoutDaoImpl.class.getSimpleName(), e.toString());
        }
    }

    @Override
    public void deleteWorkout(final Workout workout, final RealmCallback realmCallback) {
        final Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.where(Workout.class)
                            .equalTo(Workout.Fields.ID, workout.getId())
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
