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
    private String birth_date;

    public Employee(JSONObject obj){

        try {

            this.last_name = obj.getString("LastName");
            this.first_name = obj.getString("FirstName");
            this.birth_date = obj.getString("BDate");

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

    public String getBirth_date() {

        return this.birth_date;
    }

    }

