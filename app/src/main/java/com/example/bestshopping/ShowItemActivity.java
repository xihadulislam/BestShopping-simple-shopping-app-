package com.example.bestshopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestshopping.Adapters.MyAdapter;
import com.example.bestshopping.Model.UploadData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowItemActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "imageuri";
    public static final String EXTRA_CAPTION = "caption";
    public static final String EXTRA_DISCRIPTION = "description";
    public static final String EXTRA_PRICE = "price";

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    private  String category, title;
    private TextView tex;

    ArrayList<UploadData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);


        recyclerView = findViewById(R.id.showRecylerId);
        tex = findViewById(R.id.settext);


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        list = new ArrayList<>();


        String cat = getIntent().getStringExtra("name");


        if (cat.equals("Dress")){
            category = "Dress";
            tex.setText("Dress");
        }
        if (cat.equals("Nail Polish")){
            category = "Nail_Polish";
            tex.setText("Nail Polish");
        }

        if (cat.equals("Lipstick")){
            category = "Lipstick";
            tex.setText("Lipstick");
        }

        if (cat.equals("Ladies Ornaments")){
            category ="Ladies_Ornaments";
            tex.setText("Ladies Ornaments");
        }

        if (cat.equals("Boys Collection")){
            category = "Boys_Collection";
            tex.setText("Boys Collection");
        }
        if (cat.equals("Others")){
            category = "others";
            tex.setText("Others");
        }


        databaseReference = FirebaseDatabase.getInstance().getReference(category);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p = dataSnapshot1.getValue(UploadData.class);
                    list.add(p);

                }

                MyAdapter adapter = new MyAdapter(getApplicationContext(),list);

                adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(ShowItemActivity.this, ShowDetailsActivity.class);
                        UploadData uploadData = list.get(position);
                        intent.putExtra(EXTRA_URL, uploadData.getImageuri());
                        intent.putExtra(EXTRA_CAPTION, uploadData.getCaption());
                        intent.putExtra(EXTRA_DISCRIPTION, uploadData.getDescription());
                        intent.putExtra(EXTRA_PRICE, uploadData.getPrice());
                        intent.putExtra("k", uploadData.getId());
                        intent.putExtra("userid", uploadData.getUserid());
                        intent.putExtra("cat", uploadData.getCategory());

                        startActivity(intent);


                    }
                });
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ShowItemActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }


        });




    }
}


