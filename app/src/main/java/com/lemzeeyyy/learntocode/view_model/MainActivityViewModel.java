package com.lemzeeyyy.learntocode.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lemzeeyyy.learntocode.model.Category;
import com.lemzeeyyy.learntocode.model.Course;
import com.lemzeeyyy.learntocode.model.Repository;

import java.util.List;


public class MainActivityViewModel extends AndroidViewModel {

   private Repository repository;
   private LiveData<List<Course>> coursesOfSelectedCategory;
   private LiveData<List<Category>> allCategories;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
    public LiveData<List<Category>> getAllCategories(){
        allCategories = repository.getCategories();
        return allCategories;
    }

    public LiveData<List<Course>> getCoursesOfSelectedCategory(int category_id) {
        coursesOfSelectedCategory = repository.getCourses(category_id);
        return coursesOfSelectedCategory;
    }
    public void addNewCourse(Course course){
        repository.insertCourse(course);
    }
    public void updateCourse(Course course){
        repository.updateCourse(course);
    }
    public void deleteCourse(Course course){
        repository.deleteCourse(course);
    }
    public void addNewCategory(Category category){
        repository.insertCategory(category);
    }
    public void updateCategory(Category category){
        repository.updateCategory(category);
    }
    public void deleteCategory(Category category){
        repository.deleteCategory(category);
    }
}
