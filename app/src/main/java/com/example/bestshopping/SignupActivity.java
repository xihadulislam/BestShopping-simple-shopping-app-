package com.example.bestshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestshopping.Model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignupActivity extends AppCompatActivity {



    private Button button;
    MaterialEditText username,email,pass,address;
    TextView textView;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        getSupportActionBar().setTitle("Register");


        button = findViewById(R.id.signupId);
        username = findViewById(R.id.usernameId);
        email = findViewById(R.id.emailId);
        pass = findViewById(R.id.passwordId);
        textView = findViewById(R.id.backloginpage);
        address = findViewById(R.id.addId);
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Usname = username.getText().toString().trim();
                final String Email = email.getText().toString().trim();
                final String Pass = pass.getText().toString().trim();
                final String Add = address.getText().toString().trim();

                if (Usname.isEmpty()){
                    username.setError("UserName Required");
                }
                if (Email.isEmpty()){
                    email.setError("Email Required");
                }
                if (Pass.isEmpty()){
                    pass.setError("Password Required");
                }
                if (Add.isEmpty()){
                    address.setError("Address Required");
                }

                else
                {
                    progressDialog.setMessage("please wait...");
                    progressDialog.show();

                    firebaseAuth = FirebaseAuth.getInstance();

                    databaseReference = FirebaseDatabase.getInstance().getReference("userinfo");

                    firebaseAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                String uri= "https://firebasestorage.googleapis.com/v0/b/holla-5ff0e.appspot.com/o/images.png?alt=media&token=e55fc7e5-a784-46da-b873-bcfd761ca697";
                                UserData userData = new UserData(Usname,Add,Email,Pass,uri,user.getUid());

                                databaseReference.child(user.getUid()).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){

                                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                        }
                                        else
                                        {
                                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }



            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });



    }
}
