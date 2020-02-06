package com.example.fullapptest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Query("SELECT * FROM book")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM book WHERE category Like :categoryId")
    LiveData<List<Book>> getBooks(int categoryId);

    @Query("SELECT * FROM book WHERE bookId Like :bookId")
    Book getBook(int bookId);

    @Delete
    void delete(Book book);
}
