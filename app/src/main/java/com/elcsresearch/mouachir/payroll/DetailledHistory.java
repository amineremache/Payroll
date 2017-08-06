package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailledHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailled_history);
        getSupportActionBar().setTitle(R.string.detail_activity);
    }
}
