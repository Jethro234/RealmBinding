package com.example.james.realmbinding.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class ToolsFragment extends BaseFragment {

    @Inject
    MainActivityPresenter mainActivityPresenter;

    private Context context;

    @Inject
    public ToolsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tools_frag, container, false);

        setUnBinder(ButterKnife.bind(this, root));

        context = root.getContext();

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
