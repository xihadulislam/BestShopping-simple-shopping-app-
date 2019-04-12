package com.example.bestshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class APVActivity extends AppCompatActivity {




    private TextView textView1,textView2,textView3;
    private Button button1,button2;

    private DatabaseReference mref1,mref2,mref3,mref4;
    private ProgressDialog progressDialog;

    private  String p;
    private  int pr,acc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apv);


        textView1 = findViewById(R.id.orderId);
        textView2 = findViewById(R.id.trxId);
        textView3 = findViewById(R.id.phoone);
        button1 = findViewById(R.id.rejectId);
        button2 = findViewById(R.id.approveId);
        progressDialog = new ProgressDialog(this);


        final Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String userid = intent.getStringExtra("userid");
        final String order = intent.getStringExtra("order");
        final String trx = intent.getStringExtra("trx");
        final String upuserid = intent.getStringExtra("upuserid");
        final String price = intent.getStringExtra("price");
        final String phn = intent.getStringExtra("phone");


        textView1.setText(order);
        textView2.setText(trx);
        textView3.setText(phn);




        mref1 = FirebaseDatabase.getInstance().getReference("cart").child(userid).child(id);
        mref2 = FirebaseDatabase.getInstance().getReference("admin_notify").child(id);
        mref3 = FirebaseDatabase.getInstance().getReference("client_notify").child(userid).child(id);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                progressDialog.setMessage("Please Wait...!");
                progressDialog.show();

                mref1.child("status").setValue("approved").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        mref3.child("item").setValue("your order has been approved.");
                        progressDialog.dismiss();
                        startActivity(new Intent(APVActivity.this,AdminActivity.class));
                       finish();
                    }
                });
                mref2.removeValue();

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait...!");
                progressDialog.show();
                mref3.child("item").setValue("your order has been rejected").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        progressDialog.dismiss();
                        mref2.removeValue();
                        startActivity(new Intent(APVActivity.this,AdminActivity.class));
                        finish();
                    }
                });


            }
        });



    }
}
