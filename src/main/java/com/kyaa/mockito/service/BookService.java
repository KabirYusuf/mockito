package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.AddBookRequest;
import com.kyaa.mockito.data.model.Book;

import java.util.List;

public interface BookService {
    void addBook(AddBookRequest addBookRequest);
    Book findBookById(Long id);
    List<Book> findAllBooks();

    List<Book> findBookByIds(List<Long> ids);
}
