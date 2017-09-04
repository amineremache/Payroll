package com.elcsresearch.mouachir.payroll;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeesList extends AppCompatActivity  {

    private String TAG = EmployeesList.class.getSimpleName();
    private ListView listView;

    private static String url = "http://192.168.1.7:8000/mobile";

    ArrayList<HashMap<String, String>> employeesList;

    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);
        getSupportActionBar().setTitle(R.string.emp_list_activity);

        employeesList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.activity_list_of_employees);

        new GetEmployees().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent empDetail = new Intent(getApplicationContext(),EmployeeDetail.class);


                empDetail.putExtra("Employee",employeesList.get(0));


                startActivity(empDetail);
            }
        });
    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetEmployees extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(EmployeesList.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url :\n " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray employees = jsonObj.getJSONArray("Employees");

                    // looping through All Employees

                    for (int i = 0; i < employees.length(); i++) {

                        JSONObject JSONObj_iterator = employees.getJSONObject(i);

                        String lname = JSONObj_iterator.getString("LastName");
                        String fname = JSONObj_iterator.getString("FirstName");
                        String bdate = JSONObj_iterator.getString("BDate");

                        // tmp hash map for single employee
                        HashMap<String, String> employeeMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        employeeMap.put("LastName", lname);
                        employeeMap.put("FirstName", fname);
                        employeeMap.put("BDate", bdate);

                        // adding employee to employees list
                        employeesList.add(employeeMap);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error : " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "JSON parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get JSON from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get JSON from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(

                    EmployeesList.this,
                    employeesList,
                    R.layout.item_employee,
                    new String[]{"LastName", "FirstName", "BDate"},
                    new int[] {R.id.emp_nom, R.id.emp_prenom, R.id.emp_date_naiss});

            listView.setAdapter(adapter);
        }

    }

}
