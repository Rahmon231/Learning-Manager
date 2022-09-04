package com.lemzeeyyy.learntocode.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity(tableName = "category_table")

public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String category_name;

    @ColumnInfo
    private String category_description;

    @Ignore
    public Category() {
    }

    public Category(int id, String category_name, String category_description) {
        this.id = id;
        this.category_name = category_name;
        this.category_description = category_description;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);

    }

    @Bindable
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
        notifyPropertyChanged(BR.category_name);


    }

    @Bindable
    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
        notifyPropertyChanged(BR.category_description);

    }

    @Override
    public String toString() {
        return this.category_name;
    }
}
