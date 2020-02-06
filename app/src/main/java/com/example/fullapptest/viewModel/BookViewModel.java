package com.example.fullapptest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fullapptest.data.Book;
import com.example.fullapptest.data.BookRepository;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    private MutableLiveData<List<Book>> allBooks;


    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
    }

    public void insertBook(Book book){
        bookRepository.insertBook(book);
    }

    public void updateBook(Book book){
        bookRepository.updateBook(book);
    }

    public void deleteBook(Book book){
        bookRepository.deleteBook(book);
    }

    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookRepository.getBooks(categoryId);
    }

    public Book getBook(int bookId) {
        return bookRepository.getBook(bookId);
    }
}
