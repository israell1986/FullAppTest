package com.example.fullapptest.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BookRepository {

    private BookDao bookDao;

    private LiveData<List<Book>> allBooks;

    public BookRepository(Application application) {
        MyDataBase dataBase = MyDataBase.getDatabase(application);
        bookDao = dataBase.getBookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insertBook(Book book) {
        new InsertBookAsyncTask(bookDao).execute(book);

    }

    public void updateBook(Book book) {
        new UpdateBookAsyncTask(bookDao).execute(book);
    }

    public void deleteBook(Book book) {
        new DeleteBookAsyncTask(bookDao).execute(book);

    }

    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookDao.getBooks(categoryId);
    }

    public Book getBook(int bookId) {
        Book book = null;
        try {
            book = new GetBookAsyncTask(bookDao).execute(bookId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return book;
    }


    private static class GetBookAsyncTask extends AsyncTask<Integer, Void, Book> {
        private BookDao bookDao;

        public GetBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Book doInBackground(Integer... integers) {
            return bookDao.getBook(integers[0]);
        }
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public InsertBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insert(books[0]);
            return null;
        }
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public DeleteBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.delete(books[0]);
            return null;
        }
    }

    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        public UpdateBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.update(books[0]);
            return null;
        }
    }
}










