package com.example.james.realmbinding.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.ui.base.BaseFragment;
import com.example.james.realmbinding.utils.ScreenUtils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by buxtonj on 13/12/2017.
 */

public class MainActivityFrag extends BaseFragment {

    @BindView(R.id.wod_cards_container) SwipePlaceHolderView wod_cards_container;

    private Context context;

    @Inject
    public MainActivityFrag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //todo: implement a better way for adding wod card
        List<WodCard> wodCardList = new ArrayList<>();
        wodCardList.add(new WodCard());
        wodCardList.add(new WodCard());
        wodCardList.add(new WodCard());
        wodCardList.add(new WodCard());
        wodCardList.add(new WodCard());

        for (WodCard wodCard : wodCardList) {
            wod_cards_container.addView(wodCard);
        }

        ScaleAnimation animation = new ScaleAnimation(
                1.15f, 1, 1.15f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        wod_cards_container.setAnimation(animation);
        animation.setDuration(100);
        animation.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
