package com.example.james.realmbinding.scan.utils;

import android.content.Context;

import com.example.james.realmbinding.scan.OcrCaptureActivity;

import java.util.ArrayList;

/**
 * Created by jimmy on 30/09/2017.
 */

public class DetectedGestureArrayList extends ArrayList<String> {

    private Context context;

    public DetectedGestureArrayList() {
    }

    public DetectedGestureArrayList(Context context) {
        this.context = context;
    }

    @Override
    public boolean add(String s) {
        boolean result = super.add(s);
        ((OcrCaptureActivity) context).addGesture(s);
        return result;
    }
}
