package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class Scan extends AppCompatActivity {
    TextView mvname,username,date1,seatno,time,phno,threed1;
    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mvname = (TextView) findViewById(R.id.movienameview);
        username = (TextView) findViewById(R.id.username_view);
        date1 = (TextView) findViewById(R.id.dateview);
        seatno = (TextView) findViewById(R.id.seatnoview);
        time = (TextView) findViewById(R.id.timeview);
        phno = (TextView) findViewById(R.id.phonenumview);
        threed1 = (TextView) findViewById(R.id.threed);

        qrScan = new IntentIntegrator(this);

    }
  void camscan(View view)
  {
      qrScan.initiateScan();
  }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    mvname.setText(obj.getString("mMoviename"));
                    username.setText(obj.getString("mUsername"));
                    date1.setText(obj.getString("mDate"));
                    seatno.setText(obj.getString("mSeatno"));
                    time.setText(obj.getString("mTime"));
                    phno.setText(obj.getString("mphonenumb"));
                    threed1.setText(obj.getString("m3dorno"));





                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }




    @Override
    public void onBackPressed() {

        startActivity(new Intent(Scan.this,PostLoginClass1.class));

    }
}
