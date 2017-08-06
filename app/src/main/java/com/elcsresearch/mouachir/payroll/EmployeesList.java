package com.elcsresearch.mouachir.payroll;

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

public class EmployeesList extends AppCompatActivity implements LoadJSONTask.Listener,AdapterView.OnItemClickListener {


    private ListView mListView;

    public static final String URL = "https://127.0.0.1:8000/mobile/";

    private List<HashMap<String, String>> mEmployeesMapList = new ArrayList<>();

    private static final String KEY_LNAME = "LastName";
    private static final String KEY_FNAME = "FirstName";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_list);
        getSupportActionBar().setTitle(R.string.emp_list_activity);



        mListView = (ListView) findViewById(R.id.list_of_employees);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, mEmployeesMapList.get(i).get(KEY_LNAME) + " " + mEmployeesMapList.get(i).get(KEY_FNAME),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoaded(List<Employee> empsList) {

        for (Employee emp : empsList) {

            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_LNAME,emp.getLast_name());
            map.put(KEY_FNAME,emp.getFirst_name());

            mEmployeesMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }



    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(EmployeesList.this, mEmployeesMapList, R.layout.item_employee,
                new String[] { KEY_LNAME, KEY_FNAME },
                new int[] { R.id.emp_lname, R.id.emp_fname });

        mListView.setAdapter(adapter);

    }


    /* //Construct the data source
        ArrayList<Employee> arrayOfEmp = Employee.getEmployees();   // THIS IS ONLY FOR TEST */

        /*InputStream in = null;
        JSONObject jo = null;
        JSONArray jsonArray = null;
        String strJson = "INITIAL STRING";
        URL url = null;
        HttpURLConnection conn = null;

        Toast.makeText(getApplicationContext(),strJson,Toast.LENGTH_LONG).show();

        try {

            url = new URL("https://www.google.com/");

            conn = (HttpURLConnection) url.openConnection();

            new MyDownloadTask();

        } catch (IOException e) {
            e.printStackTrace();
            strJson="EXCEPTION";
            Toast.makeText(getApplicationContext(),strJson,Toast.LENGTH_LONG).show();
        }*/




        /*try {
            url = new URL("https://127.0.0.1:8000/android");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            strJson = readStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            //Toast.makeText(getApplicationContext(),strJson+" toJSON",Toast.LENGTH_LONG).show();
            jsonArray.put(jo.getJSONArray(strJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    //Toast.makeText(getApplicationContext(),strJson + " readStream TRY",Toast.LENGTH_LONG).show();
    //Toast.makeText(getApplicationContext(),strJson + " readStream Catch",Toast.LENGTH_LONG).show();
    //Toast.makeText(getApplicationContext(),strJson + " readStream Catch then TRY",Toast.LENGTH_LONG).show();




        /*try {
            jo.put("Employees",strJson);

            jsonArray.put(jo);

        } catch (JSONException e) {
            e.printStackTrace();
        }*/



    //Toast.makeText(getApplicationContext(),strJson,Toast.LENGTH_LONG).show();

        /*try {
            jo.put("first_name","John");
            jo.put("last_name","Wick");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonArray.put(jo);*/



        /*ArrayList<Employee> newEmps = Employee.fromJSON(jsonArray);

        //Create the adapter to convert the array to view
        EmployeeAdapter adapter = new EmployeeAdapter(this,newEmps);

        //Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_of_employees);
        listView.setAdapter(adapter);*/





    /*class MyDownloadTask extends AsyncTask<Void,Void,Void>
    {

        protected void onPreExecute() {
            //display progress dialog.

        }
        protected Void doInBackground(Void... params) {
            URL url = null;
            try {
                url = new URL("http://127.0.0.1:8000/android");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                String responseMsg = con.getResponseMessage();
                Toast.makeText(getApplicationContext(),responseMsg,Toast.LENGTH_LONG).show();
                int response = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }



        protected void onPostExecute(Void result) {
            // dismiss progress dialog and update ui
        }
    }*/


    /*private String readStream(InputStream input) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(input),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        input.close();
        return sb.toString();
    }*/

}
