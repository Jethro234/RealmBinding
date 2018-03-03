package com.example.james.wodrecordapp.ui.record;

import com.example.james.wodrecordapp.MvpView;
import com.example.james.wodrecordapp.ui.calendar.SublimePickerFragment;

/**
 * Created by jimmy on 24/01/2018.
 */

public interface RecordMvpView extends MvpView {
    void updateWodDate(String dateTime);
    void showDateTimePicker(SublimePickerFragment sublimePickerFrag);
}
