package com.example.fullapptest.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;

    private LiveData<List<Category>> allCategories;

    public CategoryRepository(Application application) {
        MyDataBase dataBase = MyDataBase.getDatabase(application);
        categoryDao = dataBase.getCategoryDao();
        allCategories = categoryDao.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public void insertCategory(Category category){
        new InsertCategoryAsyncTask(categoryDao).execute(category);
    }

    public void deleteCategory(Category category){
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }

    public void updateCategory(Category category){
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }


    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public InsertCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public DeleteCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.delete(categories[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        public UpdateCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }





}










