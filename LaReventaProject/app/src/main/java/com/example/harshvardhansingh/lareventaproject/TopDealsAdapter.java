package com.example.harshvardhansingh.lareventaproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by harsh vardhan singh on 18/05/16.
 */
public class TopDealsAdapter extends RecyclerView.Adapter<TopDealsAdapter.MyViewHolder> {

    private Context mContext;
    private List<TopDealsBean> albumList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        public ImageView thumbnail, overflow;
        static View v;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.ItemName);
            price = (TextView) view.findViewById(R.id.textView2);

            thumbnail =(ImageView) view.findViewById(R.id.imageView);
           v=view;
        }
    }


    public TopDealsAdapter(Context mContext, List<TopDealsBean> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_deals_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final TopDealsBean topDealsBean = albumList.get(position);
        holder.title.setText(topDealsBean.getName());
        holder.price.setText(""+topDealsBean.getPrice());
        //holder.count.setText(album.getNumOfSongs() + " pages");

        // loading album cover using Glide library
        Glide.with(mContext).load(topDealsBean.getThumbnail()).into(holder.thumbnail);

       // File file=new File(topDealsBean.getThumbnail());

        Dbhelper db=new Dbhelper(mContext.getApplicationContext());

       /* Cursor res=db.readurl();
        while (res.moveToNext()) {
            try{
                Picasso.with(mContext.getApplicationContext()).load(topDealsBean.getThumbnail()).error(R.drawable.archives).into(holder.thumbnail);

            }catch (Exception e)
            {
                Log.e("sdr",e.toString());
            }
            }*/


        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(mContext.getApplicationContext(),ViewAdsActivity.class);
                i.putExtra("proname",topDealsBean.getName());
                i.putExtra("photo",topDealsBean.getThumbnail());
                i.putExtra("proprice",topDealsBean.getPrice());
                i.putExtra("proold","6 months");
                i.putExtra("prodecription"," the product is in a very nice contition. It is a very useful product .I am selling this because its of no use for me right now ,interested students may contact me anytime they want");
                i.putExtra("prosellername","Aishwarya Singh Rajpoot");
                i.putExtra("prosellernumber","8588046697");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);




            }
        });

    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
