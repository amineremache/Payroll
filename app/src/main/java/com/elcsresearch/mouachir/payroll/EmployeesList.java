package com.elcsresearch.mouachir.payroll;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.input;

public class EmployeesList extends AppCompatActivity  {

    private String TAG = EmployeesList.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView listView;

    private static String url = "http://10.0.2.2:8000/mobile";

    ArrayList<HashMap<String, String>> employeesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);
        getSupportActionBar().setTitle(R.string.emp_list_activity);

        employeesList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_of_employees);

        new GetEmployees().execute();
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
                    new int[] {R.id.emp_lname, R.id.emp_fname, R.id.emp_bdate});

            listView.setAdapter(adapter);
        }

    }

}
