package com.example.fullapptest.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fullapptest.R;
import com.example.fullapptest.data.Book;
import com.example.fullapptest.data.Category;
import com.example.fullapptest.databinding.ActivityMainBinding;
import com.example.fullapptest.viewModel.BookViewModel;
import com.example.fullapptest.viewModel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.fullapptest.view.AddNewBookActivity.BOOK_ID;
import static com.example.fullapptest.view.AddNewBookActivity.CATEGORY_ID;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_BOOK_REQUEST_CODE = 613;
    private BookViewModel bookViewModel;
    private CategoryViewModel categoryViewModel;
    BookAdapter bookAdapter;

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handler;
    private Category selectedCategory;
    private ArrayList<Category> categoryList;
    private ArrayList<Book> booksList;
    private RecyclerView booksRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(handler);

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList = (ArrayList<Category>) categories;
                showOnSpinner();

            }
        });


    }

    private void loadBooksArrayList(int categoryId){
        bookViewModel.getBooks(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                booksList = (ArrayList<Book>) books;
                loadRecyclerView();

            }
        });

    }

    private void loadRecyclerView(){
        bookAdapter = new BookAdapter();
        bookAdapter.setBooks(booksList);
        bookAdapter.setListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent = new Intent(MainActivity.this, AddNewBookActivity.class);
                intent.putExtra(BOOK_ID, book.getBookId());
                startActivityForResult(intent, NEW_BOOK_REQUEST_CODE);
            }
        });

        booksRecyclerview = activityMainBinding.layoutContentMain.bookList;
        booksRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerview.setAdapter(bookAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Book book = booksList.get(viewHolder.getAdapterPosition());
                bookViewModel.deleteBook(book);

            }
        }).attachToRecyclerView(booksRecyclerview);

    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this, R.layout.spinner_item, categoryList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandler{

        Context context;

        public MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onFABClicked(View view){
            Intent intent = new Intent(MainActivity.this, AddNewBookActivity.class);
            intent.putExtra(CATEGORY_ID, selectedCategory.getCategoryId());
            startActivityForResult(intent, NEW_BOOK_REQUEST_CODE);

        }

        public void onselectItem(AdapterView<?> parent, View view, int pos, long id){
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            loadBooksArrayList(selectedCategory.getCategoryId());
        }

    }

}
