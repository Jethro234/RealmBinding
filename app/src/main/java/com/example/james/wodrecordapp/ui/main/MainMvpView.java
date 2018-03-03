package com.example.james.wodrecordapp.ui.main;

import com.example.james.wodrecordapp.MvpView;

/**
 * Created by buxtonj on 13/12/2017.
 */

public interface MainMvpView extends MvpView {
    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();

    void showHomeFragment();

    void showRecordWodActivity();

    void showViewProgressActivity();

    void showToolsFragment();
}
