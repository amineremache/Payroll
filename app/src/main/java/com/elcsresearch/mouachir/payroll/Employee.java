package com.elcsresearch.mouachir.payroll;

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
}
