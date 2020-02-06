package com.example.fullapptest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);

    @Query("SELECT * FROM category")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM category WHERE categoryId LIKE :id")
    Category getCategory(int id);
}
