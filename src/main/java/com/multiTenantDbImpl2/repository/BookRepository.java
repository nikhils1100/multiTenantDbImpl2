package com.multiTenantDbImpl2.repository;

import com.multiTenantDbImpl2.repository.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
