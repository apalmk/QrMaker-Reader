package com.example.anjaniprasad.qrmaker_reader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter1
{
    static final String DATABASE_NAME1 = "login1.db";
    static final int DATABASE_VERSION1 = 3;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TASK = "task";
    public static final String TABLE_NAME = "mv";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE2 = "create table "+TABLE_NAME+
            "( " +KEY_ROWID+" integer primary key autoincrement, "+ KEY_TASK+" text,TYPE integer); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db1;
    // Context of the application using the database.
    private final Context context1;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper1;
    public  LoginDataBaseAdapter1(Context _context)
    {
        context1 = _context;
        dbHelper1 = new DataBaseHelper(context1, DATABASE_NAME1, null, DATABASE_VERSION1);
    }
    public  LoginDataBaseAdapter1 open() throws SQLException
    {
        db1 = dbHelper1.getWritableDatabase();
        return this;
    }

    public  Cursor getSepficItem(){
        return  db1.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
    public void close()
    {
        db1.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db1;
    }

    public int insertEntry1(String moviename,int isd)
    {
        Cursor cursor=db1.query(TABLE_NAME, null, KEY_TASK+" =?", new String[]{moviename}, null, null, null);
        if(cursor.getCount()<1)
        {   ContentValues newValues1 = new ContentValues();
            // Assign values for each row.
            newValues1.put(KEY_TASK, moviename);
            newValues1.put("TYPE",isd);
            db1.insert(TABLE_NAME, null, newValues1);
            cursor.close();
            return 1;
        }

        else
        {
            return 0;
        }

        // Insert the row into your table
    }

    public int getSinlgeEntry1(String moviename)
    {
        Cursor cursor=db1.query(TABLE_NAME, null, KEY_TASK+" =?", new String[]{moviename}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int val= cursor.getInt(cursor.getColumnIndex("TYPE"));
        cursor.close();
        return val;
    }

//    public int getAdmin(String userName)
//    {
//        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
//        if(cursor.getCount()<1) // UserName Not Exist
//        {
//            cursor.close();
//            return 0;
//        }
//        cursor.moveToFirst();
//        int fl= cursor.getInt(cursor.getColumnIndex("ADMIN"));
//        cursor.close();
//        return fl;
//    }

//    public void  updateEntry(String userName,String password,int fl)
//    {
//        // Define the updated row content.
//        ContentValues updatedValues = new ContentValues();
//        // Assign values for each row.
//        updatedValues.put("USERNAME", userName);
//        updatedValues.put("PASSWORD",password);
//        updatedValues.put("ADMIN",fl);
//        String where="USERNAME = ?";
//        db.update("LOGIN",updatedValues, where, new String[]{userName});
//    }

}