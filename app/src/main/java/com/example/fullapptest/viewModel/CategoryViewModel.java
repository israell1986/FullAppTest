package com.example.fullapptest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fullapptest.data.Category;
import com.example.fullapptest.data.CategoryDao;
import com.example.fullapptest.data.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    CategoryRepository categoryRepository;
    LiveData<List<Category>> allCategories;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void insertCategory(Category category){
        categoryRepository.insertCategory(category);
    }

}
