package com.example.iot_exam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;

public class LpgActivity extends AppCompatActivity {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM HH:mm:ss");
    DataPoint[] lpg_points;
    JsonDataHandler dataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);
        setTitle("LPG");

        GraphView graph = (GraphView) findViewById(R.id.graph);

        String json = getIntent().getStringExtra("json");

        dataHandler = new JsonDataHandler();
        lpg_points = dataHandler.jsonHandler(json, "field3");

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(lpg_points);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);

        graph.addSeries(series);

        GraphLayout gl = new GraphLayout("LPG");
        graph = gl.beautifier(graph, series, formatter);
    }
}
