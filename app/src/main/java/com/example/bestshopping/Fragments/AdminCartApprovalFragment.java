package com.example.bestshopping.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bestshopping.APVActivity;
import com.example.bestshopping.Adapters.CartAdapter;
import com.example.bestshopping.Adapters.MyAdapter;
import com.example.bestshopping.Model.CartData;
import com.example.bestshopping.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminCartApprovalFragment extends Fragment {


    public AdminCartApprovalFragment() {

    }



    private DatabaseReference databaseReference;
    ArrayList<CartData> list;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view =inflater.inflate(R.layout.fragment_admin_cart_approval, container, false);


        recyclerView = view.findViewById(R.id.recy1);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Log.d("kita","aio");

        databaseReference = FirebaseDatabase.getInstance().getReference("admin_notify");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                list = new ArrayList<CartData>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    CartData c = dataSnapshot1.getValue(CartData.class);
                    list.add(c);

                }
                CartAdapter adapter = new CartAdapter(getContext(),list);
               adapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(View view, int position) {

                       Intent intent = new Intent(getActivity(), APVActivity.class);
                       CartData cartData = list.get(position);

                       intent.putExtra("id", cartData.getNewid());
                       intent.putExtra("userid", cartData.getUsid());
                       intent.putExtra("order", cartData.getOrderid());
                       intent.putExtra("trx", cartData.getTrxid());
                       intent.putExtra("upuserid", cartData.getUpuserid());
                       intent.putExtra("price", cartData.getPrice());
                       intent.putExtra("phone", cartData.getPhone());
                       startActivity(intent);
                   }
               });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
