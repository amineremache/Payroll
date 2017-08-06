package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EmployeePayrolls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_payrolls);
        getSupportActionBar().setTitle(R.string.emp_payrolls_activity);
    }
}
