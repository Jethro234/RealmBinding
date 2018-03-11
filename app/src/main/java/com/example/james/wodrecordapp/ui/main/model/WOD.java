package com.example.james.wodrecordapp.ui.main.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jimmy on 31/12/2017.
 */

public class WOD {
    private static final String TAG = WOD.class.getSimpleName();

    @SerializedName("name")
    private String name;
    @SerializedName("details")
    private String details;
    @SerializedName("type")
    private String type;
    @SerializedName("datetime")
    private String datetime;

    public WOD() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
