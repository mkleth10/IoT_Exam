package com.example.iot_exam;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;

public class GraphLayout {

    String axis_title;

    GraphLayout(String axis_title){
        this.axis_title = axis_title;
    };

    public GraphView beautifier(GraphView graph, LineGraphSeries<DataPoint> series, SimpleDateFormat formatter){
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext(), formatter));
        graph.getGridLabelRenderer().setNumHorizontalLabels(10);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph.getGridLabelRenderer().setVerticalAxisTitle(axis_title);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(135);
        graph.getViewport().setMinX(series.getLowestValueX());
        graph.getViewport().setMaxX(series.getHighestValueX());
        graph.getViewport().setXAxisBoundsManual(false);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.getGridLabelRenderer().setHumanRounding(false);
        return graph;
    }

}
