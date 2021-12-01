package com.multiTenantDbImpl2.service.impl;

import com.multiTenantDbImpl2.repository.model.Book;
import com.multiTenantDbImpl2.repository.BookRepository;
import com.multiTenantDbImpl2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book input) {
        bookRepository.save(input);
    }

    @Override
    public void updateBook(Book input) {
        bookRepository.save(input);
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.getById(id);
    }

    @Override
    public void removeBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void removeBook(Book book){
        bookRepository.delete(book);
    }

    @Override
    public void setBookDao(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
