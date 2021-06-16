package com.nurayim.myapplication5536;

import android.app.Application;

import androidx.room.Room;

import com.nurayim.myapplication5536.room.AppDatabase;

public class App extends Application {


    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room
                .databaseBuilder(this, AppDatabase.class, "Database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
