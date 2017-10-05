package com.elcsresearch.mouachir.payroll;

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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class TabEmployeesList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String TAG = TabEmployeesList.class.getSimpleName();
    private ListView listView;

    private static String url = "http://192.168.1.6:8000/payroll/ListeEmployeJSON";
    //private static String url ="https://peaceful-mesa-99911.herokuapp.com/mobile/";

    private static String urlPointage = "http://192.168.1.6:8000/payroll/DernierPointageJSON/";

    ArrayList<HashMap<String, String>> employeesList;
    ArrayList<Employee> EmployeesList;


    public TabEmployeesList() {
        // Required empty public constructor
    }

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
                Intent empDetail = new Intent(getContext(),ScrollingActivity.class);


                empDetail.putExtra("Employee",employeesList.get(i));


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
            JsonObjectRequest JsObjReq = new JsonObjectRequest
                    (com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                public void onResponse(JSONObject response) {


                            // THIS IS FOR NEXT TIME TO TEST AND REPLACE THE LOOP WITH RESPONSE ATTRIBUTES
                    Log.e(TAG, "Response VOLLEY: " + response.toString());

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    Log.e(TAG, "Response VOLLEY failed ");

                }
            });

            MySingleton.getInstance(getContext()).addToRequestQueue(JsObjReq);


            // Making a request to url and getting response
            /*String jsonStr = sh.getJSONList(url);
            Log.e(TAG, "Response from url :\n " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray employees = jsonObj.getJSONArray("Employees");

                    // looping through All Employees

                    EmployeesList = Employee.fromJSONArr(employees);
                    Log.e(TAG, "" + EmployeesList.get(1).getNom() + " " + EmployeesList.get(1).getPrenom() );

                    for (int i = 0; i < employees.length(); i++) {

                        JSONObject JSONObj_iterator = employees.getJSONObject(i);

                        String matricule_interne = JSONObj_iterator.getString("matricule_interne");
                        String nom = JSONObj_iterator.getString("nom");
                        String prenom = JSONObj_iterator.getString("prenom");
                        String date_naiss = JSONObj_iterator.getString("date_naiss");
                        String bureau = JSONObj_iterator.getString("bureau");
                        String Email = JSONObj_iterator.getString("Email");
                        String tel_bureau = JSONObj_iterator.getString("tel_bureau");
                        String date_entree = JSONObj_iterator.getString("date_entree");
                        String departement = JSONObj_iterator.getString("departement");
                        String post_trav = JSONObj_iterator.getString("post_trav");
                        String affect = JSONObj_iterator.getString("affect");
                        String sal_Horaire = JSONObj_iterator.getString("sal_Horaire");
                        String adresse = JSONObj_iterator.getString("adresse");
                        String lieu_naiss = JSONObj_iterator.getString("lieu_naiss");
                        String tel_perso = JSONObj_iterator.getString("tel_perso");
                        String sexe = JSONObj_iterator.getString("sexe");
                        String sit_famil = JSONObj_iterator.getString("sit_famil");
                        String nbr_enfant = JSONObj_iterator.getString("nbr_enfant");
                        String nationalite = JSONObj_iterator.getString("nationalite");
                        String nss = JSONObj_iterator.getString("nss");
                        String nccp = JSONObj_iterator.getString("nccp");
                        String entr = JSONObj_iterator.getString("entr");


                        // get last pointage with another url request

                        String urlPointageEmp = urlPointage + nom + "/" + prenom ;

                        HttpHandler pointageHandler = new HttpHandler();

                        // Making a request to url and getting response
                        String pointageStr = pointageHandler.getJSONList(urlPointageEmp);

                        Log.e(TAG, "Response from urlpointage :\n " + pointageStr);
                        JSONObject obj = null;
                        String date = null ;

                        if (pointageStr!=null) {

                            obj = new JSONObject(pointageStr);
                            date = obj.getString("date");

                        }



                        // tmp hash map for single employee
                        HashMap<String, String> employeeMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        employeeMap.put("matricule_interne",matricule_interne);
                        employeeMap.put("nom", nom);
                        employeeMap.put("prenom", prenom);
                        employeeMap.put("date_naiss", date_naiss);
                        employeeMap.put("bureau",bureau);
                        employeeMap.put("Email",Email);
                        employeeMap.put("tel_bureau",tel_bureau);
                        employeeMap.put("date_entree",date_entree);
                        employeeMap.put("departement",departement);
                        employeeMap.put("post_trav",post_trav);
                        employeeMap.put("affect",affect);
                        employeeMap.put("sal_Horaire",sal_Horaire);
                        employeeMap.put("adresse",adresse);
                        employeeMap.put("lieu_naiss",lieu_naiss);
                        employeeMap.put("tel_perso",tel_perso);
                        employeeMap.put("sexe",sexe);
                        employeeMap.put("sit_famil",sit_famil);
                        employeeMap.put("nbr_enfant",nbr_enfant);
                        employeeMap.put("nationalite",nationalite);
                        employeeMap.put("nss",nss);
                        employeeMap.put("nccp",nccp);
                        employeeMap.put("entr",entr);
                        employeeMap.put("last_pointage",date);



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
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ListAdapter adapter = new SimpleAdapter(

                    getContext(),
                    employeesList,
                    R.layout.item_employee,
                    new String[]{"matricule_interne" , "nom", "prenom", "last_pointage"},
                    new int[] {R.id.emp_matricule_interne, R.id.emp_nom, R.id.emp_prenom, R.id.emp_date_naiss});

            listView.setAdapter(adapter);
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }





}