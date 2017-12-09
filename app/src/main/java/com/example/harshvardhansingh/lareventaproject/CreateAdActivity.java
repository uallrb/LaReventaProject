package com.example.harshvardhansingh.lareventaproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateAdActivity extends AppCompatActivity {
    private static final int SELECT_SINGLE_PICTURE = 101;

    private static final int SELECT_MULTIPLE_PICTURE = 201;

    public static final String IMAGE_TYPE = "image/*";

    private ImageView selectedImagePreview;
    public  EditText name,price,description,sellername,sellerphone,itemold;
    private Dbhelper db;
    private TextView category;
    private Button choose;
    final String url1="http://172.16.2.253:8080/LaReventa/AdsInsert";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        db = new Dbhelper(getApplicationContext());

        selectedImagePreview = (ImageView) findViewById(R.id.imageView4);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        name = (EditText) findViewById(R.id.editText);
        price = (EditText) findViewById(R.id.editText2);
        description = (EditText) findViewById(R.id.editText3);
        sellername = (EditText) findViewById(R.id.editText4);
        sellerphone = (EditText) findViewById(R.id.editText5);
        itemold = (EditText) findViewById(R.id.editText6);
        category = (TextView) findViewById(R.id.textView18);
        choose = (Button) findViewById(R.id.button2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_picture)), SELECT_SINGLE_PICTURE);

            }
        });

        Button post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = db.readurl();

                if (res.getCount() != 0) {
                    while (res.moveToNext()) {

                        String url = res.getString(1);
                        Boolean chk = db.topdealsinsert(name.getText().toString(), description.getText().toString(), url, Float.parseFloat(price.getText().toString().trim()), sellerphone.getText().toString(), "Aishwarya singh", 6, "");
                        insertintoserver();
                        if (chk) {
                            Log.e("Success", "Inserted succesfully");
                        }
                        ScrollingActivity.fi.finish();
                        Intent i=new Intent(CreateAdActivity.this,ScrollingActivity.class);
                        startActivity(i);
                           ////}
                    }
                }

            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(CreateAdActivity.this);
                //  builderSingle.setIcon(R.drawable.ic_launcher);
                builderSingle.setTitle("Sort by:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CreateAdActivity.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Books");
                arrayAdapter.add("Stationery");
                arrayAdapter.add("Electronics");
                arrayAdapter.add("Notes");
                arrayAdapter.add("Calculators");

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                       /* AlertDialog.Builder builderInner = new AlertDialog.Builder(CreateAdActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Category is");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();*/
                        category.setText(strName);
                        Log.e("token", category.getText().toString());
                    }
                });
                builderSingle.show();
            }


        });


    }

    public void  onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri;
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {

                 selectedImageUri = data.getData();
                Log.e("url",""+selectedImageUri);

                Cursor res=db.readurl();
                if(res.getCount()==0)
                {
                    db.sdurlinsert(""+selectedImageUri);

                }else{
                    db.updateurl(""+selectedImageUri);
                }

              //  Dbhelper db=new Dbhelper(getApplicationContext());
                try {
                    selectedImagePreview.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                } catch (IOException e) {
                    Log.e(CreateAdActivity.class.getSimpleName(), "Failed to load image", e);
                }
                // original code
//                String selectedImagePath = getPath(selectedImageUri);
//                selectedImagePreview.setImageURI(selectedImageUri);
            }
                   } else {
            // report failure
            Toast.makeText(getApplicationContext(), R.string.msg_failed_to_get_intent_data, Toast.LENGTH_LONG).show();
            Log.d(CreateAdActivity.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);
        }
       }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {

        // just some safety built in
        if( uri == null ) {
            // perform some logging or show user feedback
            Toast.makeText(getApplicationContext(), R.string.msg_failed_to_get_picture, Toast.LENGTH_LONG).show();
            Log.d(CreateAdActivity.class.getSimpleName(), "Failed to parse image path from image URI " + uri);
            return null;
        }

        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here, thanks to the answer from @mad indicating this is needed for
        // working code based on images selected using other file managers
        return uri.getPath();
    }

    public void insertintoserver()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("token", response);
                        finish();
                        ScrollingActivity.fi.finish();
                        Intent i=new Intent(CreateAdActivity.this,ScrollingActivity.class);
                        startActivity(i);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("token", error.toString());
                Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_SHORT).show();
                }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                 params.put("itemname" ,name.getText().toString());
                params.put("itemcategory",category.getText().toString());
                Log.e("token", category.getText().toString());
                params.put("itemprice",price.getText().toString());
                params.put("itemold",itemold.getText().toString());
                params.put("ownername",sellername.getText().toString());
                params.put("ownerphone",sellerphone.getText().toString());
                params.put("owneremail","aishwarya@gmail.com");
                params.put("posteddate","12/12/12");
                params.put("postedtime","12:00");
                params.put("itemdescription",description.getText().toString());
                params.put("imageurl","");

                return params;

            }

        };
        MySingleton.getminstance(getApplicationContext()).addToRequestque(stringRequest);

    }
}
