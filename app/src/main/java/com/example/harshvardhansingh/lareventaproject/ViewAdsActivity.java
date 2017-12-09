package com.example.harshvardhansingh.lareventaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewAdsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ads);

        Intent i=getIntent();
        Bundle bundle=new Bundle();

        bundle=i.getExtras();

        int imageres=bundle.getInt("photo");
        String proname=bundle.getString("proname");
        int proprice=bundle.getInt("proprice");
        String proold=bundle.getString("proold");
        String prodecription=bundle.getString("prodecription");
        String prosellername=bundle.getString("prosellername");
        String prosellernumber=bundle.getString("prosellernumber");

        ImageView imageView=(ImageView)findViewById(R.id.imageView3) ;

        TextView productname=(TextView)findViewById(R.id.productname);
        TextView productprice=(TextView)findViewById(R.id.textView5);
        TextView productold=(TextView)findViewById(R.id.textView6);
        TextView productdescription=(TextView)findViewById(R.id.textView9);
        TextView sellername=(TextView)findViewById(R.id.sellername);
        final TextView sellerphonr=(TextView)findViewById(R.id.sellerphone);

        productname.setText(proname);
        productprice.setText(""+proprice);
        productold.setText(proold);
        productdescription.setText(prodecription);
        sellername.setText(prosellername);
        sellerphonr.setText(prosellernumber);
       // imageView.setImageResource(imageres);
        Glide.with(getApplicationContext()).load(imageres).into(imageView);


        FloatingActionButton sendsms=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        sendsms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(sellerphonr.getText().toString(),null,"hello" ,null,null);

            }
        });

    }
}
