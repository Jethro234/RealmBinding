package com.example.james.realmbinding.ui.main;

import com.example.james.realmbinding.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;

/**
 * Created by jimmy on 31/12/2017.
 */

@NonReusable
@Layout(R.layout.wod_card_view)
public class WodCard {

    private static final String TAG = WodCard.class.getSimpleName();

    public WodCard() {
    }

    @Resolve
    private void onResolved() {

    }
}
