package com.example.james.realmbinding.scan.utils;

import android.content.Context;

import com.example.james.realmbinding.scan.OcrCaptureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 30/09/2017.
 */

public class DetectedGestureArrayList extends ArrayList<String> {

    private Context context;
    private List<String> prePopulatedItems = new ArrayList<>();

    public DetectedGestureArrayList() {
    }

    public DetectedGestureArrayList(List<String> prePopulatedItems) {
        this.prePopulatedItems = prePopulatedItems;
        this.addAll(prePopulatedItems);
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

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();

        for (int i = 0; i < super.size(); i++) {
            data.append(super.get(i) ).append(" ");
        }

        return data.toString();
    }
}
