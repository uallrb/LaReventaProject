package com.example.harshvardhansingh.lareventaproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
  private static MySingleton minstance;
    private static Context mctx;
  private RequestQueue requestQueue;

  private MySingleton(Context context)
  {
    mctx=context;
    requestQueue=getRequestQueue();
  }

  private RequestQueue getRequestQueue()
  {

    if(requestQueue==null)

    {
      requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());

    }
    return requestQueue;
  }


  public static synchronized MySingleton getminstance(Context context)
  {

    if(minstance==null)
    {
      minstance=new MySingleton(context);

    }
    return minstance;
  }

  public<T> void addToRequestque(Request<T> request)
  {
    getRequestQueue().add(request);
  }
}
