package com.example.iot_exam;

import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDataHandler {

    JsonDataHandler(){

    }

    public DataPoint[] jsonHandler(String response, String field){

        DataPoint[] points = new DataPoint[10];

        try {
            JSONObject json = new JSONObject(response);
            JSONArray feeds = json.getJSONArray("feeds");

            points = dpArrayMaker(feeds, field);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return points;
    }

    public DataPoint[] dpArrayMaker(JSONArray feeds, String field) throws JSONException, ParseException {
        DataPoint[] points = new DataPoint[10];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < feeds.length(); i++) {
            JSONObject object = feeds.getJSONObject(i);

            String date = object.getString("created_at");
            date = date.replace("T", " ");
            date = date.replace("Z", "");
            Date d = formatter.parse(date);

            double value = object.getDouble(field);

            points[i] = new DataPoint(d, value);
        }
        return points;
    }
}
