package com.lemzeeyyy.learntocode.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lemzeeyyy.learntocode.model.Category;
import com.lemzeeyyy.learntocode.model.CategoryDAO;
import com.lemzeeyyy.learntocode.model.Course;
import com.lemzeeyyy.learntocode.model.CourseDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Course.class,Category.class},version = 1,exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CourseDAO getCourseDAO();
    public abstract CategoryDAO getCategoryDAO();

    //Singleton Pattern
    private static CourseDatabase INSTANCE;
    public static synchronized CourseDatabase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDatabase.class,
                    "courses_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    //Callback
    private static final RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //Insert data when db is created...
            initializeData();


        }
    };

    private static void initializeData() {
        CourseDAO courseDAO = INSTANCE.getCourseDAO();
        CategoryDAO categoryDAO = INSTANCE.getCategoryDAO();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Categories
                Category category1 = new Category();
                category1.setCategory_name("Front End");
                category1.setCategory_description("Web Development Interface");

                Category category2 = new Category();
                category2.setCategory_name("Back End");
                category2.setCategory_description("Web Development Database");

                categoryDAO.insertCategory(category1);
                categoryDAO.insertCategory(category2);

                // Courses
                Course course1 = new Course();
                course1.setCourse_name("HTML");
                course1.setUnitPrice("100$");
                course1.setCourse_id(1);

                Course course2 = new Course();
                course2.setCourse_name("CSS");
                course2.setUnitPrice("200$");
                course2.setCourse_id(2);

                Course course3 = new Course();
                course3.setCourse_name("PHP");
                course3.setUnitPrice("400$");
                course3.setCourse_id(3);

                courseDAO.insertCourse(course1);
                courseDAO.insertCourse(course2);
                courseDAO.insertCourse(course3);
            }
        });
    }

}
