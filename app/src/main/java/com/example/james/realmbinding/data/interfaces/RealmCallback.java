package com.example.james.realmbinding.data.interfaces;

/**
 * Project: Workout Logger App
 * Created by James on 27-Aug-16.
 */
public interface RealmCallback {
    void Success();
    void Failure(Throwable error, String errorMessage);
}
