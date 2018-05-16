package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import net.glxn.qrgen.android.QRCode;

import static com.example.anjaniprasad.qrmaker_reader.R.id.moviename;

public class Generate extends AppCompatActivity {
   EditText mv,usr,date,time,seat,phonenum;
    LoginDataBaseAdapter loginDataBaseAdapter;
    RadioButton threed,twod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        mv=(EditText)findViewById(moviename);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        usr=(EditText)findViewById(R.id.username1);
        date=(EditText)findViewById(R.id.dateno);
        time=(EditText)findViewById(R.id.time1);
        seat=(EditText)findViewById(R.id.seatno);
        threed=(RadioButton) findViewById(R.id.radio3d);
        twod=(RadioButton) findViewById(R.id.radio2d);
        phonenum=(EditText)findViewById(R.id.phone);
        RadioGroup mgp=(RadioGroup)findViewById(R.id.myRadioGroup);
        Intent intenti = getIntent();
        String id = intenti.getStringExtra("id");
        String val=loginDataBaseAdapter.getname(id);
        int val2=loginDataBaseAdapter.getSinlgeEntry1(val);
        if(val2==1)
        {
            mgp.check(R.id.radio3d);
        }
        else
        {
            mgp.check(R.id.radio2d);
        }
        mv.setText(val);
    }




    void book(View view) {

        String moviename = mv.getText().toString();
        String username1 = usr.getText().toString();
        String dateofshow = date.getText().toString();
        String timeofshow = time.getText().toString();
        String seatofuser = seat.getText().toString();
        String phonenumberofuser = phonenum.getText().toString();
        boolean isthreed = threed.isChecked();
        boolean istwod = twod.isChecked();
        String threedornot = (isthreed) ? "Yes" : "No";

        if (moviename.length()>0&&username1.length()>0&& dateofshow.length()>0&& timeofshow.length()>0&&seatofuser.length()>0&&phonenumberofuser.length()>0) {
            if (isthreed||istwod) {

                Movie mv = new Movie();
                mv.setM3dorno(threedornot);
                mv.setmDate(dateofshow);
                mv.setmMoviename(moviename);
                mv.setMphonenumb(phonenumberofuser);
                mv.setmSeatno(seatofuser);
                mv.setmUsername(username1);
                mv.setmTime(timeofshow);
                Gson gson = new Gson();
                String data = gson.toJson(mv);
                Bitmap myBitmap = QRCode.from(data).bitmap();
//                System.out.println(data);
                Intent intent = new Intent(this, Qrdisp.class);
                intent.putExtra("BitmapImage", myBitmap);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "enter 2D or 3D", Toast.LENGTH_LONG).show();

            }

        } else {
            Toast.makeText(getApplicationContext(), "All the fields must be entered", Toast.LENGTH_LONG).show();
        }}

//       if((!isthreed||!istwod))
//       {
//           Toast.makeText(getApplicationContext(),"Select either 2D or 3D", Toast.LENGTH_LONG).show();
//
//
//       }

    }

