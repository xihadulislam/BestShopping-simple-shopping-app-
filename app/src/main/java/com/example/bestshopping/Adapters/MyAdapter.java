package com.example.bestshopping.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bestshopping.Main2222Activity;
import com.example.bestshopping.Model.UploadData;
import com.example.bestshopping.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    Context context;
    ArrayList<UploadData> uploadData;

    //for item click listener
    private OnItemClickListener mListener;
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
//--------------------------------//


    public MyAdapter(Context c , ArrayList<UploadData> p)
    {
        context = c;
        uploadData = p;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.caption.setText(uploadData.get(i).getCaption());
        Glide.with(context).load(uploadData.get(i).getImageuri()).into(myViewHolder.image);
        myViewHolder.price.setText(uploadData.get(i).getPrice());


    }

    @Override
    public int getItemCount() {
        return uploadData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView caption,price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            caption = itemView.findViewById(R.id.itemnameId);
            price = itemView.findViewById(R.id.itempriceId);
            image = itemView.findViewById(R.id.itemimgId);


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
