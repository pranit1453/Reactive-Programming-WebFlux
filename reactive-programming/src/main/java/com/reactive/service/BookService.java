package com.reactive.service;

import com.reactive.dto.BookResponse;
import com.reactive.dto.CreateBookRequest;
import com.reactive.dto.UpdateBookRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Mono<BookResponse> createNewBook(CreateBookRequest request);

    Flux<BookResponse> getAllBooks();

    Mono<BookResponse> getBookById(Integer bookId);

    Mono<BookResponse> updateBookById(Integer bookId, UpdateBookRequest request);

    Mono<Void> deleteBookById(Integer bookId);

    Flux<BookResponse> search(String keyword);
}
