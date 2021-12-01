package com.multiTenantDbImpl2.controller;

import com.multiTenantDbImpl2.repository.model.Book;
import com.multiTenantDbImpl2.repository.BookRepository;
import com.multiTenantDbImpl2.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    public BookController(){}

    @GetMapping("/{env}")
    public String connectToDb(@PathVariable String env) { //  See Headers
        try {
            String tenantStr = "tenant_book_" + env;
            StringBuilder responseStr = new StringBuilder();

            List<Book> bookList = bookService.listBooks();
            for(Book b :bookList){
                responseStr.append(b.bookId + " |" + b.bookName + " |" +b.author + System.lineSeparator());
            }
            System.out.println(responseStr.toString());

            return responseStr.toString();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Failure";
        }
    }
}
