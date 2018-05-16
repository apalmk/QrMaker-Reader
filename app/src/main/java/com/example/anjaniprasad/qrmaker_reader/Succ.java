package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Succ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succ);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ting);
        mp.start();
    }

    @Override
    public void onBackPressed() {

        Intent start1 = new Intent(Succ.this,Postloginclass.class);
        startActivity(start1);
        finish();
    }
}
