package com.example.james.realmbinding.scan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.james.realmbinding.R;
import com.example.james.realmbinding.scan.OcrCaptureActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 01/10/2017.
 */

public class CaptureTools {

    public static void displayDialog(final Context context, final String message, List<String> gestureResults) {
        final List<String> confirmedResults = new ArrayList<>();
        final CharSequence[] items = gestureResults.toArray(new CharSequence[gestureResults.size()]);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message);
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if (b) {
                    confirmedResults.add(items[i].toString());
                } else {
                    confirmedResults.remove(items[i].toString());
                }
            }
        });

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((OcrCaptureActivity)context).processSelection(message, confirmedResults);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmedResults.clear();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
