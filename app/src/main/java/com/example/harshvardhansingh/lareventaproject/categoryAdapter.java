package com.example.harshvardhansingh.lareventaproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by harsh vardhan singh on 18/05/16.
 */
public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<CategoryBean> albumList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
         static View v;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textView3);
            thumbnail =(ImageView) view.findViewById(R.id.imageView2);
            v=view;
        }
    }


    public categoryAdapter(Context mContext, List<CategoryBean> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CategoryBean categoryBean = albumList.get(position);
        holder.title.setText(categoryBean.getName());
        //holder.count.setText(album.getNumOfSongs() + " pages");

        // loading album cover using Glide library
        Glide.with(mContext).load(categoryBean.getThumbnail()).into(holder.thumbnail);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(categoryBean.getName().equals("Books"))
                {
                    Intent i=new Intent(mContext.getApplicationContext(),AdsActivity.class);


                    i.putExtra("adapter", "Books");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);

                }else
                if(categoryBean.getName().equals("Stationery"))
                { Intent i=new Intent(mContext.getApplicationContext(),AdsActivity.class);


                    i.putExtra("adapter", "Stationery");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);


                }else
                if(categoryBean.getName().equals("Electronics"))
                { Intent i=new Intent(mContext.getApplicationContext(),AdsActivity.class);


                    i.putExtra("adapter", "Electronics");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);


                }else
                if(categoryBean.getName().equals("Notes"))
                {
                    Intent i=new Intent(mContext.getApplicationContext(),AdsActivity.class);


                    i.putExtra("adapter", "Notes");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);

                }else
                if(categoryBean.getName().equals("Calculators"))
                {
                    Intent i=new Intent(mContext.getApplicationContext(),AdsActivity.class);


                    i.putExtra("adapter", "Calculators");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);

                }



            }
        });

           }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
