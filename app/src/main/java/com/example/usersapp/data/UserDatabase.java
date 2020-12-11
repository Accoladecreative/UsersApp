package com.example.usersapp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;
 
    public abstract UserDao userDao();

    public static synchronized  UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, UserDatabase.class, "user_table")
                    .addCallback(callback)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    private static final RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsync(instance).execute();
        }
    };






    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        public UserDao userDao;

        private PopulateDBAsync(UserDatabase userDatabase) {
            userDao = userDatabase.userDao();
                                                                                        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            userDao.insertUser(new User("Femi", "Oluwadare", "femi@gmail.com", "08132747502"));
            userDao.insertUser(new User("Tope", "Oluwadare", "tope@gmail.com", "07036927791"));
            userDao.insertUser(new User("Kolade", "Oluwadare", "kolade@gmail.com", "08104757514"));
            userDao.insertUser(new User("Titi", "Oluwadare", "titi@gmail.com", "08164029533"));
            userDao.insertUser(new User("Taiwo", "Akanle", "taiwo@gmail.com", "08198272627"));
            userDao.insertUser(new User("Kenny", "Akanle", "kenny@gmail.com", "897765467687"));
            userDao.insertUser(new User("Timi", "Ibidun", "ibidun@gmail.com", "098765698769"));
            userDao.insertUser(new User("Tomiwa", "Ibidun", "tommy@gmail.com", "98767876578"));
            return null;
        }
    }
}


