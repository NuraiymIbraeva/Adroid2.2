package com.nurayim.myapplication5536.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nurayim.myapplication5536.models.Task;

@Database(entities = {Task.class},version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
