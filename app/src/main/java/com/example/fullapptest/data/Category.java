package com.example.fullapptest.data;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.fullapptest.BR;


@Entity (tableName = "Category")
public class Category extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    int categoryId;
    String categoryName;
    String description;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(com.example.fullapptest.BR.categoryName);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(com.example.fullapptest.BR.description);
    }

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return categoryName;
    }
}
