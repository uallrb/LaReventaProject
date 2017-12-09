package com.example.harshvardhansingh.lareventaproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class AdsActivity extends AppCompatActivity {


    TopDealsAdapter adapter;
    private ArrayList<TopDealsBean> list1;
    private TopDealsAdapter topDealsAdapter;
    private Dbhelper dbhelper;
   /* public AdsActivity(TopDealsAdapter adapter1)
    {
        adapter=adapter1;

    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
         list1=new ArrayList<TopDealsBean>();

dbhelper=new Dbhelper(getApplicationContext());
        Bundle bundle=new Bundle();
        Intent i=getIntent();
        bundle=i.getExtras();
      String ad=  bundle.getString("adapter");

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.adsrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        adapter=new TopDealsAdapter(getApplicationContext(),list1);

        recyclerView.setAdapter(adapter);


        if(ad.equals("Books"))
        {
            prepareDataBooks();
        }
        if(ad.equals("Stationery"))
        {
            prepareDataStationery();
        }
        if(ad.equals("Electronics"))
        {
            prepareDataElectronics();
        }
        if(ad.equals("Notes"))
        {
            prepareDataNotes();
        }
        if(ad.equals("Calculators"))
        {
            prepareDataCalculators();
        }

    }


public  void prepareDataBooks()
{


    TopDealsBean one=new TopDealsBean("Quantum mechanical 2 sem",150, R.drawable.quantu,1);
    list1.add(one);

    Cursor res= dbhelper.readtempoFromCategory("Books");
     while (res.moveToNext())
     { int no=res.getInt(0);
         String name=res.getString(1);
         float price=res.getFloat(2);
         String url=res.getString(4);

         TopDealsBean New=new TopDealsBean(name,(int)price, R.drawable.warn,no);
         list1.add(New);



     }

    adapter.notifyDataSetChanged();

}

    public  void prepareDataStationery()
    {


        TopDealsBean one=new TopDealsBean("Drafter camel",200, R.drawable.drafter,1);
        list1.add(one);
        Cursor res= dbhelper.readtempoFromCategory("Stationery");
        while (res.moveToNext())
        { int no=res.getInt(0);
            String name=res.getString(1);
            float price=res.getFloat(2);
            String url=res.getString(4);

            TopDealsBean New=new TopDealsBean(name,(int)price, R.drawable.warn,no);
            list1.add(New);



        }

        adapter.notifyDataSetChanged();

    }
    public  void prepareDataElectronics()
    {



        TopDealsBean four=new TopDealsBean("Quantum mechanical 2 sem",150, R.drawable.quantum,1);
        list1.add(four);

        Cursor res= dbhelper.readtempoFromCategory("Electronics");
        while (res.moveToNext())
        { int no=res.getInt(0);
            String name=res.getString(1);
            float price=res.getFloat(2);
            String url=res.getString(4);

            TopDealsBean New=new TopDealsBean(name,(int)price, R.drawable.warn,no);
            list1.add(New);



        }

        adapter.notifyDataSetChanged();

    }
    public  void prepareDataNotes()
    {



        TopDealsBean four=new TopDealsBean("Quantum mechanical 2 sem",150, R.drawable.quantum,1);
        list1.add(four);
        Cursor res= dbhelper.readtempoFromCategory("Notes");
        while (res.moveToNext())
        { int no=res.getInt(0);
            String name=res.getString(1);
            float price=res.getFloat(2);
            String url=res.getString(4);

            TopDealsBean New=new TopDealsBean(name,(int)price, R.drawable.warn,no);
            list1.add(New);



        }

        adapter.notifyDataSetChanged();

    }
    public  void prepareDataCalculators()
    {


        TopDealsBean one=new TopDealsBean("Casio 100-fx",500, R.drawable.clc,1);
        list1.add(one);

        TopDealsBean two=new TopDealsBean("Casio 300- mx",700, R.drawable.calc,2);

        list1.add(two);
        Cursor res= dbhelper.readtempoFromCategory("Calculators");
        while (res.moveToNext())
        { int no=res.getInt(0);
            String name=res.getString(1);
            float price=res.getFloat(2);
            String url=res.getString(4);

            TopDealsBean New=new TopDealsBean(name,(int)price, R.drawable.warn,no);
            list1.add(New);



        }

        adapter.notifyDataSetChanged();

    }




}
