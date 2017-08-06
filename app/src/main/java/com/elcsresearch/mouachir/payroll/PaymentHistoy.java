package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PaymentHistoy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_histoy);
        getSupportActionBar().setTitle(R.string.history_activity);
    }
}
