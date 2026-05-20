package com.reactive.service;

import com.reactive.dto.BookResponse;
import com.reactive.dto.CreateBookRequest;
import com.reactive.dto.UpdateBookRequest;
import com.reactive.exception.BaseException;
import com.reactive.mapping.BookMapping;
import com.reactive.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapping bookMapping;
    private final TransactionalOperator transactionalOperator;

    @Override
    public Mono<BookResponse> createNewBook(CreateBookRequest request) {
        System.out.println("Thread: " + Thread.currentThread().getName());
        return Mono.just(request)
                .doOnNext(r -> {
                    System.out.println("REQUEST: " + r);
                    System.out.println("THREAD: " + Thread.currentThread().getName());
                })
                .map(bookMapping::mappedToBook)
                .flatMap(bookRepository::save)
                .doOnNext(b -> {
                    System.out.println("ENTITY: " + b);
                    System.out.println("THREAD: " + Thread.currentThread().getName());
                })
                .map(bookMapping::mappedToBookResponse);
    }

    @Override
    public Flux<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .delayElements(Duration.ofSeconds(2))
                .map(bookMapping::mappedToBookResponse);
    }

    @Override
    public Mono<BookResponse> getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(
                        new BaseException("Book not found with id: " + bookId)))
                .map(bookMapping::mappedToBookResponse);
    }

    @Override
    public Mono<BookResponse> updateBookById(Integer bookId, UpdateBookRequest request) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new BaseException("Book not found with id: " + bookId)))
                .flatMap(oldBook -> {
                    oldBook.setName(request.name());
                    oldBook.setDescription(request.description());
                    oldBook.setPublisher(request.publisher());
                    oldBook.setAuthor(request.author());
                    return bookRepository.save(oldBook);
                }).map(bookMapping::mappedToBookResponse);
    }

    @Override
    public Mono<Void> deleteBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new BaseException("Book not found with id: " + bookId)))
                .flatMap(bookRepository::delete);
    }

    @Override
    public Flux<BookResponse> search(String keyword) {
        return bookRepository.searchBookByKeyword(keyword)
                .switchIfEmpty(Mono.error(new BaseException("Book not found with keyword: " + keyword)))
                .map(bookMapping::mappedToBookResponse);
    }
}
