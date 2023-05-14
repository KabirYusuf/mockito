package com.kyaa.mockito.data.repository;

import com.kyaa.mockito.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBooksByName(String name);
}
