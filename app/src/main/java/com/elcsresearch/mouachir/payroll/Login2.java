package com.elcsresearch.mouachir.payroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        Button connect = (Button) findViewById(R.id.email_sign_in_button);

        View.OnClickListener btnListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = "", password = "";  // THIS IS FOR TEST

                if ( username.equals(((EditText)findViewById(R.id.log_email)).getText().toString()) &&
                        password.equals(((EditText)findViewById(R.id.log_password)).getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), NavigationMain.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Username or password incorrect",Toast.LENGTH_LONG).show();
                }

            }
        };

        connect.setOnClickListener(btnListner);

    }
}
