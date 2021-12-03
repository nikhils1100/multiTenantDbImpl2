package com.multiTenantDbImpl2.controller;

import com.multiTenantDbImpl2.config.DbContextHolder;
import com.multiTenantDbImpl2.repository.model.Book;
import com.multiTenantDbImpl2.repository.BookRepository;
import com.multiTenantDbImpl2.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ApplicationContext applicationContext;

    public BookController(){}

    @GetMapping("/getBooks")
    public String connectToDb(@RequestHeader(name = "X-TENANT-ID") String env) { //  See Headers
        try {
            String tenantStr = "persistence-tenant_book_" + env;
            StringBuilder responseStr = new StringBuilder();
            DbContextHolder.setCurrentDb(tenantStr);

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
