package com.example.james.wodrecordapp.ui.main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jimmy on 04/03/2018.
 */

public class WODResponse {
    @SerializedName("wods")
    List<WOD> wods;
}
