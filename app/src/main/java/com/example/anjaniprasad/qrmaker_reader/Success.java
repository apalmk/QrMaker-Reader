package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ting);
        mp.start();
    }

    void goback(View view)
    {
        startActivity(new Intent(Success.this,First.class));

    }

    @Override
    public void onBackPressed() {

        Intent start = new Intent(Success.this,First.class);
        startActivity(start);
        finish();
    }

}
