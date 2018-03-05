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
 * This will be used to simulate a web request for the WOD json.
 */

public class MockWODService {

    public static String getWODs(Context context) {
        try {
            // Delay is only used to simulate the wait time from a web request
            Thread.sleep(5000);

            InputStream inputStream = context.getAssets().open("wods.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, "UTF-8");
            return jsonString;
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
