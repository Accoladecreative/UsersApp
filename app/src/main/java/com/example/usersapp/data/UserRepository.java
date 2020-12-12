package com.example.usersapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<User>> sortByFName;
    private LiveData<List<User>> sortByLName;

    public UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDao = database.userDao();
        allUsers = userDao.getAllUsers();
        sortByFName = userDao.sortByFirstName();
        sortByLName = userDao.sortByLastName();
    }

    public void insert(User user){
        new InsertAsync(userDao).execute(user);

    }

    public void update(User user){
        new UpdateAsync(userDao).execute(user);

    }

    public void delete(User user){
        new DeleteAsync(userDao).execute(user);
    }

    public void deleteAllUsers(){
        new DeleteAllAsync(userDao).execute();
    }

    public void deleteThisUser(){

    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;

    }


    public LiveData<List<User>> getSortByFName() {
        return sortByFName;
    }

    public LiveData<List<User>> getSortByLName() {
        return sortByLName;
    }

    private static class InsertAsync extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        private InsertAsync(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

    private static class UpdateAsync extends AsyncTask<User,Void,Void>{
        private static UserDao userDao;

        public UpdateAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }

    private static class DeleteAsync extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        DeleteAsync(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users[0]);
            return null;
        }
    }

    private static class DeleteAllAsync extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;
        DeleteAllAsync(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }

    }

    private static class GetAllUsers extends AsyncTask<Void,Void,LiveData<List<User>>> {
        private UserDao userDao;

        GetAllUsers(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected LiveData<List<User>> doInBackground(Void... voids) {
            return userDao.getAllUsers();
        }
    }
}
