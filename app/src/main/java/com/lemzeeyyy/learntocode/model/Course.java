package com.lemzeeyyy.learntocode.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;


@Entity(tableName = "course_table",foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id",childColumns = "category_id",onDelete = CASCADE))

public class Course extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int course_id;

    @ColumnInfo(name = "category_id")
    private int category_id;

    @ColumnInfo
    private String course_name;

    @ColumnInfo(name = "unit_price")
    private String unitPrice;

    public Course(int course_id, int category_id, String course_name, String unitPrice) {
        this.course_id = course_id;
        this.category_id = category_id;
        this.course_name = course_name;
        this.unitPrice = unitPrice;
    }

    @Ignore
    public Course() {
    }

    @Bindable
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
        notifyPropertyChanged(BR.course_id);


    }

    @Bindable
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
        notifyPropertyChanged(BR.category_id);


    }

    @Bindable
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
        notifyPropertyChanged(BR.course_name);


    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }


    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return course_id == course.course_id &&
                category_id == course.category_id
                && course_name.equals(course.course_name)
                && unitPrice.equals(course.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id, category_id, course_name, unitPrice);
    }
}
