package com.example.james.wodrecordapp.ui.main.ui.screens;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.james.wodrecordapp.R;
import com.example.james.wodrecordapp.ui.base.BaseFragment;
import com.example.james.wodrecordapp.ui.main.ui.presenter.MainMvpPresenter;
import com.example.james.wodrecordapp.utils.ScreenUtils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainActivityFrag extends BaseFragment implements MainMvpView {

    @BindView(R.id.wod_cards_container) SwipePlaceHolderView wod_cards_container;

    @Inject
    MainMvpPresenter mainMvpPresenter;

    private Context context;

    @Inject
    public MainActivityFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_activity_frag, container, false);

        setUnBinder(ButterKnife.bind(this, root));

        context = root.getContext();

        setupCardContainerView();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.home_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.reload_questions:
                reloadQuestions();
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupCardContainerView() {
        int screenWidth = ScreenUtils.getScreenWidth(context);
        int screenHeight = ScreenUtils.getScreenHeight(context);

        wod_cards_container.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth((int) (0.90 * screenWidth))
                        .setViewHeight((int) (0.75 * screenHeight))
                        .setPaddingTop(20)
                        .setSwipeRotationAngle(10)
                        .setRelativeScale(0.01f));


        reloadQuestions();
    }

    public void reloadQuestions() {
        mainMvpPresenter.getListOfWods();

        /*for (WOD wod : response.wods) {
            wod_cards_container.addView(new WodCard(wod));
        }

        ScaleAnimation animation = new ScaleAnimation(
                1.15f, 1, 1.15f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        wod_cards_container.setAnimation(animation);
        animation.setDuration(100);
        animation.start();*/
    }
}
