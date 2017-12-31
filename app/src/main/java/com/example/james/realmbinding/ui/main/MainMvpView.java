package com.example.james.realmbinding.ui.main;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.data.model.Workout;

import java.util.List;

/**
 * Created by buxtonj on 13/12/2017.
 */

public interface MainMvpView extends MvpView {
    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();

    void showHomeFragment();

    void showToolsFragment();
}
