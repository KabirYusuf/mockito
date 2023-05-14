package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.AddBookRequest;
import com.kyaa.mockito.data.model.Book;
import com.kyaa.mockito.data.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockitoBookServiceTest {
    @InjectMocks
    private MockitoBookService mockitoBookService;
    @Mock
    BookRepository bookRepository;
    private AddBookRequest addBookRequest;
    @BeforeEach
    void setUp(){
        addBookRequest = new AddBookRequest();
        addBookRequest.setDatePublished("23-03-2021");
        addBookRequest.setName("Mockito");
        addBookRequest.setPrice(BigDecimal.valueOf(300));
        addBookRequest.setQuantity(10);
    }

    @Test
    void addBook() {
        Book book = new Book();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        book.setDatePublished(LocalDate.parse(addBookRequest.getDatePublished(), dateTimeFormatter));
        book.setName(addBookRequest.getName());
        book.setQuantity(addBookRequest.getQuantity());
        book.setPrice(addBookRequest.getPrice());
        Book savedBook = new Book();
        savedBook.setDatePublished(LocalDate.parse(addBookRequest.getDatePublished(), dateTimeFormatter));
        savedBook.setName(addBookRequest.getName());
        savedBook.setId(1L);
        savedBook.setQuantity(addBookRequest.getQuantity());
        savedBook.setPrice(addBookRequest.getPrice());
        when(bookRepository.save(book)).thenReturn(savedBook);
        mockitoBookService.addBook(addBookRequest);
        verify(bookRepository, times(1)).save(book);
    }
    @Test
    void twoBooksWithSameNameCantBeSavedInDB(){
        Book book = new Book();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        book.setDatePublished(LocalDate.parse(addBookRequest.getDatePublished(), dateTimeFormatter));
        book.setName(addBookRequest.getName());
        book.setQuantity(addBookRequest.getQuantity());
        book.setPrice(addBookRequest.getPrice());

        when(bookRepository.findBooksByName("Mockito")).thenReturn(Optional.of(book));

        mockitoBookService.addBook(addBookRequest);


        verify(bookRepository).findBooksByName("Mockito");
        verifyNoMoreInteractions(bookRepository);
        verify(bookRepository, never()).save(book);
    }

    @Test
    void findBookById() {
        Book book = new Book();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = mockitoBookService.findBookById(1L);

        verify(bookRepository, times(1)).findById(1L);
        assertEquals(book, foundBook);
    }

    @Test
    void findAllBooks() {
        Book book1 = new Book(null, "Mockito", 3,LocalDate.now(), BigDecimal.valueOf(200));
        Book book2 = new Book(null, "Mockito", 3,LocalDate.now(), BigDecimal.valueOf(200));

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        when(bookRepository.findAll()).thenReturn(bookList);

        var books = mockitoBookService.findAllBooks();

        verify(bookRepository).findAll();

        assertEquals(2, bookList.size());

    }
    @Test
    void findBooksByIdTest(){
        Book book1 = new Book(1L, "Mockito", 3,LocalDate.now(), BigDecimal.valueOf(200));
        Book book2 = new Book(2L, "Mockito", 3,LocalDate.now(), BigDecimal.valueOf(200));
        Book book3 = new Book(3L, "Mockito", 3,LocalDate.now(), BigDecimal.valueOf(200));


        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));
        when(bookRepository.findById(3L)).thenReturn(Optional.of(book3));


        var books = mockitoBookService.findBookByIds(List.of(1L,2L,3L));

        verify(bookRepository).findById(1L);
        verify(bookRepository).findById(2L);
        verify(bookRepository).findById(3L);

        assertEquals(3, books.size());
    }
}