package com.example.bestshopping.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestshopping.Model.CartData;
import com.example.bestshopping.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {



    Context context;
    ArrayList<CartData> cartData;

    //for item click listener
    private OnItemClickListener mListener;
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
//--------------------------------//


    public CartAdapter(Context c , ArrayList<CartData> p)
    {
        context = c;
        cartData = p;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notifyview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.id.setText(cartData.get(i).getNewid());


    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.notifytextid2);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(v,position);
                        }
                    }

                }
            });

        }
    }
}
