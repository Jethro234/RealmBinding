package com.example.james.wodrecordapp.ui.scan.interfaces;

import java.util.List;

/**
 * Created by jimmy on 01/10/2017.
 */

public interface ScannedResultsCallback {
    void processSelection(String key, List<String> results);
}
