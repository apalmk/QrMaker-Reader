package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText usern,email1;
    EditText passw,reenterpass;
    RadioButton userbutton,adminbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         usern = (EditText) findViewById(R.id.username);
        email1 = (EditText) findViewById(R.id.email);
         passw = (EditText) findViewById(R.id.password);
         reenterpass = (EditText) findViewById(R.id.reenterpassword);
         userbutton = (RadioButton) findViewById(R.id.radioUser);
         adminbutton = (RadioButton) findViewById(R.id.radioAdmin);

    }

    void regfunc(View view)
    {
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
      String usernamer= usern.getText().toString();
        String passwordr= passw.getText().toString();
        String email=email1.getText().toString();
        String repassr= reenterpass.getText().toString();
        boolean adminr=adminbutton.isChecked();
        boolean userr=userbutton.isChecked();

        if(usernamer.equals("")||passwordr.equals("")||repassr.equals("")||email.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }

        if(!adminr&&!userr)
        {
            Toast.makeText(getApplicationContext(), "Enter adimn or user", Toast.LENGTH_LONG).show();
            return;
        }

        if(!passwordr.equals(repassr))
        {
            Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_LONG).show();
            return;
        }

        else
        {
            int flag = (adminr)? 1 : 0;
            // Save the Data in Database
            int val=loginDataBaseAdapter.insertEntry(usernamer,passwordr,flag,email);
            if(val!=0) {
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                Intent intentsu = new Intent(Register.this, Success.class);
                startActivity(intentsu);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "User name already exists", Toast.LENGTH_LONG).show();


            }
        }



    }
    @Override
    public void onBackPressed() {

        Intent start = new Intent(Register.this,First.class);
        startActivity(start);
        finishActivity(0);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
