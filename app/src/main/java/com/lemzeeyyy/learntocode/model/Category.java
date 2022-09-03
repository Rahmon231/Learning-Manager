package com.lemzeeyyy.learntocode.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "catagory_table")
public class Category extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private int category_id;
    @ColumnInfo
    private String category_name;
    @ColumnInfo
    private String category_description;

    @Ignore
    public Category() {
    }

    public Category(int category_id, String category_name, String category_description) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_description = category_description;
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
}
