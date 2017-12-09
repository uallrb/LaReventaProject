package com.example.harshvardhansingh.lareventaproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TopDealsAdapter extends RecyclerView.Adapter<TopDealsAdapter.MyViewHolder> {

    private Context mContext;
    private List<TopDealsBean> albumList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price,sno;
        public ImageView thumbnail, overflow;
        static View v;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.ItemName);
            price = (TextView) view.findViewById(R.id.textView2);
            sno=(TextView)view.findViewById(R.id.sno);
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
        holder.sno.setText(""+topDealsBean.getSno());
        //holder.count.setText(album.getNumOfSongs() + " pages");

        // loading album cover using Glide library
        Glide.with(mContext).load(topDealsBean.getThumbnail()).into(holder.thumbnail);

       // File file=new File(topDealsBean.getThumbnail());

        final Dbhelper db=new Dbhelper(mContext.getApplicationContext());

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

                String desc=new String(),name=new String(),phone=new String();
                    Cursor resserver= db.readtempoSno(topDealsBean.getSno());
                    while(resserver.moveToNext()) {
                        desc = resserver.getString(3);
                        name = resserver.getString(5);
                        phone = resserver.getString(6);


                        Intent i = new Intent(mContext.getApplicationContext(), ViewAdsActivity.class);
                        i.putExtra("proname", topDealsBean.getName());
                        i.putExtra("photo", topDealsBean.getThumbnail());
                        i.putExtra("proprice", topDealsBean.getPrice());
                        i.putExtra("proold", "6 months");
                        i.putExtra("prodecription", desc);
                        i.putExtra("prosellername", name);
                        i.putExtra("prosellernumber", phone);
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
