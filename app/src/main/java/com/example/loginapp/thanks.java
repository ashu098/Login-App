package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class thanks extends AppCompatActivity {


    private Button b1;
    private TextView e1;
    private FirebaseAuth f1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        f1 = FirebaseAuth.getInstance();
        if(f1.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,Signin.class));
        }
        e1 = (TextView) findViewById(R.id.e1);
        b1 = (Button) findViewById(R.id.b1);

        e1.setText("THANK YOU !!! \n\n"+" You Successfully Save Your data");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),Signin.class));
            }
        });
    }
}
