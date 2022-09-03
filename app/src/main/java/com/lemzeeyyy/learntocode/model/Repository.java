package com.lemzeeyyy.learntocode.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import com.lemzeeyyy.learntocode.database.CourseDatabase;
import com.lemzeeyyy.learntocode.model.Category;
import com.lemzeeyyy.learntocode.model.CategoryDAO;
import com.lemzeeyyy.learntocode.model.Course;
import com.lemzeeyyy.learntocode.model.CourseDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private CourseDAO courseDAO;
    private CategoryDAO categoryDAO;
    LiveData<List<Course>> courses;
    LiveData<List<Category>> categories;

    public Repository(Application application) {
        CourseDatabase database = CourseDatabase.getInstance(application);
        courseDAO = database.getCourseDAO();
        categoryDAO = database.getCategoryDAO();
//        courses = courseDAO.getAllCourse();
//        categories = categoryDAO.getAllCategories();
    }

    public LiveData<List<Course>> getCourses(int category_id) {
        return courseDAO.getCourse(category_id);
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public void insertCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.insertCategory(category);
            }
        });
    }
    public void insertCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.insertCourse(course);
            }
        });
    }
    public void deleteCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.deleteCategory(category);
            }
        });
    }
    public void deleteCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.deleteCourse(course);
            }
        });
    }
    public void updateCategory(Category category){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.updateCategory(category);
            }
        });
    }
    public void updateCourse(Course course){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.updateCourse(course);
            }
        });
    }
}
