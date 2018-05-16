package com.example.anjaniprasad.qrmaker_reader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        /*Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        if(isSDPresent)
        {
            // yes SD-card is present
            Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_LONG).show();
        }
        else
        {
            // Sorry
            Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
        }*/




    }

    void register(View view)
    {
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();

    }

    void forgot(View view)
    {
        startActivity(new Intent(getApplicationContext(), Forgot.class));
        finish();
    }




    void login(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    void call(View view)
    {
        if(isPermissionGranted()){
            call_action();
        }
    }

    public void call_action(){
         try {
             Intent callIntent = new Intent(Intent.ACTION_CALL);
             callIntent.setData(Uri.parse("tel:" + "+919790650451"));
             startActivity(callIntent);
         }
         catch(SecurityException e)
         {
             e.printStackTrace();
         }
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "Can't go back anymore", Toast.LENGTH_LONG).show();


    }
}
