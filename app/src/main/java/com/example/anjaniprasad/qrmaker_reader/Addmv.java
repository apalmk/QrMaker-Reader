package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Addmv extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText moviename;
    RadioButton twodbutton,threedbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmv);
        moviename = (EditText) findViewById(R.id.moviename);
        twodbutton = (RadioButton) findViewById(R.id.radio2d);
        threedbutton = (RadioButton) findViewById(R.id.radio3d);
    }

    void add1(View view)
    {
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        String movienamer= moviename.getText().toString();
        boolean twodbuttonr=twodbutton.isChecked();
        boolean threedbuttonr=threedbutton.isChecked();

        if(movienamer.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }

        if(!threedbuttonr&&!twodbuttonr)
        {
            Toast.makeText(getApplicationContext(), "Enter 3D or 2D", Toast.LENGTH_LONG).show();
            return;
        }


        else {
            int isd = (threedbuttonr) ? 1 : 0;
            // Save the Data in Database
            int val = loginDataBaseAdapter.insertEntry1(movienamer, isd);
            if (val == 1) {
                if (val != 0) {
                    Toast.makeText(getApplicationContext(), "Movie Successfully Added ", Toast.LENGTH_LONG).show();
                    Intent intentsu1 = new Intent(Addmv.this, Succ.class);
                    startActivity(intentsu1);
                } else {
                    Toast.makeText(getApplicationContext(), "Movie already exists", Toast.LENGTH_LONG).show();


                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();

            }
        }



    }
    @Override
    public void onBackPressed() {

        Intent start = new Intent(Addmv.this,Postloginclass.class);
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
