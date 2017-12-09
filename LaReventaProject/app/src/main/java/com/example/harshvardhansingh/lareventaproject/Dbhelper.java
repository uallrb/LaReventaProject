package com.example.harshvardhansingh.lareventaproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by harsh vardhan singh on 02-07-2016.
 */
public class Dbhelper extends SQLiteOpenHelper {
    public static String dbname="credentials";



    public Dbhelper(Context context) {

        super(context, dbname,null,1);
        //SQLiteDatabase db=this.getWritableDatabase();

    }

    public Dbhelper(Context context, String name) {
        super(context, name, null, 1);
        //SQLiteDatabase db=this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL("DROP TABLE IF EXISTS"+" "+dbname);
      //  db.execSQL("create table" + " " + dbname + "(no INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,TopImageUrl TEXT,CompressedUrl TEXT,videourl TEXT)");
        db.execSQL("CREATE TABLE"+"  "+"credentials(name TEXT,email TEXT,FcmToken TEXT)");
        db.execSQL("CREATE TABLE" + "  " + "topdeals(no INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,price REAL,description TEXT,imageurl TEXT,sellername TEXT,sellernumber TEXT,itemold INTEGER,category TEXT)");
        db.execSQL("CREATE TABLE" + "  " + "sdcardurls(no INTEGER PRIMARY KEY AUTOINCREMENT,imageurl TEXT)");
        db.execSQL("CREATE TABLE" + "  " + "topdealsserver(no INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,price REAL,description TEXT,imageurl TEXT,sellername TEXT,sellernumber TEXT,itemold INTEGER,category TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS"+dbname);
        onCreate(db);
    }


    public Boolean databaseentry(String name, String email)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name" , String.valueOf(name));
        contentValues.put("email", String.valueOf(email));
       // contentValues.put("FcmToken", String.valueOf(fcm));

      // db.insert(dbname, null, contentValues);

        long ret= db.insert(dbname, null, contentValues);
        if(ret==-1)
        {
            return false;
        }
        else return true;
    }

    public Boolean sdurlinsert(String url)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("imageurl" , String.valueOf(url));

        long ret= db.insert("sdcardurls", null, contentValues);
        if(ret==-1)
        {
            return false;
        }
        else return true;


    }
    public Cursor readurl()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + " " + "sdcardurls", null);

        return res;
    }

    public void updateurl(String url)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update sdcardurls set imageurl= '"+url+"'");



    }


    public Boolean topdealsinsert(String name, String description, String url,  float price,String phone,String sellername,int itemold,String category)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name" , String.valueOf(name));
        contentValues.put("description", description);
        contentValues.put("imageurl", url);
        contentValues.put("price", price);
        contentValues.put("sellernumber", phone);
        contentValues.put("sellername", sellername);
        contentValues.put("itemold", itemold);
        contentValues.put("category", category);


        // contentValues.put("FcmToken", String.valueOf(fcm));

        // db.insert(dbname, null, contentValues);

        long ret= db.insert("topdeals", null, contentValues);
        if(ret==-1)
        {
            return false;
        }
        else return true;


    }


    public Boolean topdealsinsertServer(String name, String description, String url,  float price,String phone,String sellername,int itemold,String category)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name" , String.valueOf(name));
        contentValues.put("description", description);
        contentValues.put("imageurl", url);
        contentValues.put("price", price);
        contentValues.put("sellernumber", phone);
        contentValues.put("sellername", sellername);
        contentValues.put("itemold", itemold);
        contentValues.put("category", category);


        // contentValues.put("FcmToken", String.valueOf(fcm));

        // db.insert(dbname, null, contentValues);

        long ret= db.insert("topdeals", null, contentValues);
        if(ret==-1)
        {
            return false;
        }
        else return true;


    }



    public void delete()
    {SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("delete from "+" "+dbname );
        db.execSQL("update sqlite_sequence set seq ="+0+" where name ='recommended_table'");

    }







    public Cursor readtempo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + " " + "topdeals", null);

        return res;


    }

    public Cursor readtempoServer()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from " + " " + "topdealsserver", null);

        return res;


    }
    public void topstoriesdelete()
    {SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("delete from "+" "+"topdealsserver" );
        db.execSQL("update sqlite_sequence set seq ="+0+" where name ='topdealsserver'");

    }


}
