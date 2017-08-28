package com.elcsresearch.mouachir.payroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        getSupportActionBar().setTitle(R.string.app_name);

        Button btn_list = (Button) findViewById(R.id.btn_list);
        View.OnClickListener btn_list_listner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EmployeesList.class);
                startActivity(intent);
            }
        };

        btn_list.setOnClickListener(btn_list_listner);



    }
}
