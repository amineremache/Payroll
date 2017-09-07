package com.elcsresearch.mouachir.payroll;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;


/**
 * Created by neofly on 9/7/17.
 */

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Last check : 25-09-2017, 15:43", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        HashMap< String,String > myEmp = (HashMap<String,String>) getIntent().getSerializableExtra("Employee");

        TextView tv_num_bureau = (TextView) findViewById(R.id.tv_num_bureau);
        TextView tv_num_mobile = (TextView) findViewById(R.id.tv_num_mobile);
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        TextView tv_adresse_bureau = (TextView) findViewById(R.id.tv_adresse_bureau);
        TextView tv_adresse_personnelle = (TextView) findViewById(R.id.tv_adresse_personnelle);


        tv_num_bureau.setText(myEmp.get("tel_bureau"));
        tv_num_mobile.setText(myEmp.get("tel_perso"));
        tv_email.setText(myEmp.get("Email"));
        tv_adresse_bureau.setText(myEmp.get("bureau"));
        tv_adresse_personnelle.setText(myEmp.get("adresse"));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((myEmp.get("nom")+" "+myEmp.get("prenom")).toUpperCase());
    }
}
