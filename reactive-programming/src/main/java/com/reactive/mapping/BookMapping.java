package com.reactive.mapping;

import com.reactive.dto.BookResponse;
import com.reactive.dto.CreateBookRequest;
import com.reactive.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapping {

    public Book mappedToBook(CreateBookRequest request) {
        return Book.builder()
                .name(request.name())
                .description(request.description())
                .publisher(request.publisher())
                .author(request.author())
                .build();
    }

    public BookResponse mappedToBookResponse(Book book) {
        return BookResponse.builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .description(book.getDescription())
                .publisher(book.getPublisher())
                .author(book.getAuthor())
                .build();
    }

}
