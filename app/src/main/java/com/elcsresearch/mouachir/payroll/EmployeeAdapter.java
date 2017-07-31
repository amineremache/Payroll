package com.elcsresearch.mouachir.payroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by neofly on 7/30/17.
 */

public class EmployeeAdapter extends ArrayAdapter <Employee>{

    public EmployeeAdapter (Context context, ArrayList<Employee> employees) {
        super (context,0,employees);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Employee emp = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_employee, parent, false);

        }

        // Lookup view for data population

        TextView tvLastName = (TextView) convertView.findViewById(R.id.emp_lname);

        TextView tvFirstName = (TextView) convertView.findViewById(R.id.emp_fname);

        // Populate the data into the template view using the data object

        tvLastName.setText(emp.getLast_name());

        tvFirstName.setText(emp.getFirst_name());

        // Return the completed view to render on screen

        return convertView;

    }
}
