package com.example.james.wodrecordapp.ui.main.customviews;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.james.wodrecordapp.ui.main.ui.MainActivity;

/**
 * Created by buxtonj on 11/12/2017.
 */

public class SmoothActionBarDrawerToggle extends ActionBarDrawerToggle {

    private MainActivity mainActivity;

    public SmoothActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
    }

    @Override
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        mainActivity.replaceFragment();
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        super.onDrawerStateChanged(newState);
    }
}
