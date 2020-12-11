package com.example.usersapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.usersapp.R;
import com.example.usersapp.data.User;
import com.example.usersapp.data.UserViewModel;

import static com.example.usersapp.ui.AddEditUserActivity.*;


public class UserDetailsActivity extends AppCompatActivity {
    //constants
    private static final int EDIT_USER_REQUEST_CODE = 2;
    //widgets
    private TextView mName, mEmail,mPhone, showBio;
    private ImageView profile_image;
    private EditText editBio;
    private Button submitBio;
    private ImageButton editBioPencil;
    //variables
    private int id;
    private String firstName, lastName, email,phone;
    //data
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //database
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        //widgets
        mName = findViewById(R.id.profile_name);
        mEmail = findViewById(R.id.profile_email);
        mPhone =  findViewById(R.id.profile_phone);
        profile_image =  findViewById(R.id.profile_image);
        editBioPencil = findViewById(R.id.edit_bio_pencil);

        editBio = findViewById(R.id.edit_bio);
        showBio = findViewById(R.id.show_bio);
        submitBio =  findViewById(R.id.submit_bio);

        //profile image
        Glide.with(this).load(R.drawable.profile_picture).into(profile_image);


        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            id = intent.getIntExtra(AddEditUserActivity.EXTRA_ID,1);
            firstName = intent.getStringExtra(EXTRA_FIRSTNAME);
            lastName = intent.getStringExtra(EXTRA_LASTNAME);
            email = intent.getStringExtra(EXTRA_EMAIL);
            phone = intent.getStringExtra(EXTRA_PHONE);
            setTitle(firstName + " " + lastName);

            mName.setText(firstName + " " + lastName );
            mEmail.setText(email);
            mPhone.setText(phone);
        }
        else{
            Toast.makeText(this, "No User Availbale", Toast.LENGTH_LONG).show();
            finish();
        }

        profile_image.setOnClickListener(view -> uploadImage());


        setShowBio();

    }

    private void uploadImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,5);
    }

    void setShowBio(){
        editBio.setOnClickListener(view -> submitBio.setVisibility(View.VISIBLE));
        //make the click on edit button to make submit button vissible
        if(!showBio.getText().toString().isEmpty()){
            editBioPencil.setVisibility(View.VISIBLE);
            editBioPencil.setOnClickListener(
                    view -> {
                        editBio.setText(showBio.getText().toString());
                        editBio.setVisibility(View.VISIBLE);
                        showBio.setVisibility(View.INVISIBLE);
                    }
            );

        if(!editBio.getText().toString().isEmpty()) {submitBio.setVisibility(View.VISIBLE);}else {
            submitBio.setVisibility(View.INVISIBLE);
        }}
        submitBio.setOnClickListener(view -> {
            if(editBio.getText().toString().isEmpty()){
                Toast.makeText(this,"Bio is Empty",Toast.LENGTH_LONG).show();
            }else{showBio.setText(editBio.getText().toString());
                showBio.setVisibility(View.VISIBLE);
                submitBio.setVisibility(View.INVISIBLE);
                editBio.setVisibility(View.INVISIBLE);
            }
        });


        }

       /* if(showBio.getText().toString() != null){
            editBio.setVisibility(View.GONE);submitBio.setVisibility(View.GONE);
        }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //user edit
        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            int id = data.getIntExtra(AddEditUserActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Update failed...", Toast.LENGTH_LONG).show();
                return;
            }
            String firstName = data.getStringExtra(AddEditUserActivity.EXTRA_FIRSTNAME);
            String lastName = data.getStringExtra(AddEditUserActivity.EXTRA_LASTNAME);
            String email = data.getStringExtra(AddEditUserActivity.EXTRA_EMAIL);
            String phone = data.getStringExtra(AddEditUserActivity.EXTRA_PHONE);

            User user = new User(firstName, lastName, email, phone);
            user.setId(id);

            viewModel.update(user);
            Toast.makeText(this, "User Update Successful", Toast.LENGTH_LONG).show();


        }
        //upload image

        if(requestCode == 5 && resultCode == RESULT_OK && null != data){
            Bundle bundle = data.getExtras();
            if(null != bundle){
                Bitmap bitmap = (Bitmap) bundle.get("data");

                Glide.with(this).asBitmap().load(bitmap).into(profile_image);
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_details_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_user:
                Intent intent = new Intent(this, AddEditUserActivity.class);
                intent.putExtra(EXTRA_ID,id);
                intent.putExtra(EXTRA_FIRSTNAME, firstName);
                intent.putExtra(EXTRA_LASTNAME, lastName);
                intent.putExtra(EXTRA_EMAIL, email);
                intent.putExtra(EXTRA_PHONE, phone);
                startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
                return true;
            case R.id.delete_user:
                alertDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void alertDialog() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Delete all?");
            dialog.setMessage("Are you sure You want to delete all?");
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   // viewModel.delete();
                }
            });
            dialog.create().show();
        }



}