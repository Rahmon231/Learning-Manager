package com.lemzeeyyy.learntocode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lemzeeyyy.learntocode.databinding.ActivityAddEditBinding;
import com.lemzeeyyy.learntocode.model.Course;

import java.util.ArrayList;

public class AddEditActivity extends AppCompatActivity {
    private ArrayList<Course> courses;
    private Course course;
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_NAME = "course_name";
    public static final String COURSE_PRICE = "unitPrice";
    private ActivityAddEditBinding addEditBinding;
    private AddEditClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        course = new Course();
        addEditBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_edit);
        addEditBinding.setCourse(course);
        clickHandler = new AddEditClickHandler(this);
        addEditBinding.setClickHander(clickHandler);
        Intent intent = getIntent();
        if(intent.hasExtra(COURSE_ID)){
            //When Course is Clicked (Recyclerview item)
            setTitle("Edit Course");
            course.setCourse_name(intent.getStringExtra(COURSE_NAME));
            course.setUnitPrice(intent.getStringExtra(COURSE_PRICE));
        }else {

            //When FAB is clicked
            setTitle("Add New Course");
        }
    }
    public class AddEditClickHandler{
        Context context;

        public AddEditClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitClick(View view){
            if(course.getCourse_name() == null){
                Toast.makeText(context, "Name field is empty", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent();
                intent.putExtra(COURSE_NAME,course.getCourse_name());
                intent.putExtra(COURSE_PRICE,course.getUnitPrice());
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
}
