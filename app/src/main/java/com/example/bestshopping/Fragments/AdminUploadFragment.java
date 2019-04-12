package com.example.bestshopping.Fragments;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bestshopping.AdminActivity;
import com.example.bestshopping.Model.UploadData;
import com.example.bestshopping.R;
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


public class AdminUploadFragment extends Fragment {


    public AdminUploadFragment() {

    }




    private ImageView showphoto;
    private Button takephoto, uploadphoto;
    private EditText caption, discription,priceEd;
    private Spinner category;
    private static final int PICK_IMAGE_REQUEST = 1;

    private DatabaseReference databaseReference, databaseReference2,db,db2;
    private StorageReference storageReference;
    private Uri muri;
    private StorageTask mUploadTask;
    private ProgressDialog progressDialog;

    private String[] categoris;

    public  String userid="";
    String captionvalue;
    String categoryspinerselected;
    String settpirce;
    String discriptionvalue;
    String Imguri;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_upload, container, false);




        showphoto =view.findViewById(R.id.showphotoId);
        takephoto = view.findViewById(R.id.takephotoId);
        caption = view.findViewById(R.id.captionId);
        category = view.findViewById(R.id.categoryspinerId);

        discription = view.findViewById(R.id.descriptionId);
        uploadphoto =view.findViewById(R.id.uploadbuttonId);
        priceEd = view.findViewById(R.id.priceEdId);
        progressDialog = new ProgressDialog(getActivity());

        categoris = getResources().getStringArray(R.array.category_Name);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinarview, R.id.spinerviewId, categoris);

        category.setAdapter(adapter);




        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });





        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                captionvalue = caption.getText().toString().trim();
                categoryspinerselected = category.getSelectedItem().toString();
                settpirce = priceEd.getText().toString().trim();;
                discriptionvalue = discription.getText().toString().trim();


                if (captionvalue.isEmpty()) {
                    caption.setError("Caption is required");
                }
               else if (discriptionvalue.isEmpty()) {
                    discription.setError("Discription is required");
                }

                else if (settpirce.isEmpty()) {
                    priceEd.setError("Price is required");
                }

                else
                {
                    if (mUploadTask != null && mUploadTask.isInProgress()) {
                        Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                    } else {

                        if (categoryspinerselected.equals("Dress")) {
                            databaseReference = FirebaseDatabase.getInstance().getReference("Dress");
                            storageReference = FirebaseStorage.getInstance().getReference("Dress");
                            photoupload();
                        } else if (categoryspinerselected.equals("Nail Polish")) {
                            databaseReference = FirebaseDatabase.getInstance().getReference("Nail_Polish");
                            storageReference = FirebaseStorage.getInstance().getReference("Nail_Polish");
                            photoupload();
                        } else if (categoryspinerselected.equals("Lipstick")) {
                            databaseReference = FirebaseDatabase.getInstance().getReference("Lipstick");
                            storageReference = FirebaseStorage.getInstance().getReference("Lipstick");
                            photoupload();
                        } else if (categoryspinerselected.equals("Ladies Ornaments")) {
                            databaseReference = FirebaseDatabase.getInstance().getReference("Ladies_Ornaments");
                            storageReference = FirebaseStorage.getInstance().getReference("Ladies_Ornaments");
                            photoupload();
                        } else if (categoryspinerselected.equals("Boys Collection")) {
                            databaseReference = FirebaseDatabase.getInstance().getReference("Boys_Collection");
                            storageReference = FirebaseStorage.getInstance().getReference("Boys_Collection");
                            photoupload();
                        } else if (categoryspinerselected.equals("Others")) {
                            databaseReference = FirebaseDatabase.getInstance().getReference("others");
                            storageReference = FirebaseStorage.getInstance().getReference("others");
                            photoupload();
                        }

                    }

                }



            }
        });



        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

            muri = data.getData();
            Glide.with(getActivity()).load(muri).into(showphoto);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver resolver = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(resolver.getType(uri));
    }




    private void photoupload() {

        if (muri != null) {
            progressDialog.setMessage("Please Wait...!");
            progressDialog.show();
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(muri));

            mUploadTask = fileReference.putFile(muri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    final Uri imageuri = uri;
                                    final String Id = databaseReference.push().getKey();
                                    final UploadData uploadData = new UploadData(captionvalue, categoryspinerselected, settpirce, discriptionvalue, imageuri.toString(),userid,Id);

                                    databaseReference.child(Id).setValue(uploadData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getActivity(), "Upload Successful..", Toast.LENGTH_SHORT).show();


                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                databaseReference2 = FirebaseDatabase.getInstance().getReference("All");
                                                databaseReference2.child(Id).setValue(uploadData);
                                                Intent intent = new Intent(getActivity(), AdminActivity.class);
                                                startActivity(intent);
                                                getActivity().finish();

                                            } else {
                                                Toast.makeText(getActivity(), "Fails..", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });


                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(getActivity(), "No Photo Selected.", Toast.LENGTH_SHORT).show();
        }


    }










}
