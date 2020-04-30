package com.example.iot_exam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        setContentView(R.layout.temp_layout);

        GraphView graph = (GraphView) findViewById(R.id.temp_graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,1),
                new DataPoint(1,2),
                new DataPoint(2,3)
        });

        graph.addSeries(series);

    }

}
