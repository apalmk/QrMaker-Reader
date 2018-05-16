package com.example.anjaniprasad.qrmaker_reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Forgot extends Activity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText usern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        usern = (EditText) findViewById(R.id.username3);

    }

    void forgo(View view)
    {

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        String username=usern.getText().toString();
        String exits=loginDataBaseAdapter.getSinlgeEntry(username);
        if(exits.equals("NOT EXIST"))
        {
            Toast.makeText(getApplicationContext(), "No such user", Toast.LENGTH_LONG).show();
        }
        else {
            String emailid = loginDataBaseAdapter.getEmail(username);
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            String changedpass = Integer.toString(n);
            int i1 = loginDataBaseAdapter.updatepassword(username, changedpass);
            if (i1 > 0) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{emailid});
                i.putExtra(Intent.EXTRA_SUBJECT, "New Password");
                i.putExtra(Intent.EXTRA_TEXT, changedpass);
                try {
                    startActivity(Intent.createChooser(i, "New Password sent"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Forgot.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    void back(View view)
    {
        startActivity(new Intent(getApplicationContext(), First.class));
        finish();
    }
}
