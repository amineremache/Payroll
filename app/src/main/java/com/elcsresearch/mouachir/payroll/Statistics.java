package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setTitle(R.string.stats_activity);
    }
}
