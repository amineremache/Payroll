package com.elcsresearch.mouachir.payroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.mian_activity);


        /* // Create and initialize the ListView Adapter

        ArrayList<String> items = new ArrayList<String>();
        items.add("Amine");
        items.add("Mounir");

        ArrayAdapter<String> list =  new ArrayAdapter<String>(this, R.layout.item_employee,items);

        // Find the LiswtView

        ListView listview = (ListView) findViewById(R.id.list_of_employees);
        listview.setAdapter(list); */

        //Construct the data source
        ArrayList<Employee> arrayOfEmp = Employee.getEmployees();


        //Create the adapter to convert the array to view
        EmployeeAdapter adapter = new EmployeeAdapter(this,arrayOfEmp);

        //Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_of_employees);
        listView.setAdapter(adapter);





    }


}
