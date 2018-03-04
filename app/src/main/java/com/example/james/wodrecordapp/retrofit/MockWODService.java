package com.example.james.wodrecordapp.retrofit;

import android.content.Context;

import com.example.james.wodrecordapp.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jimmy on 04/03/2018.
 */

public class MockWODService {

    public static String getWODs(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("wods.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, "UTF-8");
            return jsonString;
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getWODJson(Context context) {
        try {
            JSONObject jsonObject = new JSONObject(getWODs(context));
            return jsonObject.getJSONArray(Constants.WOSJSONArray);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
