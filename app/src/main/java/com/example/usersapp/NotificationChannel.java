package com.example.usersapp;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;

import com.example.usersapp.data.User;

public class NotificationChannel extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
        createDeleteNotificationChannel();
        synchronizeNotificationChannel();
    }

    private void synchronizeNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            android.app.NotificationChannel downloadData = new android.app.NotificationChannel("synchronizeChannel", "File Download Request", NotificationManager.IMPORTANCE_HIGH);
            downloadData.setDescription("File Download Notification");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(downloadData);
        }

    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            android.app.NotificationChannel bookAdded = new android.app.NotificationChannel("newUser","New User Notification", NotificationManager.IMPORTANCE_DEFAULT);
            bookAdded.setDescription("New User Notification");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(bookAdded);

        }
    }

    private void createDeleteNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            android.app.NotificationChannel DeleteOneBook = new android.app.NotificationChannel("DeleteOneUser", "Delete One User Notification", NotificationManager.IMPORTANCE_DEFAULT);
            DeleteOneBook.setDescription("Delete One user Notification");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(DeleteOneBook);
        }

    }


    interface kolade{

    }
    interface tope{
    }
    interface goodluck{}

    class sample extends User implements kolade,tope,goodluck{


        public sample(String firstName, String lastName, String email, String dateOfBirth, String phone, String dateAdded) {
            super(firstName, lastName, email, dateOfBirth, phone, dateAdded);
        }
        sample sample1 = new sample("","","","","","");
    }
}
