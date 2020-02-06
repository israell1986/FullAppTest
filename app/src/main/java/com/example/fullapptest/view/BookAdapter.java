package com.example.fullapptest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fullapptest.R;
import com.example.fullapptest.data.Book;
import com.example.fullapptest.databinding.BookItemBinding;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private ArrayList<Book> books;
    private OnItemClickListener listener;


    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemBinding bookItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.book_item, parent, false);
        return new BookHolder(bookItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book current = books.get(position);
        holder.bookItemBinding.setBook(current);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookHolder extends RecyclerView.ViewHolder {

        private BookItemBinding bookItemBinding;

        public BookHolder(@NonNull BookItemBinding bookItemBinding) {
            super(bookItemBinding.getRoot());
            this.bookItemBinding = bookItemBinding;
            bookItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null || position != RecyclerView.NO_POSITION ) {
                        listener.onItemClick(books.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setBooks(ArrayList<Book> newBooks) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BooksDiffCallback(books, newBooks),false);
        books = newBooks;
        result.dispatchUpdatesTo(BookAdapter.this);

    }
}
