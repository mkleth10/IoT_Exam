package com.example.iot_exam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;

public class HumActivity extends AppCompatActivity {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM HH:mm:ss");
    DataPoint[] hum_points;
    JsonDataHandler dataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);
        setTitle("Humidity");

        GraphView graph = (GraphView) findViewById(R.id.graph);

        String json = getIntent().getStringExtra("json");

        dataHandler = new JsonDataHandler();
        hum_points = dataHandler.jsonHandler(json, "field2");

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(hum_points);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);

        graph.addSeries(series);

        GraphLayout gl = new GraphLayout("%");
        graph = gl.beautifier(graph, series, formatter);
    }
}
