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

@Database(entities = {Course.class,Category.class},version = 1,exportSchema = true)
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


        }
    };

}
