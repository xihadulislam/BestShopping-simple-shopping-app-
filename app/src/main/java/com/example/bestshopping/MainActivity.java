package com.example.bestshopping;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    Handler handler;

    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.logo_id);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            startActivity(new Intent(MainActivity.this,Main2222Activity.class));
            finish();
        }
        else
        {

            handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setVisibility(View.VISIBLE);
                }
            },500);

            handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);



        }



    }
}
