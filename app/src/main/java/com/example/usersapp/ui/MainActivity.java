package com.example.usersapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usersapp.R;
import com.example.usersapp.data.User;
import com.example.usersapp.data.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int ADD_USER_REQUEST_CODE = 1;
    private static final int EDIT_USER_REQUEST_CODE = 2;
    //database
    UserViewModel viewModel;

    //widget
    RecyclerView recyclerView;
    FloatingActionButton fab;
    UserAdapter userAdapter;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adapter & widgets
        userAdapter = new UserAdapter();
        recyclerView = findViewById(R.id.my_recyclerview);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //data
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //update ui
                userAdapter.setUsers(users);

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(userAdapter.getUserAt(viewHolder.getAdapterPosition()));
                Snackbar snackbar = Snackbar.make(viewHolder.itemView, "User successfully deleted", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(R.color.yellow))

                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);
        userAdapter.SetOnClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(MainActivity.this, AddEditUserActivity.class)
                        .putExtra(AddEditUserActivity.EXTRA_ID, user.getId())
                        .putExtra(AddEditUserActivity.EXTRA_FIRSTNAME, user.getFirstName())
                        .putExtra(AddEditUserActivity.EXTRA_LASTNAME, user.getLastName())
                        .putExtra(AddEditUserActivity.EXTRA_EMAIL, user.getEmail())
                        .putExtra(AddEditUserActivity.EXTRA_PHONE, user.getPhone());
                startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
            }
        });


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditUserActivity.class);
            startActivityForResult(intent, ADD_USER_REQUEST_CODE);
        });


        if (userAdapter.users.isEmpty()) {
/*
            Working with spinner
           \

            spinner = findViewById(R.id.spinner);

            ArrayList<String> countryList = new ArrayList<>();
            countryList.add("Africa");
            countryList.add("Europe");
            countryList.add("South America");
            countryList.add("North America");
            countryList.add("Asia");
            countryList.add("Australia");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countryList);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this, "You have choosen to be in " + countryList.get(i), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });*/

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(AddEditUserActivity.EXTRA_FIRSTNAME);
            String lastName = data.getStringExtra(AddEditUserActivity.EXTRA_LASTNAME);
            String email = data.getStringExtra(AddEditUserActivity.EXTRA_EMAIL);
            String phone = data.getStringExtra(AddEditUserActivity.EXTRA_PHONE);

            User user = new User(firstName, lastName, email, phone);
            viewModel.insert(user);
        }
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_items, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_fname:
                viewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
                viewModel.getSortByFName().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        userAdapter = new UserAdapter();
                        userAdapter.setUsers(users);

                    }
                });
                return true;
            case R.id.sort_lname:
                viewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
                viewModel.getSortByLName().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        userAdapter = new UserAdapter();
                        userAdapter.setUsers(users);

                    }
                });
                return true;
            case R.id.delete_all:
                if (!userAdapter.users.isEmpty()) {
                    alertDialog();
                } else {
                    Toast.makeText(this, "No User Record found!", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.app_bar_search:
                Toast.makeText(this, "Meun 1 is clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sync:
                Toast.makeText(this, "Meun Sync is clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.notify:
                //     Intent intent = new Intent(this, Notification.class);
                //   startActivity(intent);
                return true;
            case R.id.custom_drive:
                //new Asyn().execute();
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
                viewModel.deleteAll();
            }
        });
        dialog.create().show();
    }

}