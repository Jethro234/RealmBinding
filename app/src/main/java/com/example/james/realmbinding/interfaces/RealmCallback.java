package com.example.james.realmbinding.interfaces;

/**
 * Project: Crossfit Calendar App
 * Created by James on 27-Aug-16.
 */
public interface RealmCallback {
    void Success();
    void Failure(Throwable error, String errorMessage);
}
