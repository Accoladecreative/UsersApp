package com.example.usersapp.ui;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usersapp.R;
import com.example.usersapp.data.User;
import com.example.usersapp.data.UserViewModel;
import com.example.usersapp.ui.adapter.UserAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int ADD_USER_REQUEST_CODE = 1;
    //private static final int EDIT_USER_REQUEST_CODE = 2;
    //database
    UserViewModel viewModel;

    //widget
    RecyclerView recyclerView;
    FloatingActionButton fab;
    UserAdapter userAdapter;


    //Nav Drawer
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    //Buttom Nav
    BottomNavigationView bottomNavigationView;


    RelativeLayout relativeLayout;


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
        relativeLayout = findViewById(R.id.empty_activity);



        //data
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //update ui
                userAdapter.setUsers(users);
                if (userAdapter.getItemCount() == 0) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.INVISIBLE);
                }

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //todo: add to user achieve
                viewModel.delete(userAdapter.getUserAt(viewHolder.getAdapterPosition()));
                deleteOneUserNotification();
                Snackbar snackbar = Snackbar.make(viewHolder.itemView, "User successfully deleted", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(R.color.Orange))

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
                Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class)
                        .putExtra(AddEditUserActivity.EXTRA_ID, user.getId())
                        .putExtra(AddEditUserActivity.EXTRA_FIRSTNAME, user.getFirstName())
                        .putExtra(AddEditUserActivity.EXTRA_LASTNAME, user.getLastName())
                        .putExtra(AddEditUserActivity.EXTRA_EMAIL, user.getEmail())
                        .putExtra(AddEditUserActivity.EXTRA_DOB,user.getDateOfBirth())
                        .putExtra(AddEditUserActivity.EXTRA_DATEADDED,user.getDateAdded())
                        .putExtra(AddEditUserActivity.EXTRA_PHONE, user.getPhone());
                startActivity(intent);
            }
        });


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditUserActivity.class);
            startActivityForResult(intent, ADD_USER_REQUEST_CODE);
        });



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


        initNavBar();
        initBottomNabBAr();

    }

    private void initBottomNabBAr() {
        bottomNavigationView = findViewById(R.id.btm_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "You're at Home, Welcome", Toast.LENGTH_SHORT).show();
                        bottomNavigationView.setSelectedItemId(R.id.home);
                        return true;
                    case R.id.cart:
                       startActivity(new Intent(MainActivity.this, Explore.class));
                       break;
                }
                return false;}
        });

    }

    private void initNavBar() {
        //Navigation bar
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer,R.string.closedrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "You're at Home, Welcome", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                        break;
                    case R.id.categories:
                        startActivity(new Intent(MainActivity.this,Dashboard.class));
                        break;
                }
                return false;}
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra(AddEditUserActivity.EXTRA_FIRSTNAME);
            String lastName = data.getStringExtra(AddEditUserActivity.EXTRA_LASTNAME);
            String email = data.getStringExtra(AddEditUserActivity.EXTRA_EMAIL);
            String phone = data.getStringExtra(AddEditUserActivity.EXTRA_PHONE);
            String dob = data.getStringExtra(AddEditUserActivity.EXTRA_DOB);
            String dateAdded = data.getStringExtra(AddEditUserActivity.EXTRA_DATEADDED);

            User user = new User(firstName, lastName, email, dob, phone,dateAdded);
            viewModel.insert(user);

            Toast.makeText(this, "User " + firstName + " " + lastName + " created successfully ", Toast.LENGTH_SHORT).show();
//            NewBookNotify
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            Resources res = this.getResources();
            final Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ic_user);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "newUser")
                    .setContentTitle("One User Added")
                    .setContentText("User " + firstName + " " + lastName + " created successfully ")
                    .setSmallIcon(R.drawable.profile_picture)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(bitmap)
                    .setGroup("addUser")
                    .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle("User " +firstName +" added")
                        .bigText(firstName +" "+ lastName+" added successfully with Email: "+email+" and Date of Birth: "+dob+"\n\n Successfully added on"+ dateAdded)
                        )
                    .addAction(R.drawable.profile_picture,"View",pendingIntent)
            ;

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(1, builder.build());
        }


    }

    public void deleteOneUserNotification() {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Resources res = this.getResources();
        final Bitmap bitmap = BitmapFactory.decodeResource(res,R.drawable.my_logo);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "DeleteOneUser");
        builder.setSmallIcon(R.drawable.profile_picture)
                .setContentTitle("One User deleted")
                .setContentText("User Deleted Successfully")
                .setLargeIcon(bitmap)
                .setAutoCancel(true)
                .setGroup("deleteUser")
                .setContentIntent(pendingIntent)
        .addAction(R.drawable.ic_user,"Undo",pendingIntent)

        ;



        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(2,builder.build());
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

            case R.id.settings:
                 Intent intent = new Intent(this, SettingsActivity.class);
                 startActivity(intent);
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