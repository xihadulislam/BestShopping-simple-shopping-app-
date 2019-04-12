package com.example.bestshopping;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bestshopping.Adapters.TabAdapter;
import com.example.bestshopping.Fragments.AdminCartApprovalFragment;
import com.example.bestshopping.Fragments.AdminUploadFragment;
import com.example.bestshopping.Model.UploadData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class AdminActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tabLayout = findViewById(R.id.tablayoutId);
        viewPager = findViewById(R.id.viewpagerId);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new AdminUploadFragment(), "Upload a product ");
        tabAdapter.addFragment(new AdminCartApprovalFragment()," Cart Approved");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }



}



