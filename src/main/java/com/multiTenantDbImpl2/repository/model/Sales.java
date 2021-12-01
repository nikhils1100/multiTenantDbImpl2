package com.multiTenantDbImpl2.repository.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Sales")
public class Sales implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="sales_id")
    public int salesId;
    @Column(name="user_id")
    public int userId;
    @Column(name="book_id")
    public int bookId;
}
