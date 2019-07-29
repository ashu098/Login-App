package com.example.loginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView t2;
    private Button b1,s1;
    private EditText e1,e2;
    private ProgressDialog p1;

    private FirebaseAuth f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f1 = f1.getInstance();

        if(f1.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,LoggedIn.class));
        }

        t2 = (TextView) findViewById(R.id.t2);
        b1 = (Button) findViewById(R.id.b1);
        e1  =(EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);


        p1 = new ProgressDialog(this);

        b1.setOnClickListener(this);
        t2.setOnClickListener(this);
    }

    @Override public void onClick(View view)
    {
        if(view == b1)
        {
            registeruser();
        }

        if(view == t2)
        {
            finish();
            startActivity(new Intent(this,Signin.class));
        }
    }

    private void registeruser()
    {
        String email = e1.getText().toString().trim();
        String pass = e2.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this , "Please Enter Email" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Please Enter Password" , Toast.LENGTH_SHORT).show();
            return;
        }

        p1.setMessage("You Are Registring :) ");
        p1.show();

        f1.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            p1.dismiss();
                            Toast.makeText(MainActivity.this,"You Are Registered Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),LoggedIn.class));
                        }

                        else {
                            p1.dismiss();
                            Toast.makeText(MainActivity.this, "There is some ERROR! PLease Try Again",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}