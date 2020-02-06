package com.example.fullapptest.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.fullapptest.R;
import com.example.fullapptest.data.Book;
import com.example.fullapptest.databinding.ActivityAddBookBinding;
import com.example.fullapptest.viewModel.BookViewModel;

public class AddNewBookActivity extends AppCompatActivity {

    public static final String BOOK_ID = "bookId";
    public static final String CATEGORY_ID = "categoryId";
    private ActivityAddBookBinding binding;
    private Book book;
    private BookViewModel bookViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_book);

        book = new Book();
        binding.setClickHandler(new AddNewBookActivityClickHanlder(this));
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        if (getIntent().hasExtra(BOOK_ID)){
            setTitle("Edit book");
            book = bookViewModel.getBook(getIntent().getIntExtra(BOOK_ID, -1));
        } else {
            book.setCategory(getIntent().getExtras().getInt(CATEGORY_ID));
            setTitle("Add new book");
        }
        binding.setBook(book);

    }

    public class AddNewBookActivityClickHanlder {
        Context context;

        public AddNewBookActivityClickHanlder(Context context) {
            this.context = context;
        }

        public void onSubmitClicked(View view) {

            if (book.getBookName() == null){
                Toast.makeText(context, "have to choose name", Toast.LENGTH_SHORT).show();
            } else {
                if (getIntent().hasExtra(BOOK_ID)){
                    bookViewModel.updateBook(book);
                } else {
                    bookViewModel.insertBook(book);
                }
                finish();
            }


        }
    }

}
