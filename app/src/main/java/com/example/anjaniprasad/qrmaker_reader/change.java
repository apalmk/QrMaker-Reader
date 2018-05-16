package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class change extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText oldpassword,newpassword1,reenternewpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change);
        oldpassword= (EditText)findViewById(R.id.oldpassword);
        newpassword1= (EditText)findViewById(R.id.newpassword);
        reenternewpassword= (EditText)findViewById(R.id.reenternewpassword);

    }

   void changepass1(View view) {
       loginDataBaseAdapter = new LoginDataBaseAdapter(this);
       loginDataBaseAdapter = loginDataBaseAdapter.open();
       Intent intenti = getIntent();
       String id = intenti.getStringExtra("id");
       String storedPassword=loginDataBaseAdapter.getSinlgeEntry(id);
       String oldpass= oldpassword.getText().toString();
       String newpassword= newpassword1.getText().toString();
       String reenternewpass= reenternewpassword.getText().toString();
       if(oldpass.equals(storedPassword))
       {
           if(newpassword.equals(reenternewpass))
           {
               int i = loginDataBaseAdapter.updateEntry(id,newpassword);
               if(i>=1)
               {
                   Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_LONG).show();
                   Intent start = new Intent(change.this,First.class);
                   startActivity(start);
               }
           }
           else {
               Toast.makeText(getApplicationContext(), "new passwords dont match", Toast.LENGTH_LONG).show();

           }
       }
       else
       {
           Toast.makeText(getApplicationContext(), "old password doesnot match", Toast.LENGTH_LONG).show();

       }
   }
}
