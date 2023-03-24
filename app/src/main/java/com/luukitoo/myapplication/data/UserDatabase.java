package com.luukitoo.myapplication.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    public static UserDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, "users_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

}
