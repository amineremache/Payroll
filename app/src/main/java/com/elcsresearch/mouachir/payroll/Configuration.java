package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        getSupportActionBar().setTitle(R.string.config_activity);
    }
}
