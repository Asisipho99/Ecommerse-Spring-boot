package com.asisipho.securedEnd.service;

import com.asisipho.securedEnd.entities.Book;
import com.asisipho.securedEnd.model.request.BookRequest;

import java.util.List;

public interface BookService {
    public void save(BookRequest request);

    public List<Book> findAll();
}
