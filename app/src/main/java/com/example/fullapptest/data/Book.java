package com.example.fullapptest.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.fullapptest.BR;

import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "Book", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "categoryId",
        childColumns = "category", onDelete = CASCADE))
public class Book extends BaseObservable {

    @PrimaryKey (autoGenerate = true)
    int bookId;
    String bookName;
    String unitPrice;
    int category;

    @Ignore
    public Book() {
    }

    public Book(int bookId, String bookName, String unitPrice, int category) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    @Ignore
    public Book(String bookName, String unitPrice, int category) {
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    @Bindable
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(com.example.fullapptest.BR.bookName);
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }


    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(com.example.fullapptest.BR.unitPrice);

    }

    @Bindable
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
        notifyPropertyChanged(com.example.fullapptest.BR.category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                category == book.category &&
                bookName.equals(book.bookName) &&
                unitPrice.equals(book.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, unitPrice, category);
    }
}
