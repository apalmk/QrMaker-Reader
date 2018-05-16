package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Delete extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        Intent intenti = getIntent();
        String id = intenti.getStringExtra("id");
        String val=loginDataBaseAdapter.getname(id);
        int d= loginDataBaseAdapter.deleteEntry(val);
        if(d>0)
        {
            setContentView(R.layout.activity_delete);
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ting);
            mp.start();
        }

    }
    @Override
    public void onBackPressed() {

        Intent start1 = new Intent(Delete.this,Postloginclass.class);
        startActivity(start1);
        finish();
    }

}


