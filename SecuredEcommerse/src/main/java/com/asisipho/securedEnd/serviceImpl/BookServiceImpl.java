package com.asisipho.securedEnd.serviceImpl;

import com.asisipho.securedEnd.entities.Book;
import com.asisipho.securedEnd.model.request.BookRequest;
import com.asisipho.securedEnd.repository.BookRepository;
import com.asisipho.securedEnd.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl  implements BookService {

    private final BookRepository repository;
    @Override
    public void save(BookRequest request) {
        var book = Book.builder()
                .id(request.getId())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .build();
        repository.save(book);
    }
    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }
}
