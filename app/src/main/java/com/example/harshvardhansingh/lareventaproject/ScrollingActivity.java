package com.example.harshvardhansingh.lareventaproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollingActivity extends AppCompatActivity {
    RecyclerView recyclerView,recyclerView1;
    categoryAdapter adapter1;
    TopDealsAdapter adapter;
    List<TopDealsBean> list1;
    ImageButton button;
    List<CategoryBean> list;
    Dbhelper dbhelper;
   public static Activity fi;
    final String url1="http://172.16.2.253:8080/LaReventa/TopStories";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
           fi=this;


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(ScrollingActivity.this,CreateAdActivity.class);
                startActivity(i);
                    }
        });

        list1=new ArrayList<>();
        recyclerView1=(RecyclerView)findViewById(R.id.topDeals);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,true));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        adapter=new TopDealsAdapter(getApplicationContext(),list1);
        recyclerView1.setAdapter(adapter);
        recyclerView1.setNestedScrollingEnabled(false);


       // prepareTopDeals();


        list=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.Categories);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter1=new categoryAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter1);
        recyclerView.setNestedScrollingEnabled(false);

        prepareCategories();

         dbhelper=new Dbhelper(getApplicationContext());

       Cursor ds= dbhelper.readtempo1();
        if(ds.getCount()==0)
        {
            Intent i=new Intent(ScrollingActivity.this,LoginActivity.class);
            startActivity(i);
        }


        Cursor res= dbhelper.readtempoServer();
        if(res.getCount()!=0)
        {
            prepareTopDeals();
            fetchfromserver();

        }
        else if(res.getCount()==0)
        {
            Initialfetchfromserver();


        }



    }





    public void prepareTopDeals()
    {  list1.clear();

        Dbhelper db=new Dbhelper(getApplicationContext());
        Cursor res=db.readtempoServer();
        if(res.getCount()!=0)
        {

            while (res.moveToNext())
            {
                int no=res.getInt(0);
                String name=res.getString(1);
                float price=res.getFloat(2);
                String url=res.getString(4);

                TopDealsBean New=new TopDealsBean(name,(int)price, R.drawable.warn ,no);
                list1.add(New);
                Log.e("insert",name+" "+price);

            }

        }


  adapter.notifyDataSetChanged();
    }




     public void prepareCategories()
     {

         CategoryBean one=new CategoryBean("Books", R.drawable.archives);
         list.add(one);

         CategoryBean two=new CategoryBean("Stationery", R.drawable.pantone);
         list.add(two);

         CategoryBean three=new CategoryBean("Electronics", R.drawable.macbook);
         list.add(three);

         CategoryBean four=new CategoryBean("Notes", R.drawable.notebook);
         list.add(four);

         CategoryBean five=new CategoryBean("Calculators", R.drawable.calculator);
         list.add(five);



   adapter1.notifyDataSetChanged();

     }



    public void fetchfromserver()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("token", response);
                        dbhelper.topstoriesdelete();
                        Toast.makeText(getApplicationContext(),"local db deleted",Toast.LENGTH_SHORT).show();

                        JSONObject jsonObj = null;
                        try {
                            jsonObj = new JSONObject(response);
                            // Getting JSON Array node
                            JSONArray contents = jsonObj.getJSONArray("item");

                            // looping through All Contacts
                            for (int i = 0; i < contents.length(); i++) {
                                JSONObject c = contents.getJSONObject(i);

                                String itemname = c.getString("itemname");
                                String itemcategory = c.getString("itemcategory");
                                String itemprice = c.getString("itemprice");
                                String itemold =c.getString("itemold");
                                String ownername = c.getString("ownername");
                                String ownerphone = c.getString("ownerphone");
                                String owneremail = c.getString("owneremail");
                                String posteddate = c.getString("posteddate");
                                String postedtime = c.getString("postedtime");
                                String itemdescription = c.getString("itemdescription");
                                String url = c.getString("imageurl");

                               boolean chk= dbhelper.topdealsinsertServer(itemname, itemdescription,url, Float.parseFloat(itemprice), ownerphone, ownername,6,itemcategory);
                               if(chk){
                                   Log.e("insert",itemname+" "+itemprice);

                               }
                                // Log.e("insert",name+" "+price);


                                //Log.e("token",  + " " + name + " " + title);

                                //dbhelper.topdealsinsert(title, content, url, date, time);


                            }
                            prepareTopDeals();

                            // adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("token", error.toString());
                Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"fetching from local db",Toast.LENGTH_SHORT).show();
                prepareTopDeals();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Title","Hello Harsh vardhan!");

                // params.put("Name" ,name.getText().toString());
                //params.put("Email",email.getText().toString());

                return params;

            }

        };
        MySingleton.getminstance(getApplicationContext()).addToRequestque(stringRequest);




    }



    public void Initialfetchfromserver()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("token", response);

                        JSONObject jsonObj;
                        try {
                            jsonObj = new JSONObject(response);
                            // Getting JSON Array node
                            JSONArray contents = jsonObj.getJSONArray("item");

                            // looping through All Contacts
                            for (int i = 0; i < contents.length(); i++) {
                                JSONObject c = contents.getJSONObject(i);

                                String itemname = c.getString("itemname");
                                String itemcategory = c.getString("itemcategory");
                                String itemprice = c.getString("itemprice");
                                String itemold =c.getString("itemold");
                                String ownername = c.getString("ownername");
                                String ownerphone = c.getString("ownerphone");
                                String owneremail = c.getString("owneremail");
                                String posteddate = c.getString("posteddate");
                                String postedtime = c.getString("postedtime");
                                String itemdescription = c.getString("itemdescription");
                                String url = c.getString("imageurl");

                                dbhelper.topdealsinsertServer(itemname, itemdescription,url, Float.parseFloat(itemprice), ownerphone, ownername,6,itemcategory);


                            }
                            prepareTopDeals();

                            // adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("token", error.toString());
                Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Initial fetch", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Title","Hello Harsh vardhan!");

                // params.put("Name" ,name.getText().toString());
                //params.put("Email",email.getText().toString());

                return params;

            }

        };
        MySingleton.getminstance(getApplicationContext()).addToRequestque(stringRequest);




    }

}
