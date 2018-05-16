package com.example.anjaniprasad.qrmaker_reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.anjaniprasad.qrmaker_reader.R.id.username;


public class MainActivity extends Activity {

    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText id= (EditText)findViewById(username);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        final EditText password= (EditText)findViewById(R.id.password);
        Button submitbutton = (Button)findViewById(R.id.loginButton);
        submitbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                String username= id.getText().toString();
                String pass= password.getText().toString();

                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(username);
                int flaggy = loginDataBaseAdapter.getAdmin(username);

                if ((username != null && username.length()>0)
                        && (pass != null && pass.length()>0)) {

                    if ( pass.equals(storedPassword)&&flaggy==1) {

                        startActivity(new Intent(getApplicationContext(), Postloginclass.class).putExtra("loginStatus", true).putExtra("id",username));
                        finish();
                    }
                    else if(pass.equals(storedPassword)&&flaggy==0)
                    {
                        startActivity(new Intent(getApplicationContext(), PostLoginClass1.class).putExtra("loginStatus", true).putExtra("id",username));
                        finish();
                    }
                     else {
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(), "Username & Password should not be empty.", Toast.LENGTH_LONG).show();
                }

            }


        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

    @Override
    public void onBackPressed() {

        Intent start = new Intent(MainActivity.this,First.class);
        startActivity(start);
        finish();
    }


}
