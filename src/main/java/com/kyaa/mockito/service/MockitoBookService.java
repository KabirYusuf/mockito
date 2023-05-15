package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.AddBookRequest;
import com.kyaa.mockito.data.model.Book;
import com.kyaa.mockito.data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MockitoBookService implements BookService{
    private final BookRepository bookRepository;
    @Override
    public void addBook(AddBookRequest addBookRequest) {
        if (bookRepository.findBooksByName(addBookRequest.getName()).isPresent())return;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Book book = new Book();
        book.setDatePublished(LocalDate.parse(addBookRequest.getDatePublished(), dateTimeFormatter));
        book.setName(addBookRequest.getName());
        book.setQuantity(addBookRequest.getQuantity());
        book.setPrice(addBookRequest.getPrice());
        bookRepository.save(book);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new IllegalStateException("Book does not exist"));
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBookByIds(List<Long> ids) {
        return ids
                .stream()
                .map(bookRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
