package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Postloginclass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postloginclass);
    }

    void logout(View view)
    {
        startActivity(new Intent(Postloginclass.this,First.class));

    }

    void gen(View view)
    {
        startActivity(new Intent(Postloginclass.this,MovieShow.class));

    }

    void changepass2(View view)
    {
        Intent intenti = getIntent();
        String id = intenti.getStringExtra("id");
        startActivity(new Intent(Postloginclass.this,change.class).putExtra("id",id));
    }

    void show2(View view)
    {
        startActivity(new Intent(Postloginclass.this,Movieshow2.class));

    }



    void add(View view)
    {
        startActivity(new Intent(Postloginclass.this,Addmv.class));

    }

    void scan1(View view)
    {
        startActivity(new Intent(Postloginclass.this,Scan1.class));

    }

    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "Logout to go back", Toast.LENGTH_LONG).show();

    }
}
