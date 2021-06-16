package com.nurayim.myapplication5536.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nurayim.myapplication5536.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    //@Query запрос
    //* Звездочка  озночает
    // все или любое из таблицы task
    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAll();

    @Insert
    void insert(Task task);

    @Query("SELECT * FROM Task ORDER BY title  ASC")
    LiveData<List<Task>> sortByAsc();

    @Delete
    void remove(Task task);
}
