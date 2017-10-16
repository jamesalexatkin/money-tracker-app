package com.example.jamesatkin.monies.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jamesatkin.monies.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class StatisticsActivity extends AppCompatActivity {

    private ArrayList<BarEntry> entries = new ArrayList<>();
    BarDataSet dataset = new BarDataSet(entries, "£ spent per type");
    private BarChart chart;
    private BarData data;
    private ArrayList<String> labels;
    private String period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        initPeriodField();
        initGraph();
    }

    private void initGraph() {
        chart = (BarChart) findViewById(R.id.graph);

        period = "All time";

        float[] costs = MainActivity.db.getByPeriod(period);
        makeBars(costs);
        updateGraph();
    }

    protected void initPeriodField() {
        // Set up spinner
        Spinner dropdown = (Spinner) findViewById(R.id.spinner_Period);
        ArrayList<String> periods = new ArrayList<>(Arrays.asList("Week", "Month", "Year", "All time"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, periods);
        dropdown.setAdapter(adapter);
    }

    public void onGoClicked(View view) {
        // Gets content of spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_Period);
        period = spinner.getSelectedItem().toString();

        float[] costs = MainActivity.db.getByPeriod(period);
        makeBars(costs);
        updateGraph();
    }

    private void makeBars(float[] costs) {
        String[] typeNames = MainActivity.getTypeNames();
        ArrayList<BarEntry> bars = new ArrayList<BarEntry>();

        for (int i = 0; i < typeNames.length; i++) {
            BarEntry b = new BarEntry(i, costs[i]);
            bars.add(b);
        }

        entries = bars;
        dataset = new BarDataSet(bars, "£ spent per type");
        labels = new ArrayList<String>(Arrays.asList(typeNames));
    }

    private void updateGraph() {
        // Colors
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        // Add data to chart
        data = new BarData(dataset);
        chart.setData(data);

        // Sets x-axis labels
        XAxis x = chart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        x.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int)value);
            }
        });
        x.setGranularity(1f);

        // Description
        Description desc = new Description();
        desc.setText("");
        chart.setDescription(desc);

        // Animation
        chart.animateY(2000);

        // Title
        setTitle();
    }

    private void setTitle() {
        String title;
        String append = "";
        switch(period) {
            case "Week":
                append = "Weekly";
                break;
            case "Month":
                append = "Monthly";
                break;
            case "Year":
                append = "Yearly";
                break;
            case "All time":
                append = "All Time";
                break;
            default:
                break;
        }
        title = append + " Breakdown";

        TextView txtTitle = (TextView) findViewById(R.id.txt_title);
        txtTitle.setText(title);
    }
}
