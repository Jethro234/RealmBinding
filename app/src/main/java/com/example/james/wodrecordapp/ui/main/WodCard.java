package com.example.james.wodrecordapp.ui.main;

import android.widget.TextView;

import com.example.james.wodrecordapp.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by jimmy on 04/03/2018.
 */

@NonReusable
@Layout(R.layout.wod_card_view)
public class WodCard {
    private static final String TAG = WodCard.class.getSimpleName();

    @View(R.id.txt_wod_name) private TextView txt_wod_name;
    @View(R.id.txt_wod_details) private TextView txt_wod_details;
    @View(R.id.txt_wod_type) private TextView txt_wod_type;
    @View(R.id.txt_wod_date) private TextView txt_wod_date;

    private WOD mWod;

    public WodCard(WOD wod) {
        this.mWod = wod;
    }

    @Resolve
    private void onResolved() {
        txt_wod_name.setText(mWod.getName());
        txt_wod_details.setText(mWod.getDetails());
        txt_wod_type.setText(mWod.getType());
        txt_wod_date.setText(mWod.getDatetime());
    }
}
