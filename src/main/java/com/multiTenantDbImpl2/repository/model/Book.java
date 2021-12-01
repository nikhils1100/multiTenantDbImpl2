package com.multiTenantDbImpl2.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Book")
public class Book implements Serializable {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookId;
    @Column(name="book_name")
    public String bookName;
    @Column(name="genre")
    public String genre;
    @Column(name="author")
    public String author;
}
