package com.example.bestshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    MaterialEditText email,pass;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private  TextView fpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.texttttt);
        email = findViewById(R.id.loginemailId);
        pass = findViewById(R.id.loginPassId);
        button = findViewById(R.id.loginId);
        progressDialog = new ProgressDialog(this);

        fpass = findViewById(R.id.forgotpass);

//        fpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,AdminActivity.class));
//            }
//        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getText().toString().trim();
                final String Pass = pass.getText().toString().trim();

                if (Email.isEmpty()){
                    email.setError("Email Required");
                }
                else if (Pass.isEmpty()){
                    pass.setError("Password Required");
                }
                else {



                    progressDialog.setMessage("please wait...");
                    progressDialog.show();


                    if (Email.equals("admin")&&Pass.equals("admin")){
                        progressDialog.dismiss();
                        startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                        finish();

                    }

                    else
                    {
                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    startActivity(new Intent(LoginActivity.this,Main2222Activity.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }


                }
            }
        });


    }
}
