package com.elcsresearch.mouachir.payroll;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabEmployeesList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabEmployeesList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabEmployeesList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    private String TAG = EmployeesList.class.getSimpleName();
    private ListView listView;

    private static String url = "http://192.168.1.7:8000/mobile";

    ArrayList<HashMap<String, String>> employeesList;


    public TabEmployeesList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabEmployeesList.
     */
    // TODO: Rename and change types and number of parameters
    public static TabEmployeesList newInstance(String param1, String param2) {
        TabEmployeesList fragment = new TabEmployeesList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_employees_list, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        employeesList = new ArrayList<>();
        listView = (ListView) getActivity().findViewById(R.id.frag_emp_list);

        new GetEmployees().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent empDetail = new Intent(getContext(),EmployeeDetail.class);


                empDetail.putExtra("Employee",employeesList.get(0));


                startActivity(empDetail);
            }
        });

    }


    private class GetEmployees extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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

                        String id = JSONObj_iterator.getString("id");
                        String lname = JSONObj_iterator.getString("last_name");
                        String fname = JSONObj_iterator.getString("first_name");
                        String bdate = JSONObj_iterator.getString("birth_date");

                        // tmp hash map for single employee
                        HashMap<String, String> employeeMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        employeeMap.put("PK",id);
                        employeeMap.put("LastName", lname);
                        employeeMap.put("FirstName", fname);
                        employeeMap.put("BDate", bdate);

                        // adding employee (HashMap) to employees' list (ArrayList)
                        employeesList.add(employeeMap);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error : " + e.getMessage());
                    Toast.makeText(getContext(),
                            "JSON parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();

                }
            } else {
                Log.e(TAG, "Couldn't get JSON from server.");
                Toast.makeText(getContext(),
                        "Couldn't get JSON from server. Check LogCat for possible errors!",
                        Toast.LENGTH_LONG)
                        .show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(

                    getContext(),
                    employeesList,
                    R.layout.item_employee,
                    new String[]{"PK" , "LastName", "FirstName", "BDate"},
                    new int[] {R.id.emp_id, R.id.emp_lname, R.id.emp_fname, R.id.emp_bdate});

            listView.setAdapter(adapter);
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
