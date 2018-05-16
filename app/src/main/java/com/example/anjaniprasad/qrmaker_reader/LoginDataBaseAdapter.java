package com.example.anjaniprasad.qrmaker_reader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 5;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text,ADMIN integer,EMAIL text); ";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TASK = "task";
    public static final String TABLE_NAME = "movie";
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE2 = "create table "+TABLE_NAME+
            "( " +KEY_ROWID+" integer primary key autoincrement, "+ KEY_TASK+" text,TYPE integer); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public int insertEntry(String userName,String password,int flag,String email)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1)
        {   ContentValues newValues = new ContentValues();
            // Assign values for each row.
            newValues.put("USERNAME", userName);
            newValues.put("PASSWORD",password);
            newValues.put("EMAIL",email);
            newValues.put("ADMIN",flag);
            db.insert("LOGIN", null, newValues);
            cursor.close();
            return 1;
        }

        else
        {
          return 0;
        }

        // Insert the row into your table
    }

    public  Cursor getSepficItem(){
        return  db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    public int insertEntry1(String moviename,int isd)
    {
        Cursor cursor=db.query(TABLE_NAME, null, KEY_TASK+" =?", new String[]{moviename}, null, null, null);
        if(cursor.getCount()<1)
        {   ContentValues newValues1 = new ContentValues();
            // Assign values for each row.
            newValues1.put(KEY_TASK, moviename);
            newValues1.put("TYPE",isd);
            db.insert(TABLE_NAME, null, newValues1);
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
        Cursor cursor=db.query(TABLE_NAME, null, KEY_TASK+" =?", new String[]{moviename}, null, null, null);
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

    public String getname(String num)
    {
        Cursor cursor=db.query(TABLE_NAME, null, KEY_ROWID+" =?", new String[]{num}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "null";
        }
        cursor.moveToFirst();
        String val1= cursor.getString(cursor.getColumnIndex(KEY_TASK));
        cursor.close();
        return val1;
    }

    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where=KEY_TASK+" =?";
        int numberOFEntriesDeleted= db.delete(TABLE_NAME, where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getEmail(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String email= cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return email;
    }

    public int getAdmin(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int fl= cursor.getInt(cursor.getColumnIndex("ADMIN"));
        cursor.close();
        return fl;
    }

    public int  updateEntry(String userName,String password)
    {
        ContentValues cv = new ContentValues();
        cv.put("PASSWORD", password);
        int i= db.update("LOGIN", cv,"USERNAME= ?", new String[] {userName});
        return i;
    }

    public int  updatepassword(String userName,String password)
    {
        ContentValues cv = new ContentValues();
        cv.put("PASSWORD", password);
        int i= db.update("LOGIN", cv,"USERNAME= ?", new String[] {userName});
        return i;
    }
}