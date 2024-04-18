package com.asisipho.securedEnd.repository;

import com.asisipho.securedEnd.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
