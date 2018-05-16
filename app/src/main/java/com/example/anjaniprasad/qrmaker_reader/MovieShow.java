package com.example.anjaniprasad.qrmaker_reader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MovieShow extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewitem);
       populate();
    }

private  void populate()
{
    LoginDataBaseAdapter mydb = new LoginDataBaseAdapter(this);
    mydb.open();
    Cursor cursor1= mydb.getSepficItem();
    if(cursor1!=null)
    {
        cursor1.moveToFirst();
    }

    String[] fromdb= new String[]{LoginDataBaseAdapter1.KEY_ROWID,LoginDataBaseAdapter1.KEY_TASK};
    int[] toview = new int[]{R.id.num,R.id.movie};
    SimpleCursorAdapter mycursorad= new SimpleCursorAdapter(getBaseContext(),R.layout.listitem,cursor1,fromdb,toview,0);
    ListView mylist = (ListView)findViewById(R.id.list);
    mylist.setAdapter(mycursorad);
    mylist.setBackgroundColor(getColor(R.color.lightblue));

    mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            long entry= (long) parent.getAdapter().getItemId(position);
            Intent intent = new Intent(MovieShow.this, Generate.class);
            String message = Long.toString(entry);
            intent.putExtra("id", message);
            startActivity(intent);
        }
    });
}


}
