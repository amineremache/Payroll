package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class EmployeeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        getSupportActionBar().setTitle(R.string.detail_activity);

        HashMap< String,String > myEmp = (HashMap<String,String>) getIntent().getSerializableExtra("Employee");

        TextView lname = (TextView) findViewById(R.id.emp_det_lname);
        TextView fname = (TextView) findViewById(R.id.emp_det_fname);
        TextView bdate = (TextView) findViewById(R.id.emp_det_bdate);

        lname.setText(myEmp.get("LastName"));
        fname.setText(myEmp.get("FirstName"));
        bdate.setText(myEmp.get("BDate"));

    }
}
