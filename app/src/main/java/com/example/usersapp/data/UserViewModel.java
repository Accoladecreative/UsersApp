package com.example.usersapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository ;
    private LiveData<List<User>> allUsers;
    private LiveData<List<User>> sortByFName;
    private LiveData<List<User>> sortByLName;
    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
        sortByFName =repository.getSortByFName();
        sortByLName = repository.getSortByLName();
    }

    public void insert(User user){
        repository.insert(user);
    }
    public void update(User user){
        repository.update(user);
    }
    public void delete(User user){
        repository.delete(user);
    }
    public void deleteAll(){
        repository.deleteAllUsers();
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<List<User>> getSortByLName() {
        return sortByLName;
    }

    public LiveData<List<User>> getSortByFName() {
        return sortByFName;
    }
}
