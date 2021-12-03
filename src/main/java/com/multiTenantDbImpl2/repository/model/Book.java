package com.multiTenantDbImpl2.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer bookId;
    @Column(name="book_name")
    public String bookName;
    @Column(name="genre")
    public String genre;
    @Column(name="author")
    public String author;
}
