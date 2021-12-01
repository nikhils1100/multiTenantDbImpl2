package com.multiTenantDbImpl2.service;

import com.multiTenantDbImpl2.repository.model.Book;
import com.multiTenantDbImpl2.repository.BookRepository;

import java.util.List;

public interface IBookService {
    void addBook(Book input);

    void updateBook(Book input);

    List<Book> listBooks();

    Book getBookById(int id);

    void removeBookById(int id);

    void removeBook(Book book);

    void setBookDao(BookRepository bookDao);
}
