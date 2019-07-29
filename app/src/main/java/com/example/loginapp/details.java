package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.escape.Escaper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class details extends AppCompatActivity {

    private Button b1,b2;
    private FirebaseAuth f1;
    private EditText Firstname,LastName,Address,Age,Hobby;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        f1 = FirebaseAuth.getInstance();

        if(f1.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,Signin.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference() ;

        Firstname = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        Age = (EditText) findViewById(R.id.Age);
        Address = (EditText) findViewById(R.id.Address);
        Hobby = (EditText) findViewById(R.id.Hobby);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);

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
                UserInf();
                finish();
                startActivity(new Intent(getApplicationContext(),thanks.class));
            }
        });

    }
    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();

    }

    public void UserInf()
    {
        String ffn = Firstname.getText().toString().trim();
        String lln = LastName.getText().toString().trim();
        String num = Age.getText().toString().trim();
        String pta = Address.getText().toString().trim();
        String hob = Hobby.getText().toString().trim();

        if(TextUtils.isEmpty(ffn))
        {
            finish();
            startActivity(new Intent(this,details.class));
        }
        if(TextUtils.isEmpty(lln))
        {
            Toast.makeText(this,"Please Enter Your Last Name",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,details.class));
        }
        if(TextUtils.isEmpty(num))
        {
            Toast.makeText(this,"Please Enter Your Age",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,details.class));
        }
        if(TextUtils.isEmpty(pta))
        {
            Toast.makeText(this,"Please Enter Your Address",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,details.class));
        }
        if(TextUtils.isEmpty(hob))
        {
            Toast.makeText(this,"Please Enter Your Hobby",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,details.class));
        }

        int numage = Integer.parseInt(num);

        userinf value = new userinf(ffn,lln,numage,pta,hob);

        FirebaseUser user = f1.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(value);

        toastMsg("You Successfully saved your data");

    }


}
