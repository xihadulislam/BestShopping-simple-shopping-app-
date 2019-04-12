package com.example.bestshopping;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.bestshopping.Fragments.HomeFragment.EXTRA_CAPTION;
import static com.example.bestshopping.Fragments.HomeFragment.EXTRA_DISCRIPTION;
import static com.example.bestshopping.Fragments.HomeFragment.EXTRA_PRICE;
import static com.example.bestshopping.Fragments.HomeFragment.EXTRA_URL;

public class ShowDetailsActivity extends AppCompatActivity {

    private ImageView imageView1,imageView2;
    private TextView textView1, textView2,textView3,textView4,textView5,textView6;


    private DatabaseReference databaseReference,mdata;

    private  String cusername;

    private  String category;

    DatabaseReference dn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);



        imageView1 = findViewById(R.id.mainImageId2);
        textView1 = findViewById(R.id.finalpriceId2);
        textView4 = findViewById(R.id.mainCaptionId2);
        textView5 = findViewById(R.id.mainDiscriptionId2);
        textView6= findViewById(R.id.cattttt2);

        final Intent intent = getIntent();
        final String imageUrl = intent.getStringExtra(EXTRA_URL);
        final String caption = intent.getStringExtra(EXTRA_CAPTION);
        final String discription = intent.getStringExtra(EXTRA_DISCRIPTION);
        final String price = intent.getStringExtra(EXTRA_PRICE);
        final String key = intent.getStringExtra("k");
        final String upuserid = intent.getStringExtra("userid");
        final String cat = intent.getStringExtra("cat");


        Glide.with(this).load(imageUrl).centerInside().into(imageView1);

        textView1.setText(price);
        textView4.setText(caption);
        textView5.setText(discription);
        textView6.setText(cat);


//
//
//        if (cat.equals("Dress")){
//            category = "Dress";
//        }
//        if (cat.equals("Nail Polish")){
//            category = "Nail_Polish";
//        }
//
//        if (cat.equals("Lipstick")){
//            category = "Lipstick";
//        }
//
//        if (cat.equals("Ladies Ornaments")){
//            category ="Ladies_Ornaments";
//        }
//
//        if (cat.equals("Boys Collection")){
//            category = "Boys_Collection";
//        }
//        if (cat.equals("Others")){
//            category = "others";
//        }
//
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference(category).child(key);
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar snackbar = Snackbar
                        .make(view, "WANT TO TAKE IT?", Snackbar.LENGTH_LONG)
                        .setAction("CART", new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(ShowDetailsActivity.this, CartReqActivity.class);
                                intent.putExtra("img", imageUrl);
                                intent.putExtra("price", price);
                                intent.putExtra("cap", caption);
                                intent.putExtra("userid", upuserid);
                                startActivity(intent);
                               // finish();
                            }
                        });
                snackbar.setActionTextColor(Color.GREEN);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }
        });



    }

}
