package com.example.james.realmbinding.ui.record;

import com.example.james.realmbinding.MvpView;
import com.example.james.realmbinding.ui.calendar.SublimePickerFragment;

/**
 * Created by jimmy on 24/01/2018.
 */

public interface RecordMvpView extends MvpView {
    void updateWodDate(String dateTime);
    void showDateTimePicker(SublimePickerFragment sublimePickerFrag);
}
