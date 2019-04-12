package com.example.bestshopping.Fragments;


import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bestshopping.Adapters.CartAdapter;
import com.example.bestshopping.Adapters.MyAdapter;
import com.example.bestshopping.Main2222Activity;
import com.example.bestshopping.Model.CartData;
import com.example.bestshopping.Model.UploadData;
import com.example.bestshopping.R;
import com.example.bestshopping.ShowDetailsActivity;
import com.example.bestshopping.ShowItemActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.os.Environment.DIRECTORY_PICTURES;


public class CartFragment extends Fragment {


    public CartFragment() {

    }


    private RecyclerView recyclerView;
    private TextView textView;

    private DatabaseReference databaseReference;

    private FirebaseRecyclerOptions<CartData> options;
    private FirebaseRecyclerAdapter<CartData, UiAdapter> firebaseRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_cart, container, false);



        recyclerView = view.findViewById(R.id.cartRecyId);
        textView = view.findViewById(R.id.carttextId);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("cart").child(user.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!dataSnapshot.exists()){
                    textView.setText("No cart available.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));




        options = new FirebaseRecyclerOptions.Builder<CartData>()
                .setQuery(databaseReference, CartData.class)
                .build();


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CartData, UiAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UiAdapter holder, int position, @NonNull final CartData model) {

                holder.setCaption(model.getCaption());
                holder.setPrice(model.getPrice());
                holder.setImageuri(model.getImageuri());
                holder.setStatus(model.getStatus());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (model.getStatus().equals("Pending..")){

                            Toast.makeText(getActivity(), "please wait for admin approval..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            Toast.makeText(getActivity(), "Checkout process successfully Done", Toast.LENGTH_SHORT).show();

                        }


                    }
                });



            }


            @NonNull
            @Override
            public UiAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater
                        .from(getContext())
                        .inflate(R.layout.cart_item_view, viewGroup, false);


                return new CartFragment.UiAdapter(v);
            }
        };


        recyclerView.setAdapter(firebaseRecyclerAdapter);


        return view;
    }





    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();

    }





    public static class UiAdapter extends RecyclerView.ViewHolder {


        View mview;
        View button;
        public UiAdapter(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            button =itemView;


        }


        public void setCaption(String caption) {
            TextView Caption = mview.findViewById(R.id.cartnameitemId);
            Caption.setText(caption);
        }


        public void setPrice(String price) {
            TextView prices = mview.findViewById(R.id.cartitempriceId);
            prices.setText(price);
        }


        public void setImageuri(String imageuri) {
            ImageView Image = mview.findViewById(R.id.cartitemphotoId);
            Glide.with(mview.getContext()).load(imageuri).into(Image);

        }



        public void setStatus(String status) {
            TextView Status = mview.findViewById(R.id.statusId);
            Status.setText(status);
        }
    }


}
