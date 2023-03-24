package com.luukitoo.myapplication.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String email;
    public int age;
    public String gender;

    public User(String username, String email, int age, String gender) {
        this.id = 1;
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}
