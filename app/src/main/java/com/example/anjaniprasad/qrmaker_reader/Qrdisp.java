package com.example.anjaniprasad.qrmaker_reader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class Qrdisp extends AppCompatActivity {
ImageView img;

    int t=0;
    int RESULT=13;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrdisp);
        img=(ImageView)findViewById(R.id.qr);
        Intent intent = getIntent();
        bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        img.setImageBitmap(bitmap);


    }

void share(View view) {
    String ans= save();
    if(ans.equals("error"))
    {
        Toast.makeText(getApplicationContext(), "Some internal occured the image could not be shared", Toast.LENGTH_LONG).show();
    }
    else
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        String path="/storage/emulated/0/Qr pics/"+ans;
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image"));
    }

}
    String save()
    {

        String err="error";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT);
        }
        else {


            String strpath = android.os.Environment.getExternalStorageDirectory().toString();
            String dirName = "Qr pics";
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            File makeDirectory = new File(strpath + "/" + dirName);
            makeDirectory.mkdir();
            OutputStream outStream;
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);

            String filename = "QRimage"+n+".jpg";
            String dirpath = strpath + "/" + dirName + "/";

            File storagePath = new File(dirpath);
            File myImage = new File(storagePath, filename);
            try {
                outStream = new FileOutputStream(myImage);
                outStream.write(bytes.toByteArray());
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                t = 1;
            }

            if (t == 0) {
                Toast.makeText(getApplicationContext(), "Image saved", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, First.class));
            } else {
                Toast.makeText(getApplicationContext(), "Some internal occured the image could not be saved", Toast.LENGTH_LONG).show();
            }
            return filename;

        }

       return err;
    }
}





