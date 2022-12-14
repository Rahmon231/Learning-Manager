package com.lemzeeyyy.learntocode.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CategoryDAO {

    @Insert
    void insertCategory(Category category);

    @Update
    void  updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Query("SELECT * FROM category_table")
    LiveData<List<Category>> getAllCategories();
}
