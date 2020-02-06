package com.example.fullapptest.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class, Category.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public static MyDataBase database;

    public abstract CategoryDao getCategoryDao();

    public abstract BookDao getBookDao();

    public static synchronized MyDataBase getDatabase(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), MyDataBase.class, "my_database").fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return database;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(database).execute();
        }
    };

    private static class populateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private CategoryDao categoryDao;
        private BookDao bookDao;

        public populateDbAsyncTask(MyDataBase db) {
            this.categoryDao = db.getCategoryDao();
            this.bookDao = db.getBookDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.insert(new Category("romance", "romance books"));
            categoryDao.insert(new Category("since fictions", "since fictions books"));
            categoryDao.insert(new Category("horror", "horror books"));
            bookDao.insert(new Book("romance book","50", 1));
            bookDao.insert(new Book("since fictions book","40", 2));
            bookDao.insert(new Book("horror book", "30", 3));
            return null;
        }
    }

}
