package com.elcsresearch.mouachir.payroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Button btn_history = (Button) findViewById(R.id.btn_history);
        View.OnClickListener btn_history_listner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PaymentHistoy.class);
                startActivity(intent);
            }
        };

        btn_history.setOnClickListener(btn_history_listner);

        Button btn_config = (Button) findViewById(R.id.btn_config);
        View.OnClickListener btn_config_listner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Configuration.class);
                startActivity(intent);
            }
        };

        btn_config.setOnClickListener(btn_config_listner);

        Button btn_stats = (Button) findViewById(R.id.btn_stats);
        View.OnClickListener btn_stats_listner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Statistics.class);
                startActivity(intent);
            }
        };

        btn_stats.setOnClickListener(btn_stats_listner);

    }
}
