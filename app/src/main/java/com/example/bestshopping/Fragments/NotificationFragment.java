package com.example.bestshopping.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestshopping.Model.NotifyData;
import com.example.bestshopping.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NotificationFragment extends Fragment {


    public NotificationFragment() {

    }



    private FirebaseRecyclerOptions<NotifyData> options;
    private FirebaseRecyclerAdapter<NotifyData, UiAdapter> firebaseRecyclerAdapter;

    private TextView textView;
    private RecyclerView recyclerView;

    private DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_notification, container, false);



        recyclerView = view.findViewById(R.id.notifyrecy2);
        textView = view.findViewById(R.id.nottext2);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("client_notify").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!dataSnapshot.exists()){
                    textView.setText("No Notification");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        options = new FirebaseRecyclerOptions.Builder<NotifyData>()
                .setQuery(databaseReference, NotifyData.class)
                .build();


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<NotifyData, UiAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UiAdapter holder, int position, @NonNull NotifyData model) {

                holder.setItem(model.getItem());
            }

            @NonNull
            @Override
            public UiAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater
                        .from(getContext())
                        .inflate(R.layout.usernotifyview, viewGroup, false);

                return new UiAdapter(v);
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

        public UiAdapter(@NonNull View itemView) {
            super(itemView);
            mview = itemView;


        }


        public void setItem(String item) {

            TextView text = mview.findViewById(R.id.notifytextid);
            text.setText(item);
        }


    }


}