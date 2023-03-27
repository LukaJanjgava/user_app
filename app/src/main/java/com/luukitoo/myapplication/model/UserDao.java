package com.luukitoo.myapplication.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addUser(User user);

    @Query("SELECT * FROM users_table LIMIT 1")
    Single<User> getUser();

}
