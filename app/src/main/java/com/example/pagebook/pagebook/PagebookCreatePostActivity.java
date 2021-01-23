package com.example.pagebook.pagebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pagebook.R;
import com.example.pagebook.pagebook.builder.RetrofitBuilder;
import com.example.pagebook.pagebook.model.AddPost;
import com.example.pagebook.pagebook.model.ResponsePost;
import com.example.pagebook.pagebook.network.IPostApis;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PagebookCreatePostActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    Uri imageFileUri;
    ImageView iv_add_image;
    Button bt_add_image;
    AddPost addPost = new AddPost();


    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagebook_create_post);

        iv_add_image = findViewById(R.id.iv_pagebook_create_post_image_upload);
        bt_add_image = findViewById(R.id.btn_pagebook_create_post_upload_image);
        bt_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, RESULT_LOAD_IMAGE);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String pb_username = sharedPreferences.getString("pb_username", null);
        String pb_userid = sharedPreferences.getString("pb_userid", null);
        if (pb_userid != null)
            addPost.setUserId(pb_userid);
        addPost.setSharedPostId(null);
        EditText et_text = findViewById(R.id.et_pagebook_create_post_text);
        EditText et_category = findViewById(R.id.et_pagebook_create_post_category);
        addPost.setPostCategory(et_category.getText().toString());
        if (et_text.getText().toString() != null) {
            addPost.setPostType(1);
            addPost.setPostText(et_text.getText().toString());
        } else {
            imageUpload();
        }
        findViewById(R.id.btn_pagebook_create_post_add_post).setOnClickListener(
                view->{
                    imageUpload();
                }
        );
    }


    void imageUpload() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
        long time = System.currentTimeMillis();
        StorageReference storageReference = mStorageRef.child("images/" + addPost.getUserId() + String.valueOf(time));
        storageReference.putFile(imageFileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                Toast.makeText(PagebookCreatePostActivity.this, downloadUrl.toString(), Toast.LENGTH_SHORT).show();
                downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(PagebookCreatePostActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
                        addPost.setPostType(2);
                        addPost.setPostUrl(uri.toString());
                        Log.d("TAG", "onSuccess: " + uri.toString());
                        Retrofit retrofit = RetrofitBuilder.getInstance();
                        IPostApis iApiInterface = retrofit.create(IPostApis.class);
                        addPost.setUserId("102");
                        addPost.setPostCategory("General");
                        addPost.setPostType(2);
                        Call<ResponsePost> apiCallAddUser = iApiInterface.addPost(addPost);
                        Callback<ResponsePost> callback = new Callback<ResponsePost>() {
                            @Override
                            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                                Toast.makeText(PagebookCreatePostActivity.this,"uploaded",Toast.LENGTH_LONG).show();
                                //Intent intent = new Intent(PagebookCreatePostActivity.this, PagebookCreatePostActivity.class);
                                //startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ResponsePost> call, Throwable t) {
                                Toast.makeText(PagebookCreatePostActivity.this, "Unable to upload post.\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        };
                        apiCallAddUser.enqueue(callback);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PagebookCreatePostActivity.this,"ABCDE", Toast.LENGTH_SHORT).show();
                        Toast.makeText(PagebookCreatePostActivity.this, "Couldn't upload file.", Toast.LENGTH_SHORT).show();
                    }
                });
//

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE){
            iv_add_image.setImageURI(data.getData());
            //.setText("Change Profile Image");
            imageFileUri = data.getData();
            Log.d("imageuri3", imageFileUri.toString());
        }
    }
}
