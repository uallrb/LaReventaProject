package com.example.harshvardhansingh.lareventaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdsActivity extends AppCompatActivity {


    TopDealsAdapter adapter;
    private ArrayList<TopDealsBean> list1;
    private TopDealsAdapter topDealsAdapter;

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


        Bundle bundle=new Bundle();
        Intent i=getIntent();
        bundle=i.getExtras();
      String ad=  bundle.getString("adapter");

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.adsrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true));
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


    TopDealsBean one=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantu);
    list1.add(one);

    TopDealsBean two=new TopDealsBean("Quantum CS 3 sem",100,R.drawable.ind);
    list1.add(two);

    TopDealsBean three=new TopDealsBean("Quantum IT 4 sem",75,R.drawable.quant);
    list1.add(three);

    TopDealsBean four=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantum);
    list1.add(four);
    adapter.notifyDataSetChanged();

}

    public  void prepareDataStationery()
    {


        TopDealsBean one=new TopDealsBean("Drafter camel",200,R.drawable.drafter);
        list1.add(one);

        TopDealsBean two=new TopDealsBean("Quantum CS 3 sem",100,R.drawable.ind);
        list1.add(two);

        TopDealsBean three=new TopDealsBean("Quantum IT 4 sem",75,R.drawable.quant);
        list1.add(three);

        TopDealsBean four=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantum);
        list1.add(four);
        TopDealsBean five=new TopDealsBean("Quantum mechanical 8 sem",150,R.drawable.index);
        list1.add(five);

        TopDealsBean six=new TopDealsBean("C# book",450,R.drawable.csharp);
        list1.add(six);

        adapter.notifyDataSetChanged();

    }
    public  void prepareDataElectronics()
    {


        TopDealsBean one=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantu);
        list1.add(one);

        TopDealsBean two=new TopDealsBean("Quantum CS 3 sem",100,R.drawable.ind);
        list1.add(two);

        TopDealsBean three=new TopDealsBean("Quantum IT 4 sem",75,R.drawable.quant);
        list1.add(three);

        TopDealsBean four=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantum);
        list1.add(four);
        adapter.notifyDataSetChanged();

    }
    public  void prepareDataNotes()
    {


        TopDealsBean one=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantu);
        list1.add(one);

        TopDealsBean two=new TopDealsBean("Quantum CS 3 sem",100,R.drawable.ind);
        list1.add(two);

        TopDealsBean three=new TopDealsBean("Quantum IT 4 sem",75,R.drawable.quant);
        list1.add(three);

        TopDealsBean four=new TopDealsBean("Quantum mechanical 2 sem",150,R.drawable.quantum);
        list1.add(four);
        adapter.notifyDataSetChanged();

    }
    public  void prepareDataCalculators()
    {


        TopDealsBean one=new TopDealsBean("Casio 100-fx",500,R.drawable.clc);
        list1.add(one);

        TopDealsBean two=new TopDealsBean("Casio 300- mx",700,R.drawable.calc);
        list1.add(two);

        adapter.notifyDataSetChanged();

    }




}
