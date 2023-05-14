package com.kyaa.mockito.data.repository;

import com.kyaa.mockito.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
