package com.elcsresearch.mouachir.payroll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by neofly on 7/30/17.
 */

public class Employee {

     private String matricule_interne;
     private String nom ;
     private String prenom ;
     private String date_naiss ;
     private String bureau ;
     private String Email  ;
     private String tel_bureau  ;
     private String date_entree  ;
     private String departement  ;
     private String post_trav  ;
     private String affect  ;
     private String sal_Horaire  ;
     private String adresse  ;
     private String lieu_naiss  ;
     private String tel_perso  ;
     private String sexe  ;
     private String sit_famil  ;
     private String nbr_enfant  ;
     private String nationalite  ;
     private String nss  ;
     private String nccp  ;
     private String entr  ;

    public Employee(){

    }

    public static Employee fromJSONObj (JSONObject obj) {

        Employee emp = new Employee();

        try {
            emp.matricule_interne = obj.getString("matricule_interne");
            emp.nom = obj.getString("nom");
            emp.prenom = obj.getString("prenom");
            emp.date_naiss = obj.getString("date_naiss");
            emp.bureau = obj.getString("bureau");
            emp.Email = obj.getString("Email");
            emp.tel_bureau = obj.getString("tel_bureau");
            emp.date_entree = obj.getString("date_entree");
            emp.departement = obj.getString("departement");
            emp.post_trav = obj.getString("post_trav");
            emp.affect = obj.getString("affect");
            emp.sal_Horaire = obj.getString("sal_Horaire");
            emp.adresse = obj.getString("adresse");
            emp.lieu_naiss = obj.getString("lieu_naiss");
            emp.tel_perso = obj.getString("tel_perso");
            emp.sexe = obj.getString("sexe");
            emp.sit_famil = obj.getString("sit_famil");
            emp.nbr_enfant = obj.getString("nbr_enfant");
            emp.nationalite = obj.getString("nationalite");
            emp.nss = obj.getString("nss");
            emp.nccp = obj.getString("nccp");
            emp.entr = obj.getString("entr");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        return emp;

    }



    public static ArrayList<Employee> fromJSONArr (JSONArray arr) {

        JSONObject empJSON;

        ArrayList<Employee> employees = new ArrayList<Employee>(arr.length());

        for (int i=0;i<arr.length();i++) {

            try {
                empJSON = arr.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

            Employee emp = Employee.fromJSONObj(empJSON);
            if (emp != null) {
                employees.add(emp);
            }

        }

        return employees;

    }


    }

