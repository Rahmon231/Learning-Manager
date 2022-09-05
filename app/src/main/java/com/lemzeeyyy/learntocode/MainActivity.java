package com.lemzeeyyy.learntocode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lemzeeyyy.learntocode.adapter.CourseAdapter;
import com.lemzeeyyy.learntocode.databinding.ActivityMainBinding;
import com.lemzeeyyy.learntocode.model.Category;
import com.lemzeeyyy.learntocode.model.Course;
import com.lemzeeyyy.learntocode.view_model.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final int ADD_COURSE_REQUEST_CODE = 1;
    private static final int EDIT_COURSE_REQUEST_CODE = 2;
    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private Category selectedCategory;
    private ArrayList<Course> courseArrayList;
    public int selectedCourseId;

    //RecyclerView
    private RecyclerView courseRecyclerView;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);


        mainActivityViewModel.getAllCategories().observe(this, categories -> {
            categoriesList = (ArrayList<Category>) categories;
            for (Category c :
                    categories) {
                Log.i("TAG", c.getCategory_name());
            }
            showOnSpinner();
        });
        mainActivityViewModel.getCoursesOfSelectedCategory(1).observe(this, courses -> {
            for (Course c :
                    courses) {
                Log.v("TAG", c.getCourse_name());
            }
        });
    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter =  new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                categoriesList
                );
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    public void loadCourseArrayList(int cat_id){
        mainActivityViewModel.getCoursesOfSelectedCategory(cat_id).observe(this, courses -> {
            courseArrayList = (ArrayList<Course>) courses;
            loadRecyclerView();
        });
    }

    private void loadRecyclerView() {
        courseRecyclerView = activityMainBinding.secondaryLayout.recyclerView;
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setHasFixedSize(true);
        courseAdapter = new CourseAdapter();
        courseRecyclerView.setAdapter(courseAdapter);
        courseAdapter.setCourseArrayList(courseArrayList);

        courseAdapter.setListener(new CourseAdapter.onItemClickedListener() {
            @Override
            public void onItemClicked(Course course) {

                //EDit Course
                selectedCourseId = course.getCourse_id();
                Intent intent = new Intent(MainActivity.this,AddEditActivity.class);
                intent.putExtra(AddEditActivity.COURSE_ID, selectedCourseId);
            }
        });
    }

    public class MainActivityClickHandlers {

        public void onFabClicked(View view){

            //Add New Course
            Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
            startActivityForResult(intent,ADD_COURSE_REQUEST_CODE);
            //Toast.makeText(MainActivity.this, "Add New Course Clicked", Toast.LENGTH_SHORT).show();
        }

        public void onItemSelected(AdapterView<?> parent, View view,int pos, long id){
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            loadCourseArrayList(selectedCategory.getId());

            String message = selectedCategory.getCategory_name();
            Toast.makeText(MainActivity.this, ""+message, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
    }
}