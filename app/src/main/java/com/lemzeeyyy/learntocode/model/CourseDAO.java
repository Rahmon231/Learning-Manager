package com.lemzeeyyy.learntocode.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course_table")
    LiveData<List<Course>> getAllCourse();

    @Query("SELECT * FROM course_table WHERE category_id == :category_id")
    LiveData<List<Course>> getCourse(int category_id);

}
