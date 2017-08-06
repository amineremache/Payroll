package com.elcsresearch.mouachir.payroll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by neofly on 7/30/17.
 */

public class Employee {

    private String last_name ;
    private String first_name;

    public Employee (String lname, String fname) {

        this.first_name=fname;
        this.last_name=lname;

    }

    public Employee(JSONObject object){

        try {

            this.last_name = object.getString("LastName");
            this.first_name = object.getString("FirstName");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getLast_name () {

        return this.last_name;
    }

    public String getFirst_name () {

        return this.first_name;
    }

    public static ArrayList<Employee> getEmployees () {

        Employee user = new Employee("Amine","Remache");
        ArrayList<Employee> list = new ArrayList<Employee>();
        list.add(user);
        list.add(new Employee("Mounir","Remache"));

        return list;
    }

    public static ArrayList<Employee> fromJSON (JSONArray jsonObjects) {
        ArrayList<Employee> emps = new ArrayList<Employee>();
        for ( int i =0; i < jsonObjects.length();i++) {
            try {
                emps.add(new Employee(jsonObjects.getJSONObject(i)));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            }

            return emps;
        }



    }

