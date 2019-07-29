package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoggedIn extends AppCompatActivity {

    private TextView t1;
    private Button b1,b2;

    private FirebaseAuth f1;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        f1 = FirebaseAuth.getInstance();
        if(f1.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,Signin.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        t1 = (TextView) findViewById(R.id.t1);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);

        t1.setText("HELLO!! " + f1.getCurrentUser().getEmail() +"\n\nThis is ASHUTOSH PALIWAL" + "\n\n\n\nPlease Add Your Details");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),Signin.class));
                toastMsg("You Successfully Log Out");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),details.class));
            }
        });

    }

    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();

    }
}
