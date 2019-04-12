package com.example.bestshopping.Fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestshopping.Adapters.MyAdapter;
import com.example.bestshopping.Model.UploadData;
import com.example.bestshopping.R;
import com.example.bestshopping.ShowDetailsActivity;
import com.example.bestshopping.ShowItemActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {


    public static final String EXTRA_URL = "imageuri";
    public static final String EXTRA_CAPTION = "caption";
    public static final String EXTRA_DISCRIPTION = "description";
    public static final String EXTRA_PRICE = "price";

    private ProgressDialog progressDialog;
    private Button button,button2,button3,button4,button5,button6;
    public RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6;
    private DatabaseReference mref,mref2,mref3,mref4,mref5,mref6;

    private  TextView art,wild,land,mobile,macro,wedd;
    ArrayList<UploadData> list;
    ArrayList<UploadData> list2;
    ArrayList<UploadData> list3;
    ArrayList<UploadData> list4;
    ArrayList<UploadData> list5;
    ArrayList<UploadData> list6;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = view.findViewById(R.id.reclyleId1);
        recyclerView2 = view.findViewById(R.id.reclyleId2);
        recyclerView3 = view.findViewById(R.id.reclyleId3);
        recyclerView4 = view.findViewById(R.id.reclyleId4);
        recyclerView5 = view.findViewById(R.id.reclyleId5);
        recyclerView6 = view.findViewById(R.id.reclyleId6);



        mref = FirebaseDatabase.getInstance().getReference("Dress");
        mref2 = FirebaseDatabase.getInstance().getReference("Nail_Polish");
        mref3 = FirebaseDatabase.getInstance().getReference("Lipstick");
        mref4 = FirebaseDatabase.getInstance().getReference("Ladies_Ornaments");
        mref5 = FirebaseDatabase.getInstance().getReference("Boys_Collection");
        mref6 = FirebaseDatabase.getInstance().getReference("others");


        mref.keepSynced(true);
        mref2.keepSynced(true);
        mref3.keepSynced(true);
        mref4.keepSynced(true);
        mref5.keepSynced(true);
        mref6.keepSynced(true);


        button = view.findViewById(R.id.artphotogrphyId);
        button2 = view.findViewById(R.id.WildlifeId);
        button3 = view.findViewById(R.id.LandscapeId);
        button4 = view.findViewById(R.id.Mobile_photographyId);
        button5 = view.findViewById(R.id.MacroId);
        button6 = view.findViewById(R.id.WeddingId);



        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);



        art = view.findViewById(R.id.art1);
        wild = view.findViewById(R.id.wild1);
        land = view.findViewById(R.id.land1);
        mobile = view.findViewById(R.id.mobile);
        macro = view.findViewById(R.id.macro);
        wedd = view.findViewById(R.id.weed);

        progressDialog = new ProgressDialog(getActivity());


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        mref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


               list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p = dataSnapshot1.getValue(UploadData.class);
                    list.add(p);

                }

                MyAdapter adapter = new MyAdapter(getContext(),list);

                adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
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
                art.setText("Dress");
                button.setVisibility(view.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }


        });



        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        list2 = new ArrayList<>();
        mref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               list2.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p2 = dataSnapshot1.getValue(UploadData.class);
                    list2.add(p2);
                }
                MyAdapter adapter2 = new MyAdapter(getContext(),list2);

                adapter2.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
                        UploadData uploadData = list2.get(position);
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
                recyclerView2.setAdapter(adapter2);

                wild.setText("Nail Polish");
                button2.setVisibility(view.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });





        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(linearLayoutManager3);

        list3 = new ArrayList<>();
        mref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               list3.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p3 = dataSnapshot1.getValue(UploadData.class);
                    list3.add(p3);
                }
                MyAdapter adapter3 = new MyAdapter(getContext(),list3);

                adapter3.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
                        UploadData uploadData = list3.get(position);
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
                recyclerView3.setAdapter(adapter3);


                land.setText("Lipstick");
                button3.setVisibility(view.VISIBLE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });



        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(linearLayoutManager4);
        list4 = new ArrayList<>();
        mref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           list4.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p4 = dataSnapshot1.getValue(UploadData.class);
                    list4.add(p4);
                }
                MyAdapter adapter4 = new MyAdapter(getContext(),list4);
                adapter4.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
                        UploadData uploadData = list4.get(position);
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
                recyclerView4.setAdapter(adapter4);

                mobile.setText("Ladies Ornaments");
                button4.setVisibility(view.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });



        recyclerView5.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView5.setLayoutManager(linearLayoutManager5);
        list5 = new ArrayList<>();
        mref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            list5.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p5 = dataSnapshot1.getValue(UploadData.class);
                    list5.add(p5);
                }
                MyAdapter adapter5 = new MyAdapter(getContext(),list5);

                adapter5.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
                        UploadData uploadData = list5.get(position);
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

                recyclerView5.setAdapter(adapter5);

                macro.setText("Boys Collection");
                button5.setVisibility(view.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });




        recyclerView6.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView6.setLayoutManager(linearLayoutManager6);

        list6 = new ArrayList<>();
        mref6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list6.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    UploadData p6 = dataSnapshot1.getValue(UploadData.class);
                    list6.add(p6);
                }
                MyAdapter adapter6 = new MyAdapter(getContext(),list6);

                adapter6.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
                        UploadData uploadData = list6.get(position);
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
                recyclerView6.setAdapter(adapter6);


                wedd.setText("Others");
                button6.setVisibility(view.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });






        return view;
    }

    @Override
    public void onClick(View v) {


        if (v.getId()==R.id.artphotogrphyId){
            Intent intent  = new Intent(getActivity(), ShowItemActivity.class);
            intent.putExtra("name", "Dress");
            startActivity(intent);
        }
        if (v.getId()==R.id.WildlifeId){
            Intent intent  = new Intent(getActivity(),ShowItemActivity.class);
            intent.putExtra("name", "Nail Polish");
            startActivity(intent);
        }
        if (v.getId()==R.id.LandscapeId){
            Intent intent  = new Intent(getActivity(),ShowItemActivity.class);
            intent.putExtra("name", "Lipstick");
            startActivity(intent);
        }
        if (v.getId()==R.id.Mobile_photographyId){
            Intent intent  = new Intent(getActivity(),ShowItemActivity.class);
            intent.putExtra("name", "Ladies Ornaments");
            startActivity(intent);
        }
        if (v.getId()==R.id.MacroId){
            Intent intent  = new Intent(getActivity(),ShowItemActivity.class);
            intent.putExtra("name", "Boys Collection");
            startActivity(intent);
        }
        if (v.getId()==R.id.WeddingId){
            Intent intent  = new Intent(getActivity(),ShowItemActivity.class);
            intent.putExtra("name", "Others");
            startActivity(intent);
        }



    }
}
