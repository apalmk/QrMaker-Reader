package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class PostLoginClass1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login_class1);
    }

    void scan(View view)
    {
        startActivity(new Intent(PostLoginClass1.this,Scan.class));

    }

    void changepass(View view)
    {
        Intent intenti = getIntent();
        String id = intenti.getStringExtra("id");
        startActivity(new Intent(PostLoginClass1.this,change.class).putExtra("id",id));
    }

    void logout(View view)
    {
        startActivity(new Intent(PostLoginClass1.this,First.class));

    }

    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "Logout to go back", Toast.LENGTH_LONG).show();

    }
}
