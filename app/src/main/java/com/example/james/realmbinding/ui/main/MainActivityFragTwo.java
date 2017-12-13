package com.example.james.realmbinding.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainActivityFragTwo extends BaseFragment {

    @Inject
    public MainActivityFragTwo() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_activity_frag_two, container, false);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
